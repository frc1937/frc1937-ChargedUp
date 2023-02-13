// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

  private boolean intakeUp;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    m_leftMotor.setInverted(true);
    m_rightMotor.setInverted(false);
    m_angleMotor.setNeutralMode(NeutralMode.Brake);

    intakeUp = m_angleMotor.isRevLimitSwitchClosed() == 1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // @return whether the intake is up or down, true means up.
  public boolean getIsIntakeUp() {
    return intakeUp;
  }
  
  // Set the intake angle motor position to given value [revolutions].
  public void setIntakePosition(double position) {
    m_angleMotor.set(ControlMode.Position, position);
    intakeUp = !intakeUp; // Change the value of whether the intake is up or down.
  }

  // Open the intake pistons
  public void openIntake() {
    m_intakePistons.set(Value.kReverse);
  }

  // Close the intake pistons
  public void closeIntake() {
    m_intakePistons.set(Value.kForward);
  }

  // Set intake wheel speed for given value (-1 - 1)
  public void setIntakeWheelSpeed(double speed) {
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, -speed);
  }

  // Stop angle motor
  public void stopAngle() {
    m_angleMotor.set(ControlMode.Disabled, 0);
  }

  // Stop the intake wheel motors
  public void stopIntakeWheel() {
    m_leftMotor.set(ControlMode.Disabled, 0);
    m_rightMotor.set(ControlMode.Disabled, 0);
  }

   // Toggle the intake pistons
  public void togglePistons() {
    m_intakePistons.toggle();
  }
}
