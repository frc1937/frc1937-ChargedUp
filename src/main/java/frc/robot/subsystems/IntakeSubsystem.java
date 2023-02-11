// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports.Intake;

public class IntakeSubsystem extends SubsystemBase {
  private final TalonSRX m_angleMotor = new TalonSRX(Intake.INTAKE_ANGLE_MOTOR);
  private final TalonSRX m_leftMotor = new TalonSRX(Intake.RIGHT_INTAKE_MOTOR);
  private final TalonSRX m_rightMotor = new TalonSRX(Intake.LEFT_INTAKE_MOTOR);
  private final DoubleSolenoid m_intakePistons = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Intake.OPEN_PISTONS, Intake.CLOSE_PISTONS);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean microSwitchEnabaled() {
    return m_angleMotor.isRevLimitSwitchClosed() == 1;
  }

  public void setAngleSpeed(double speed) {
    m_angleMotor.set(ControlMode.PercentOutput, speed);
  }

  public void openIntake() {
    m_intakePistons.set(Value.kReverse);
  }

  public void closeIntake() {
    m_intakePistons.set(Value.kForward);
  }

  public void setIntakeSpeed(double speed) {
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, -speed);
  }

  public double getAnglePos() {
    return m_angleMotor.getSelectedSensorPosition();
  }

  public void stopAngle() {
    m_angleMotor.set(ControlMode.Disabled, 0);
  }

  public void stopMotor() {
    m_leftMotor.set(ControlMode.Disabled, 0);
    m_rightMotor.set(ControlMode.Disabled, 0);
  }
}
