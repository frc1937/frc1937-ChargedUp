// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.trackCommands;

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
    double targetPosition = m_track.isOpen() ? TrackConstants.MINIMUM_MOTOR_POS : TrackConstants.MAXIMUM_MOTOR_POS;
    m_track.setPosition(targetPosition);
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
    return isClosed() || isOpened();
  }

  private boolean isOpened() {
    return !m_track.isOpen() && m_track.getPosition() > TrackConstants.MAXIMUM_MOTOR_POS - TrackConstants.MAXIMUM_TOLERANCE;
  }

  private boolean isClosed() {
    return m_track.isOpen() && m_track.getPosition() <= 1000;
  }
}
