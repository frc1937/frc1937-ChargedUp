// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AlignToGamePiece extends CommandBase {
  private DriveSubsystem m_drive;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("vision");
  NetworkTableEntry tx = table.getEntry("target_x");
  NetworkTableEntry tstatus = table.getEntry("cone_state");
  double x;
  double coneStatus = -1;
  
  public AlignToGamePiece(DriveSubsystem m_drive) {
    this.m_drive = m_drive;

    addRequirements(m_drive);
  }

  
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // Manipulate the rotation values a litle bit depending on the side of the tipped cone, so it will rotate more towards the tip
    x = tx.getDouble(0);
    if(coneStatus == 2){
      x -= 0.4;
    }
    else if(coneStatus == 3){
      x += 0.4;
    }

    if(x != -2 && x != 0){
      m_drive.arcadeDrive(0.4, x/2);
    }
    else{
      m_drive.arcadeDrive(0.3, 0);
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
