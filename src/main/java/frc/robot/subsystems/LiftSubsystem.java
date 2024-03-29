// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.Ports.Lift;

/** The lift subsystem for controlling the lift */
public class LiftSubsystem extends SubsystemBase {
  private TalonFX m_liftMotor = new TalonFX(Lift.LIFT_MOTOR);
  private IntakeSubsystem m_intake;
  
  /** Creates a new LiftSubsystem. */
  public LiftSubsystem(IntakeSubsystem m_intake) {
    /** Define the reverse limit switch for the talon */
    this.m_intake = m_intake;

    /** Configure the maximum and minimum output of the motor */
    m_liftMotor.configPeakOutputForward(LiftConstants.K_MAX);
    m_liftMotor.configPeakOutputReverse(-LiftConstants.K_MAX);

    /** Configure the PIDF values for slot 0 */
    m_liftMotor.config_kP(0, LiftConstants.K_P_0);
    m_liftMotor.config_kI(0, LiftConstants.K_I_0);
    m_liftMotor.config_kD(0, LiftConstants.K_D_0);
    m_liftMotor.config_kF(0, LiftConstants.K_F_0);

    /** Configure the PIDF values for slot 1 */
    m_liftMotor.config_kP(1, LiftConstants.K_P_1);
    m_liftMotor.config_kI(1, LiftConstants.K_I_1);
    m_liftMotor.config_kD(1, LiftConstants.K_D_1);
    m_liftMotor.config_kF(1, LiftConstants.K_F_1);
  }

  @Override
  public void periodic() {
    if (limitSwitchPressed())
      m_liftMotor.setSelectedSensorPosition(0);
  }

  /** Stop the lift motor movement */
  public void stopMotor(){
    m_liftMotor.set(ControlMode.Disabled, 0);
  }

  /** Set the lift position and change PID values to the selected slot */
  public void setPosition(double targetPosition, int slot) {
    m_liftMotor.selectProfileSlot(slot, 0);
    m_liftMotor.set(TalonFXControlMode.Position, targetPosition);
  }

  /**
   * Get the lift position and whether it's up or down
   * @return true if the lift is in range of the limit switch and false otherwise
   */
  public boolean getLiftIsDown() {
    return Math.abs(m_liftMotor.getSelectedSensorPosition()) < 1000;
  }

  /**
   * Get the limit switch value
   * @return true if the limit switch is pressed and false otherwise
   */
  public boolean limitSwitchPressed() {
    return m_intake.getLiftSwitch();
  }

  /**
   * Set the lift movement speed
   * @param speed the value in precenteges [-1 <-> 1] of the lift motor
   */
  public void setSpeed(double speed) {
    m_liftMotor.set(ControlMode.PercentOutput, speed);
  }
}
