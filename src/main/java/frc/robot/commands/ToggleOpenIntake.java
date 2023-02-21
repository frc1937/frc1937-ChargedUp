// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ToggleOpenIntake extends InstantCommand {
  private IntakeSubsystem m_intake;
  
  public ToggleOpenIntake(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (!m_intake.getIsUp()) {
      m_intake.togglePistons();
    } else {
      m_intake.movePID(1);
      m_intake.setIntakeWheelSpeed(IntakeConstants.INTAKE_WHEEL_SPEED);
      m_intake.setIsUp(false);
    }
  }
}
