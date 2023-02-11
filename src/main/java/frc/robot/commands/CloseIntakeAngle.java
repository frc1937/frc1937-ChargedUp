// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class CloseIntakeAngle extends CommandBase {
  private IntakeSubsystem m_intake;
  private double speed;
  /** Creates a new CloseIntakeAngle. */
  public CloseIntakeAngle(IntakeSubsystem m_intake, double speed) {
    this.m_intake = m_intake;
    this.speed = speed;

    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setAngleSpeed(-speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.stopAngle();
    m_intake.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_intake.microSwitchEnabaled();
  }
}
