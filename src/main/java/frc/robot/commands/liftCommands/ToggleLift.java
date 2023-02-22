// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.liftCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

public class ToggleLift extends CommandBase {
  private LiftSubsystem m_lift;
  private boolean isUp; //target place is up or down
  /** Creates a new ToggleLift. */
  public ToggleLift(LiftSubsystem m_lift) {
    this.m_lift = m_lift;
    this.isUp = m_lift.getLiftIsUp();

    addRequirements(m_lift);
  }

  // Called when the command is initially scheduled.
  // set the target position to the min if the currpos is up and the oppsite
  @Override
  public void initialize() {
    double targetPos = isUp ? LiftConstants.MINIMUM_LIFT_POSITION : LiftConstants.MAXIMUM_LIFT_POSITION;
    //m_lift.setPosition(targetPos); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_lift.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
