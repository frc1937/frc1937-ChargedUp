// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ToggleLift extends InstantCommand {
  private LiftSubsystem m_lift;
  private boolean m_liftup;
  /** Creates a new ToggleLift. */
  public ToggleLift(LiftSubsystem m_lift) {
    this.m_lift = m_lift;
    this.m_liftup = m_lift.getLiftIsUp();

    addRequirements(m_lift);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_liftup) {
      m_lift.setPosition(LiftConstants.MINIMUM_MOTOR_POSITION);
    } else {
      m_lift.setPosition(LiftConstants.MAXIMUM_ENCODER_POSITION);
    }
  }
}