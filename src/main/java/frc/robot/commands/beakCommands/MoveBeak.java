// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.beakCommands;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

/*
 * Moves the beak until it detects resistance, if it reaches the max position of the cube 
 * then act as if it's a cone.
 */
public class MoveBeak extends CommandBase {
  private BeakSubsystem m_beak;
  
  /** Creates a new MoveBeak. */
  public MoveBeak(BeakSubsystem m_beak) {
    this.m_beak = m_beak;

    addRequirements(m_beak);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_beak.isBeakAble())
      m_beak.setVoltage(-0.7);
  }

  // Called every time the scheduler runs while tPhe command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (m_beak.getPosition() <= BeakConstants.BEAK_CUBE_MAX_POSITION && m_beak.isBeakAble()) {
      m_beak.getController().setReference(BeakConstants.BEAK_CONE_HOLD_POS, ControlType.kPosition);
    } else if (m_beak.isBeakAble()) {
      m_beak.getController().setReference(BeakConstants.BEAK_CUBE_HOLD_POSITION, ControlType.kPosition);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_beak.getPosition() <= BeakConstants.BEAK_CUBE_MAX_POSITION || Math.abs(m_beak.getVelocity()) < 5 || !m_beak.isBeakAble();
  }
}
