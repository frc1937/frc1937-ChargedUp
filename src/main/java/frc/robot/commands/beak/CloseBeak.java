// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.beak;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

/** Set the beaks position to the position of starting the detection */
public class CloseBeak extends CommandBase {
  private BeakSubsystem m_beak;
  
  /** Creates a new OpenBeak. */
  public CloseBeak(BeakSubsystem m_beak) {
    this.m_beak = m_beak;

    addRequirements(m_beak);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_beak.isBeakAble())
      m_beak.getController().setReference(BeakConstants.BEAK_CUBE_START_POSITION, ControlType.kPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_beak.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_beak.getPosition() - BeakConstants.BEAK_CUBE_START_POSITION) < 1 || !m_beak.isBeakAble();
  }
}
