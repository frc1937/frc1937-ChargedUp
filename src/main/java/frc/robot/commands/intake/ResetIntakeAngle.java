// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

/** Reset the intake angle encoder when it reaches the limit switch */
public class ResetIntakeAngle extends CommandBase {
  private IntakeSubsystem m_intake;
  /** Creates a new ResetIntakeAngle. */
  public ResetIntakeAngle(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.stopIntakeWheel();
    m_intake.setAngleSpeed(-0.4);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.resetEncoder();
    m_intake.closeIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_intake.getSwitch();
  }
}
