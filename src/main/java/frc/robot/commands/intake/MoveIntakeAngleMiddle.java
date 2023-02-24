// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

/** Set the intake angle to 45 degrees*/
public class MoveIntakeAngleMiddle extends InstantCommand {
  private IntakeSubsystem m_intake;

  public MoveIntakeAngleMiddle(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;
    
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.movePID(0, true);
  }
}
