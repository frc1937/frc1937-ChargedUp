// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeAngleState;
import frc.robot.subsystems.IntakeSubsystem.intakeWheelState;

public class ShootCubeMiddle extends CommandBase {
  IntakeSubsystem m_intake;
  LiftSubsystem m_lift;
  /** Creates a new ShootCube. */
  public ShootCubeMiddle(IntakeSubsystem m_intake, LiftSubsystem m_lift) {
    this.m_intake = m_intake;
    this.m_lift = m_lift;

    addRequirements(m_intake, m_lift);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setAngleState(IntakeAngleState.Middle);
    m_lift.setPosition(30000, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_intake.getPosition() > 650){
      m_intake.setWheelState(intakeWheelState.ShootMiddle);
      m_intake.setAngleState(IntakeAngleState.Upwards);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setWheelState(intakeWheelState.Stop);
    m_lift.setPosition(0, 1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_intake.getAngleState() == IntakeAngleState.Upwards && m_intake.getPosition() < 300;
  }
}
