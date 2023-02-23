// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

/** Toggle the intake pistons */
public class ToggleIntakePistons extends InstantCommand {
  private IntakeSubsystem m_intake;

  public ToggleIntakePistons(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_intake.togglePistons();
  }
}