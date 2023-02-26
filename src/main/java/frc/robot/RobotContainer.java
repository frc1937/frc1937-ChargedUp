// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.List;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.RamseteAutoBuilder;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.BeakSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.Ports.*;
import frc.robot.commands.beak.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.lift.*;
import frc.robot.commands.track.*;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.TrackSubsystem;

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
  private TrackSubsystem m_track = new TrackSubsystem();
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(Controllers.DRIVER_CONTROLLER);
  private final CommandJoystick m_opController = new CommandJoystick(1);

  private final Trigger xButton = m_driverController.x();
  private final Trigger bButton = m_driverController.b();
  private final Trigger yButton = m_driverController.y();
  private final Trigger aButton = m_driverController.a();
  private final Trigger rbButton = m_driverController.rightBumper();
  private final Trigger rtButton = m_driverController.rightTrigger();
  private final Trigger lbButton = m_driverController.leftBumper();
  private final Trigger ltButton = m_driverController.leftTrigger();

  private final Trigger J1Button =  m_opController.button(1);
  private final Trigger J2Button = m_opController.button(2);
  private final Trigger j3Button = m_opController.button(3);
  private final Trigger j4Button = m_opController.button(4);
  private final Trigger j8Button = m_opController.button(8);
  private final Trigger j7Button = m_opController.button(7);
  private final Trigger j9Button = m_opController.button(9);
  private final Trigger j10Button = m_opController.button(10);


  private final Command autoBeakCloseCommand = new SequentialCommandGroup(
    new CloseBeak(m_beak),
    new MoveBeak(m_beak));

  /** Open the lift and track simultaneously */
  private final Command OpenLiftTrack = new OpenLift(m_lift).alongWith(
    new OpenTrack(m_track)).alongWith(new ToggleIntakePistons(m_intake));

  /** Close the lift and the track simultaneously */
  private final Command CloseLiftTrack = new CloseLift(m_lift).alongWith(
    new CloseTrack(m_track)).alongWith(new OpenIntakePistons(m_intake));

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

    rtButton.onTrue(new ToggleOpenIntake(m_intake,m_lift.getLiftIsDown()));
    rbButton.onTrue(new CloseIntake(m_intake));
    ltButton.onTrue(new OpenBeak(m_beak));


    J1Button.onTrue(OpenLiftTrack);
    J2Button.onTrue(CloseLiftTrack);
    j8Button.onTrue(new CloseCone(m_beak));
    j7Button.onTrue(new CloseCube(m_beak));
    j4Button.whileTrue(new ConeLeft(m_intake));
    j3Button.whileTrue(new ConeRight(m_intake));
    j9Button.onTrue(new OpenBeak(m_beak));
    j10Button.whileTrue(new EjectObject(m_intake));
  }


  public void teleopInit() {
    // new ResetTrack(m_track).schedule();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup("Cheeky Path", new PathConstraints(3.25, 1.75));

    HashMap<String, Command> eventMap = new HashMap<>();
    
    RamseteAutoBuilder autoBuilder = new RamseteAutoBuilder(
      m_drive::getPoseMetres,
      m_drive::resetPoseMetres,
      new RamseteController(),
      DriveSubsystem.KINEMATICS,
      new SimpleMotorFeedforward(0.30226, 2.6, 0.20779),
      m_drive::getSpeeds,
      new PIDConstants(3, 0, 0),
      m_drive::setVoltage,
      eventMap,
      false, // TODO change me!
      m_drive
    );
    
    return autoBuilder.fullAuto(pathGroup);
  }

  public void disabledInit() {
    m_intake.stopIntakeWheel();
    m_intake.stopAngle();
    m_track.stopMotor();
    m_lift.stopMotor();
  }

  
}
