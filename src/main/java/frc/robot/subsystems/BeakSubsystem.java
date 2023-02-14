// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalProperties.BeakConstants;
import frc.robot.Constants.Ports.Beak;

public class BeakSubsystem extends SubsystemBase {
  private CANSparkMax m_beakMotor = new CANSparkMax(Beak.BEAK_MOTOR_PORT, MotorType.kBrushless);
  private RelativeEncoder m_encoder = m_beakMotor.getEncoder();
  private boolean BeakRaised = true;
  
  private double m_setPoint = 0;
  private double k_p = BeakConstants.K_P;
  private double k_i = BeakConstants.K_I;
  private double k_d = BeakConstants.K_D;

  private SparkMaxPIDController m_controller = m_beakMotor.getPIDController();
  
  /** Creates a new BeakSubsystem. */
  public BeakSubsystem() {
    m_beakMotor.setIdleMode(IdleMode.kBrake);
    m_beakMotor.getEncoder().setPosition(0);
    SmartDashboard.setDefaultNumber("Set point", m_setPoint);
    SmartDashboard.setDefaultNumber("Position", m_encoder.getPosition());
    m_controller.setD(k_d);
    m_controller.setP(k_p);
    m_controller.setI(k_i);
    m_controller.setOutputRange(-0.6, 0.6);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_setPoint = SmartDashboard.getNumber("Set point", m_setPoint);
    SmartDashboard.putNumber("Position", m_beakMotor.getEncoder().getPosition());
  }

  // Stop the beaks movement
  public void stopMotor() {
    m_beakMotor.stopMotor();
  }

   // @return the point the motor needs to reach
   public double getSetpoint() {
    return m_setPoint;
  }
 
  // @return the pid controller
  public SparkMaxPIDController getController() {
    return m_controller;
  } 

  // Reset the beak encoder 
  public void ResetBeakEncoder() {
    m_beakMotor.getEncoder().setPosition(0);
  }

  // @return true if the beak is raised and false if it's not
  public boolean getBeakUp() {
    BeakRaised = !BeakRaised;
    return !BeakRaised;
  }
}
