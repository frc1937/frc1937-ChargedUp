// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PhysicalProperties;
import frc.robot.subsystems.TrackSubsystem;

// Toggle the track so if it started open it will close and if it started closed it will open
public class ToggleTrack extends CommandBase {
  private TrackSubsystem m_track;
  private boolean isOpen;
  private double speed = PhysicalProperties.TrackConstants.TRACK_MOVEMENT_SPEED;
  

  /** Creates a new ToggleTrack. */
  public ToggleTrack(TrackSubsystem m_track) {
    addRequirements(m_track);
    
    this.m_track = m_track;
    this.isOpen = m_track.isTrackActive();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Toggle the piston
    m_track.togglePiston();

    // Closes the track if it's open and closes it if it's closed
    if (isOpen)
      m_track.setSpeed(-speed);
    else
      m_track.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // If the command is finished then finish the movement
    m_track.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Return true if the track has arrived at its max position whether it's closed or opened
    return reachedClosedPosition() || reachedOpenPosition();
  }

  // @return true if the track reached it's max pos starting closed, otherwise false
  public boolean reachedClosedPosition() {
    return isOpen && m_track.reachedMinSwitch();
  }

  
  // @return true if the track reached it's min pos starting opened, otherwise false
  public boolean reachedOpenPosition() {
    return !isOpen && m_track.reachedMaxSwitch();
  }
}
