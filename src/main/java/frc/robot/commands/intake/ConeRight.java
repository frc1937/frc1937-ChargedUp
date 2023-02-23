// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

/** Center the cone when it leans twards the right */
public class ConeRight extends InstantCommand {
  private IntakeSubsystem m_intake;

  public ConeRight(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setIntakeWheelSpeedOposing(-IntakeConstants.INTAKE_WHEEL_SPEED);

  }
}
