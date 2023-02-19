// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class ToggleIntake extends CommandBase {
  private IntakeSubsystem m_intake;
  private final double speed = IntakeConstants.INTAKE_WHEEL_SPEED;

  /* 
  * If the intake is up, lower it and open it as well as start the wheels,
  * when the button stops being pressed, close the intake but don't raise it nor stop the wheels.
  * If the intake is down, raise it as well as stop the wheels.
  */
  public ToggleIntake(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_intake.openIntake();
      m_intake.setIntakeWheelSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.closeIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
