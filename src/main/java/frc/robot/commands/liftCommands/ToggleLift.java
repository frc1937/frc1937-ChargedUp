// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.liftCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

/** 
 * Toggle the lift, meaning that if the lift is down then raise it and if it is raised then lower it
 * */
public class ToggleLift extends CommandBase {
  private LiftSubsystem m_lift;
  private boolean isDown; 

  /** Creates a new ToggleLift. */
  public ToggleLift(LiftSubsystem m_lift) {
    this.m_lift = m_lift;
    this.isDown = m_lift.getLiftIsDown();

    addRequirements(m_lift);
  }

  @Override
  public void initialize() {
    double targetPos = isDown ?  LiftConstants.MAXIMUM_LIFT_POSITION : LiftConstants.MINIMUM_LIFT_POSITION;
    m_lift.setPosition(targetPos, isDown ? 0 : 1); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_lift.stopMotor();
  }

  /** 
   * @return true if the lift started at the top and reached the minimum position
   */
  @Override
  public boolean isFinished() {
    return isDown && m_lift.getLiftIsDown();
  }
}
