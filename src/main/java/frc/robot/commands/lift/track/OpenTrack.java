// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.lift.track;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.TrackConstants;
import frc.robot.subsystems.TrackSubsystem;

/** Open the track first layer and then the sencond layers piston */
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
  //opens the two pistons of the track
  m_track.openPiston();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_track.isOpen();
  }
}
