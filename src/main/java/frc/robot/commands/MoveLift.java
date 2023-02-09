// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftSubsystem;

public class MoveLift extends CommandBase {
  private final LiftSubsystem m_LiftSubsystem;
  /** Creates a new LiftUp. */
  public MoveLift(LiftSubsystem m_LiftSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_LiftSubsystem);
    this.m_LiftSubsystem = m_LiftSubsystem;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.m_LiftSubsystem.startMotor(LiftConstants.INITIALIZE_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_LiftSubsystem.stopMotor();
    this.m_LiftSubsystem.setIsToggleLift();
  }
    
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(this.m_LiftSubsystem.isToggleLift()){
      return this.m_LiftSubsystem.reachedMinSwitch();
    }

    return this.m_LiftSubsystem.reachedMaxPossion();
    
  }
}
