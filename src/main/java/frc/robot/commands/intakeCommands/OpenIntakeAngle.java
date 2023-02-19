// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class OpenIntakeAngle extends CommandBase {
  private IntakeSubsystem m_intake;
  /** Creates a new OpenIntakeAngle. */
  public OpenIntakeAngle(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.movePID(1);
  }

  // Stop the intake angle motor when it reached it's maximum position 
  @Override
  public void end(boolean interrupted) {
    m_intake.stopAngle();
  }

  // Returns true when the intake angle reached it's max position.
  @Override
  public boolean isFinished() {
    return m_intake.getPosition() >= IntakeConstants.MAXIMUM_POSITION;
  }
}
