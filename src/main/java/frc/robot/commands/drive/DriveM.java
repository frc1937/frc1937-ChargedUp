// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveM extends CommandBase {
  private DriveSubsystem m_drive;
  private double targetPosition;
  /** Creates a new DriveM. */
  public DriveM(DriveSubsystem m_drive, double targetPosition) {
    this.m_drive = m_drive;
    this.targetPosition = targetPosition;

    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetPoseMetres(null);
    double speed = targetPosition > 0 ? 0.4 : -0.4;
    m_drive.arcadeDrive(speed, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (targetPosition > 0 && m_drive.getLeftTravelDistanceMetres() >= targetPosition) ||
           (targetPosition < 0 && m_drive.getLeftTravelDistanceMetres() <= targetPosition);
  }
}
