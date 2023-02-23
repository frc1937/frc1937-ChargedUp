// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.beak;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

/** Close the beak to the cubes position */
public class CloseCube extends InstantCommand {
  private BeakSubsystem m_beak;

  public CloseCube(BeakSubsystem m_beak) {
    this.m_beak = m_beak;

    addRequirements(m_beak);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_beak.getController().setReference(BeakConstants.BEAK_CUBE_HOLD_POSITION, ControlType.kPosition);
  }
}
