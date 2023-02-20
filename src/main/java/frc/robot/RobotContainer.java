// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.Ports.*;
import frc.robot.subsystems.BeakSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.CloseBeak;
import frc.robot.commands.CloseIntake;
import frc.robot.commands.MoveBeak;
import frc.robot.commands.OpenBeak;
import frc.robot.commands.OpenIntake;
import frc.robot.commands.ResetIntakeAngle;
import frc.robot.commands.ToggleIntakePistons;
import frc.robot.commands.ToggleLift;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private BeakSubsystem m_beak = new BeakSubsystem();
  private IntakeSubsystem m_intake = new IntakeSubsystem();
  private DriveSubsystem m_drive = new DriveSubsystem();
  private LiftSubsystem m_lift = new LiftSubsystem();
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Controllers.DRIVER_CONTROLLER);
  private final Trigger xButton = m_driverController.x();
  private final Trigger bButton = m_driverController.b();
  private final Trigger yButton = m_driverController.y();
  private final Trigger aButton = m_driverController.a();
  private final Trigger rbButton = m_driverController.rightBumper();
  private final Trigger rtButton = m_driverController.rightTrigger();
  private final Trigger lbButton = m_driverController.leftBumper();
  private final Trigger ltButton = m_driverController.leftTrigger();

  private final Command autoBeakCloseCommand = new SequentialCommandGroup(
    new CloseBeak(m_beak),
    new MoveBeak(m_beak));

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_drive.setDefaultCommand(new ArcadeDrive(m_driverController, m_drive));

    rtButton.whileTrue(new OpenIntake(m_intake));
    rbButton.onTrue(new CloseIntake(m_intake));
    lbButton.onTrue(autoBeakCloseCommand);
    ltButton.onTrue(new OpenBeak(m_beak));
    xButton.onTrue(new ToggleIntakePistons(m_intake));
  }


  public void teleopInit() {
    new ResetIntakeAngle(m_intake).schedule();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
