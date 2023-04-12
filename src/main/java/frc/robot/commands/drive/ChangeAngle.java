// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ChangeAngle extends CommandBase {
  /** Creates a new ChangeAngle. */
  private DriveSubsystem m_drive;
  private int targetAngle;

  public ChangeAngle(DriveSubsystem m_drive, int targetAngle) {
    this.m_drive = m_drive;
    this.targetAngle = targetAngle;

    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.getGyro().setYaw(0);
    m_drive.arcadeDrive(0, 0.3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(targetAngle - m_drive.getYaw()) <= 15;
  }
}
