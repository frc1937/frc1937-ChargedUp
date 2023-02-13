// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports.Lift;

public class LiftSubsystem extends SubsystemBase {
  private TalonFX m_liftMotor = new TalonFX(Lift.LIFT_MOTOR);
  private DigitalInput m_switch = new DigitalInput(Lift.LIFT_SWITCH);
  private boolean m_liftUp = false;
  
  /** Creates a new LiftSubsystem. */
  public LiftSubsystem() {}

  @Override
  public void periodic() {
    // Reset encoder if micro switch is pressed.
    if (m_switch.get())
      m_liftMotor.setSelectedSensorPosition(0);
  }

  // Stop the lift Motor
  public void stopMotor(){
    m_liftMotor.set(ControlMode.Disabled, 0);
  }

  // Set the motor position
  public void setPosition(double enoderPosition) {
    m_liftMotor.set(ControlMode.Position, enoderPosition);
  }

  // @return true if the lift is up and false if it's down.
  public boolean getLiftIsUp() {
    m_liftUp = !m_liftUp;
    return !m_liftUp;
  }
}
