// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.PhysicalProperties.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class OpenBeak extends InstantCommand {
  private BeakSubsystem m_beak;
  /** Creates a new OpenBeak. */
  public OpenBeak(BeakSubsystem m_beak) {
    this.m_beak = m_beak;

    addRequirements(m_beak);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_beak.getController().setReference(BeakConstants.BEAK_CUBE_MAX_POS, ControlType.kPosition);
  }
}
