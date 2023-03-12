// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.track;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TrackSubsystem;

/** Close the track first layer and then the sencond layers piston */
public class CloseTrack extends InstantCommand {
  private final TrackSubsystem m_track;
  /** Creates a new CloseTrack. */
  public CloseTrack(TrackSubsystem m_track) {
    this.m_track = m_track;

    addRequirements(m_track);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_track.closeTrack();
  }
}



