// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

/*
 * Raise the lift if it's lowered and lower it if it's raised
 */
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
    double motorPosition = m_liftup ? LiftConstants.MINIMUM_LIFT_POSITION : LiftConstants.MAXIMUM_LIFT_POSITION;
    m_lift.setPosition(motorPosition);
  }
}
