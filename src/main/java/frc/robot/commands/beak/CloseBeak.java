// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.beak;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.BeakConstants;
import frc.robot.subsystems.BeakSubsystem;

public class CloseBeak extends CommandBase {
  private BeakSubsystem m_beak;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("vision");
  NetworkTableEntry tgamePiece = table.getEntry("game_piece");

  double gamePiece;
  double targetPosition;

  /** Creates a new CloseBeak. */
  public CloseBeak(BeakSubsystem m_beak) {
    this.m_beak = m_beak;

    addRequirements(m_beak);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gamePiece = tgamePiece.getDouble(-1);

    targetPosition = gamePiece == 1 ? BeakConstants.BEAK_CUBE_HOLD_POSITION : BeakConstants.BEAK_CONE_HOLD_POSITION;
    if (gamePiece == -1) {
      targetPosition = m_beak.getPosition();
    }

    m_beak.getController().setReference(targetPosition, ControlType.kPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
