// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.beakCommands;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CloseCone extends InstantCommand {
  private BeakSubsystem m_beak;
  public CloseCone(BeakSubsystem m_beak) {
    this.m_beak = m_beak;

    addRequirements(m_beak);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_beak.getController().setReference(BeakConstants.BEAK_CONE_HOLD_POSITION, ControlType.kPosition);
  }
}
