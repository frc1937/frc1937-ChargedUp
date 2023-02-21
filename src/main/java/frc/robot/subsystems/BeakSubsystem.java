// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BeakConstants;
import frc.robot.Constants.Ports;

/** The beak subsystem for controlling the beaks angle */
public class BeakSubsystem extends SubsystemBase {
  private CANSparkMax m_beakMotor = new CANSparkMax(Ports.Beak.BEAK_MOTOR, MotorType.kBrushless);
  private RelativeEncoder m_encoder = m_beakMotor.getEncoder();
  private TalonSRX m_intakeAngleMotor = IntakeSubsystem.m_angleMotor; 
  
  private double k_p = BeakConstants.K_P;
  private double k_i = BeakConstants.K_I;
  private double k_d = BeakConstants.K_D;

  private SparkMaxPIDController m_controller = m_beakMotor.getPIDController();
  
  /** Creates a new BeakSubsystem. */
  public BeakSubsystem() {
    m_beakMotor.restoreFactoryDefaults();

    m_beakMotor.setIdleMode(IdleMode.kCoast);
    m_beakMotor.getEncoder().setPosition(0);
    m_controller.setD(k_d);
    m_controller.setP(k_p);
    m_controller.setI(k_i);
    m_controller.setOutputRange(-0.6, 0.6);
  }

  @Override
  public void periodic() {}

  // Stop the beaks movement
  public void stopMotor() {
    m_beakMotor.stopMotor();
  }
 
  /*
   * @return  PIDController of the beak motor
   */
  public SparkMaxPIDController getController() {
    return m_controller;
  } 

  // Reset the beak encoder 
  public void ResetBeakEncoder() {
    m_beakMotor.getEncoder().setPosition(0);
  }

  /*
   * @return  The motors current position
   */
  public double getPosition() {
    return m_encoder.getPosition();
  }

  /*
   * @param voltage the motor will get
   */
  public void setVoltage(double voltage) {
    m_beakMotor.setVoltage(voltage);
  }

  /*
   * @return  The velocity of the motor
   */
  public double getVelocity() {
    return m_encoder.getVelocity();
  }

  public boolean isBeakAble() {
    return m_intakeAngleMotor.getSelectedSensorPosition() <= 200;
  }
  
}
