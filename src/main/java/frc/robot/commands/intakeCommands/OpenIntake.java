// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

/** Open the intake and it's angle also whilst opening the pistons and activating the wheels */
public class OpenIntake extends CommandBase {
  private IntakeSubsystem m_intake;
  
  public OpenIntake(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.openIntake();
    m_intake.movePID(1);
    m_intake.setIntakeWheelSpeed(IntakeConstants.INTAKE_WHEEL_SPEED);
    m_intake.setIsUp(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.closeIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
