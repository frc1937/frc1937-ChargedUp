// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.intakeWheelState;

public class SucCone extends CommandBase {
  IntakeSubsystem m_intake;
  intakeWheelState priorState;
  /** Creates a new SucCone. */
  public SucCone(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;
    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    priorState = m_intake.getState();
    m_intake.setWheelState(intakeWheelState.Inly);
    m_intake.openIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setWheelState(priorState);
    m_intake.closeIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
