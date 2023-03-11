// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeAngleState;
import frc.robot.subsystems.IntakeSubsystem.intakeWheelState;

/** Open the intake and it's angle also whilst opening the pistons and activating the wheels */
public class AutoOpenIntake extends CommandBase {
  private IntakeSubsystem m_intake;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("vision");
  NetworkTableEntry tgamePiece = table.getEntry("game_piece");
  NetworkTableEntry tstatus = table.getEntry("cone_state");
  NetworkTableEntry tarea = table.getEntry("target_area");
  NetworkTableEntry tx = table.getEntry("target_x");

  double gamePiece;
  double coneStatus;
  double area;
  double x;
  double y;

  public AutoOpenIntake(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_intake.openIntake();
    //m_intake.setAngleState(IntakeAngleState.Down);
    m_intake.setWheelState(intakeWheelState.In);
    m_intake.setIsUp(false);
  }

  @Override
  public void execute() {
    gamePiece = tgamePiece.getDouble(0);
    coneStatus = tstatus.getDouble(0);
    area = tarea.getDouble(0);
    x = tx.getDouble(0);
    
    SmartDashboard.putNumber("Cone Status", coneStatus);

    // Change the wheel speed and direction based on the detected target
    if(gamePiece == 1){
      m_intake.setWheelState(intakeWheelState.Stop);
    }
    else if(coneStatus == 2){
      m_intake.setWheelState(intakeWheelState.Right);
    }
    else if(coneStatus == 3){
    m_intake.setWheelState(intakeWheelState.Left);
    }
    else{
      m_intake.setWheelState(intakeWheelState.In);
    }


    // if((area > 55 && coneStatus == 0) || area > 80){
    //   m_intake.closeIntake();
    // }
    // else{
    //   m_intake.openIntake();
    // }

  }

  @Override
  public void end(boolean interrupted) {
    m_intake.closeIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
