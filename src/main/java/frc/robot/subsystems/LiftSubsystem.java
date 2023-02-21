// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.Ports.Lift;

/** The lift subsystem for controlling the lift */
public class LiftSubsystem extends SubsystemBase {
  private TalonFX m_liftMotor = new TalonFX(Lift.LIFT_MOTOR);
  private TalonSRX m_lifLimitSwitch = IntakeSubsystem.m_leftMotor;
  private boolean m_liftUp;
  
  /** Creates a new LiftSubsystem. */
  public LiftSubsystem() {
    m_liftMotor.configFactoryDefault();
    m_liftMotor.config_kP(0, LiftConstants.K_P_0);
    m_liftMotor.config_kI(0, LiftConstants.K_I_0);
    m_liftMotor.config_kD(0, LiftConstants.K_D_0);
    m_liftMotor.config_kF(0, LiftConstants.K_F_0);
    m_liftMotor.configPeakOutputForward(LiftConstants.K_MAX_0);
    m_liftMotor.configPeakOutputReverse(-LiftConstants.K_MAX_0);

    m_liftMotor.config_kP(1, LiftConstants.K_P_1);
    m_liftMotor.config_kI(1, LiftConstants.K_I_1);
    m_liftMotor.config_kD(1, LiftConstants.K_D_1);
    m_liftMotor.config_kF(1, LiftConstants.K_F_1);
    m_liftMotor.configPeakOutputForward(LiftConstants.K_MAX_1);
    m_liftMotor.configPeakOutputReverse(-LiftConstants.K_MAX_1);
  }

  @Override
  public void periodic() {
    if (m_lifLimitSwitch.isRevLimitSwitchClosed() == 1)
      m_liftMotor.setSelectedSensorPosition(0);
  }

  // Stop the lift Motor
  public void stopMotor(){
    m_liftMotor.set(ControlMode.Disabled, 0);
  }

  // Set the motor position
  public void setPosition(double targetPosition, int slot) {
    m_liftMotor.selectProfileSlot(slot, 0);
    m_liftMotor.set(TalonFXControlMode.Position, targetPosition);
  }

  // @return true if the lift is up and false if it's down.
  public boolean getLiftIsUp() {
    return Math.abs(m_liftMotor.getSelectedSensorPosition()) < 1000;
  }


}
