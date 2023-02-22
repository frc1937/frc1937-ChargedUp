// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.liftCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

/** Raise the lift to it's maximum position using {@link LiftSubsystem} */
public class OpenLift extends InstantCommand {
  private LiftSubsystem m_lift;
  public OpenLift(LiftSubsystem m_lift) {
    this.m_lift = m_lift;

    addRequirements(m_lift);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_lift.setPosition(LiftConstants.MAXIMUM_LIFT_POSITION, 0);
  }
}
