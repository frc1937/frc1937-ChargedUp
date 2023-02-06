// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

// Enables drivers control the robot during teleop period with xbox controller
public class ArcadeDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final CommandXboxController XboxController;

  public ArcadeDrive(CommandXboxController XboxController, DriveSubsystem m_drive) {
    addRequirements(m_drive);

    this.m_drive = m_drive;
    this.XboxController = XboxController;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Get the controller values every time the schedueler runs and moves the robot in those values
    m_drive.arcadeDrive(XboxController.getLeftY() * DriveConstants.CONTROLLER_SENSETIVITY, 
    XboxController.getRightX() * DriveConstants.CONTROLLER_SENSETIVITY);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (!interrupted) {
      // Stops the driving if the command ends
      m_drive.stopMotor();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
