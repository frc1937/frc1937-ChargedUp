// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;
import frc.robot.Constants.Ports.Lift;

public class LiftSubsystem extends SubsystemBase {
  private TalonFX m_liftMotor = new TalonFX(Lift.LIFT_MOTOR);
  //private DigitalInput m_switch = new DigitalInput(Ports.Lift.SWITCH);
  
  /** Creates a new LiftSubsystem. */
  public LiftSubsystem() {
    m_liftMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("asdasdas dasd asd", m_liftMotor.getSelectedSensorPosition());
  }

  // Stop motor 
  public void stopMotor(){
    m_liftMotor.set(ControlMode.Disabled, 0);
  }

  // Set the motor position
  public void setPosition(double enoderPosition) {
    m_liftMotor.set(ControlMode.Position, enoderPosition);
  }
}
