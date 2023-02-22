// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Open the intake for consumption of object and activate the wheels,
 * every time this command is called the pistons are toggled.
 * This command CANT be activated when the lift is enabled.
 */
public class ToggleOpenIntake extends InstantCommand {
  private IntakeSubsystem m_intake;
  private boolean liftUp;
  
  public ToggleOpenIntake(IntakeSubsystem m_intake, boolean liftUp) {
    this.m_intake = m_intake;
    this.liftUp = liftUp;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (!liftUp) {
      m_intake.movePID(1);
      m_intake.setIntakeWheelSpeed(IntakeConstants.INTAKE_WHEEL_SPEED);
      m_intake.setIsUp(false);
      m_intake.togglePistons();
    }     
  }
}
