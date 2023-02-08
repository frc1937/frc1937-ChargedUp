// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PhysicalProperties.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

public class ToggleBeak extends CommandBase {
  private BeakSubsystem m_beak;
  private double speed = BeakConstants.BEAK_MOTOR_SPEED;
  private boolean isOpen;

  // Close if open or open if close
  public ToggleBeak(BeakSubsystem m_beak) {
    addRequirements(m_beak);
    this.m_beak = m_beak;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isOpen = m_beak.getIsOpen();
    m_beak.setInverted();
    m_beak.setSpeed(speed);
  }

  /* Called once the command ends or is interrupted and stop the beak motor.
   */
  @Override
  public void end(boolean interrupted) {
    m_beak.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_beak.reachedClosed(isOpen) || m_beak.reachedOpen(isOpen);
  }
}
