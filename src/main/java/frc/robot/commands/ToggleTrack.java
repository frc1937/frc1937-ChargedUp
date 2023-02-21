// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TrackConstants;
import frc.robot.subsystems.TrackSubsystem;

public class ToggleTrack extends CommandBase {
  private TrackSubsystem m_track;
  /** Creates a new ToggleTrack. */
  public ToggleTrack(TrackSubsystem m_track) {
    this.m_track = m_track;

    addRequirements(m_track);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double speed = m_track.isOpen() ? -TrackConstants.TRACK_MOVEMENT_SPEED : TrackConstants.TRACK_MOVEMENT_SPEED;
    m_track.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (m_track.isOpen())
      m_track.closePiston();
    else 
      m_track.openPiston();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_track.reachedMaxSwitch() && !m_track.isOpen()) || (m_track.reachedMinSwitch() && m_track.isOpen());
  }
}
