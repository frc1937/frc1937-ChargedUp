// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.intakeWheelState;

/**
 * Open the intake for consumption of object and activate the wheels,
 * every time this command is called the pistons are toggled.
 * This command CANT be activated when the lift is enabled.
 */
public class ToggleOpenIntake extends InstantCommand {
  private IntakeSubsystem m_intake;
  private boolean liftDown;
  
  public ToggleOpenIntake(IntakeSubsystem m_intake, boolean liftDown) {
    this.m_intake = m_intake;
    this.liftDown = liftDown;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (liftDown) {
      if (m_intake.currentlyUp()) {
        m_intake.movePID(1);
        m_intake.setWheelState(intakeWheelState.In);
        m_intake.setIsUp(false);
        m_intake.openIntake();
      }
      m_intake.togglePistons();
    }     
  }
}
