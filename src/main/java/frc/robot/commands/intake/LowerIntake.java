// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeAngleState;
import frc.robot.subsystems.IntakeSubsystem.intakeWheelState;

public class LowerIntake extends CommandBase {
  private IntakeSubsystem m_intake;
  /** Creates a new LowerIntake. */
  public LowerIntake(IntakeSubsystem m_intake) {
    this.m_intake = m_intake;

    addRequirements(m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
<<<<<<< HEAD:src/main/java/frc/robot/commands/intake/LowerIntake.java
    m_intake.setAngleState(IntakeAngleState.Down);
    m_intake.setWheelState(intakeWheelState.Stop);
    m_intake.openIntake();
=======
    m_intake.setAngleState(IntakeAngleState.Up);
>>>>>>> 0c90c2504786bed2c4f34a35ece2af59e0c23c0e:src/main/java/frc/robot/commands/intake/ShootCube.java
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
<<<<<<< HEAD:src/main/java/frc/robot/commands/intake/LowerIntake.java
  public void end(boolean interrupted) {}
=======
  public void end(boolean interrupted) {
    m_intake.setWheelState(intakeWheelState.Spit);
    
  }
>>>>>>> 0c90c2504786bed2c4f34a35ece2af59e0c23c0e:src/main/java/frc/robot/commands/intake/ShootCube.java

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
