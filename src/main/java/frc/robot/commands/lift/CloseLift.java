// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

/** Lower the lift position to it's minimum position using {@link LiftSubsystem} */
public class CloseLift extends InstantCommand {
  private LiftSubsystem m_lift;

  public CloseLift(LiftSubsystem m_lift) {
    this.m_lift = m_lift;

    addRequirements(m_lift);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_lift.setPosition(LiftConstants.MINIMUM_LIFT_POSITION,1);
  }
}
