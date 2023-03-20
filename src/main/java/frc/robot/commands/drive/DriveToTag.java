// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToTag extends CommandBase {
  DriveSubsystem m_drive;
  NetworkTable m_table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry m_tx = m_table.getEntry("tx");
  NetworkTableEntry m_ty = m_table.getEntry("ty");

  double tx;
  double ty;
  /** TODO: Find the propper values */
  double target_tx = -1;
  double target_ty = -1;
  /** Creates a new DriveToTag. */
  public DriveToTag(DriveSubsystem m_drive) {
    this.m_drive = m_drive;

    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = m_tx.getDouble(0);
    ty = m_ty.getDouble(0);

    m_drive.arcadeDrive(
      ty / (target_ty * 0.5),
      tx / (target_tx * 0.5));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return check_ty() && check_tx();
  }

  private boolean check_ty() {
    return ty > target_ty - 0.5 && ty < target_ty + 0.5;
  }

  private boolean check_tx() {
    return tx > target_tx - 0.5 && tx < target_tx + 0.5;
  }
}
