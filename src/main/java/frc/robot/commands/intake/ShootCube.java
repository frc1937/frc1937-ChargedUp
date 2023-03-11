// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeAngleState;
import frc.robot.subsystems.IntakeSubsystem.intakeWheelState;

public class ShootCube extends CommandBase {
  IntakeSubsystem m_intake;
  /** Creates a new ShootCube. */
  public ShootCube(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setAngleState(IntakeAngleState.Middle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putNumber("Pos Intake", m_intake.getPosition());
    m_intake.setWheelState(intakeWheelState.Spit);
    m_intake.setAngleState(IntakeAngleState.Up);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
