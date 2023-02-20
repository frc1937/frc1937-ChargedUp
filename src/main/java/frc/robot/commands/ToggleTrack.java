// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.PhysicalProperties.TrackConstants;
import frc.robot.subsystems.TrackSubsystem;

public class ToggleTrack extends InstantCommand {
  private TrackSubsystem m_track;

  public ToggleTrack(TrackSubsystem m_track) {
    this.m_track = m_track;

    addRequirements(m_track);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_track.isOpen()) {
      m_track.setPosition(TrackConstants.MAX_MOTOR_POS);
      m_track.openPiston();
    } else {
      m_track.setPosition(TrackConstants.MIN_MOTOR_POS);
      m_track.closePiston();
    }
  }
}
