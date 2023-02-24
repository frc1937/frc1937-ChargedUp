// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AmitHot extends PIDCommand {
  /** Creates a new DriveTemp. */
  public AmitHot(DriveSubsystem m_drive) {
    super(
        // The controller that the command will use
        new PIDController(0.14 ,0, 0.02),
        // This should return the measurement
        () -> m_drive.getLeftTravelDistanceMetres(),
        // This should return the setpoint (can also be a constant)
        () -> 2,
        // This uses the output
        output -> {
          m_drive.setVolts(output, output);
          // Use the output here
        });
      addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}