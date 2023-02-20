// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TrackSubsystem;
import frc.robot.Constants.TrackConstants;

public class OpenTrack extends CommandBase {
  private TrackSubsystem m_track;
  /** Creates a new OpenTrack. */
  public OpenTrack(TrackSubsystem m_track) {
    this.m_track = m_track;

    addRequirements(m_track);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_track.closePiston();
    m_track.setSpeed(TrackConstants.TRACK_MOVEMENT_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_track.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_track.reachedMaxSwitch(); /* @return true if reached forward limit switch */
  }
}
