// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AlignToPole extends CommandBase {
  private DriveSubsystem m_drive;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  double x;

  public AlignToPole(DriveSubsystem m_drive) {
    this.m_drive = m_drive;

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    x = tx.getDouble(0);
    m_drive.arcadeDrive(0, x);
    SmartDashboard.putNumber("currenTx: ", x);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return Math.abs(x - DriveConstants.TOLERANCE_DEGREES) <= 1;
  }
}