// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ArcDrive extends CommandBase {
  /** Creates a new ArcDrive. */
  DriveSubsystem m_drive;
  double startPitch;

  public ArcDrive(DriveSubsystem m_drive) {
    this.m_drive = m_drive;

    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startPitch = m_drive.getGyro().getYaw();
    m_drive.arcDrive(0.2,-0.43);
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
    return Math.abs(m_drive.getGyro().getYaw() - startPitch) - 7.5 >= 85;
  }
}
