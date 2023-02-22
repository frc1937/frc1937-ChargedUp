// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

/** Dispense the object currently in the intake */
public class DispenseCone extends InstantCommand {
  private IntakeSubsystem m_intake;

  public DispenseCone(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setIntakeWheelSpeed(-IntakeConstants.INTAKE_WHEEL_SPEED);
  }
}
