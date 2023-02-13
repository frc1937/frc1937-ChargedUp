// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.PhysicalProperties.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

/* 
 * TODO: add ability to detect whether there is a large amount of
 * resistance from the object for catching both cones and boxes.
*/
// Close the beak if it's raised and open if closed.
public class ToggleBeak extends InstantCommand {
  private BeakSubsystem m_beak;
  private boolean beakUp;

  public ToggleBeak(BeakSubsystem m_beak) {
    this.m_beak = m_beak;
    this.beakUp = m_beak.getBeakUp();

    addRequirements(m_beak);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (beakUp) {
      m_beak.getController().setReference(BeakConstants.BEAK_MIN_POS, ControlType.kPosition);
    } else {
      m_beak.getController().setReference(BeakConstants.BEAK_MAX_POS, ControlType.kPosition);
    }
  }
}
