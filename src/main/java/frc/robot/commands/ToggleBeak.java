// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PhysicalProperties.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

public class ToggleBeak extends CommandBase {
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
      m_beak.getController().setReference(BeakConstants.BEAK_CUBE_MAX_POS, ControlType.kPosition);
    }
  }
  
  @Override
  public void execute() {
    if (m_beak.getPosition() == BeakConstants.BEAK_CUBE_MAX_POS && beakUp) {
      m_beak.getController().setOutputRange(-0.1, 0.1);
      m_beak.getController().setReference(BeakConstants.BEAK_CONE_MAX_POS, ControlType.kPosition);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
