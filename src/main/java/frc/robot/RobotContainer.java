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

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.Ports.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.intake.*;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private IntakeSubsystem m_intake = new IntakeSubsystem();
  private DriveSubsystem m_drive = new DriveSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(Controllers.DRIVER_CONTROLLER);
  private final CommandJoystick m_opController = new CommandJoystick(1);

  /** All options for autonomus */
  private SendableChooser<String> m_chooser = new SendableChooser<>();
  private final String m_defaultRoute = "Line";
  private final String m_itemDefaultRoute = "Grid Line";
  private final String m_itemComplexRoute = "Ramp Grid Line";
  private String m_selected;

  private SendableChooser<String> m_itemChooser = new SendableChooser<>();
  private final String m_cone = "Cone";
  private final String m_cube = "Cube";
  private  String m_selectedItem;

  /** All needed buttons on the robot */
  private final Trigger rbButton = m_driverController.rightBumper();
  private final Trigger rtButton = m_driverController.rightTrigger();
  private final Trigger ltButton = m_driverController.leftTrigger();
  private final Trigger aButton = m_driverController.a();
  private final Trigger yButton = m_driverController.b();
  private final Trigger xButton = m_driverController.x();


  private final Trigger J1Button =  m_opController.button(1);
  private final Trigger J2Button = m_opController.button(2);
  private final Trigger j3Button = m_opController.button(3);
  private final Trigger j4Button = m_opController.button(4);
  private final Trigger j8Button = m_opController.button(8);
  private final Trigger j7Button = m_opController.button(7);
  private final Trigger j9Button = m_opController.button(9);
  private final Trigger j10Button = m_opController.button(10);
  private final Trigger j11Button = m_opController.button(11);
  private final Trigger POVdown = m_opController.povDown();

  /** Open the lift and track simultaneously */
  

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

    rtButton.onTrue(new ToggleOpenIntake(m_intake));
    rbButton.onTrue(new CloseIntake(m_intake));
    aButton.whileTrue(new S]\
    
    hootCube(m_intake));
    xButton.onTrue(new CloseItakePistol(m_intake));
    yButton.whileTrue(new EjectObject(m_intake));


    
  }


  public void teleopInit() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_selected = m_chooser.getSelected();
    Command autonomusCommand = null;

    /** The paths we wish to follow */
    HashMap<String, Command> eventMap = new HashMap<>();
    
    /** The path following program */
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
      true, 
      m_drive
    );

    /** The autonomus route according to the selected on in the smartdashboard */
   
    
    return autonomusCommand;
  }

  /** Happens once uppon the robot being disabled */
  public void disabledInit() {
    m_intake.stopIntakeWheel();
    m_intake.stopAngle();
  }

  /**
   * Make command to move autonomuslly from the community to the ramp.
   * @param eventMap      the event map that includes all events.
   * @param autoBuilder   the auto builder to make the command accorrding to the path.
   * @return              the command that needs to be run to complete the stated path.
   */
  public Command commToRamp(HashMap<String,Command> eventMap, RamseteAutoBuilder autoBuilder) {
    List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(
      "CommToRamp", new PathConstraints(3.25, 1.75));
    return autoBuilder.fullAuto(pathGroup);
  }

  /**
   * Make command to move autonomuslly from the grid to the Community.
   * @param eventMap      the event map that includes all events.
   * @param autoBuilder   the auto builder to make the command accorrding to the path.
   * @return              the command that needs to be run to complete the stated path.
   */
  public Command defaultRoute(HashMap<String,Command> eventMap, RamseteAutoBuilder autoBuilder) {
    List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(
      "DefaultRoute", new PathConstraints(3.25, 1.75));
    return autoBuilder.fullAuto(pathGroup);
  }

 

  /** Happens once upon the codes being deployed */
  public void robotInit() {
    m_chooser.setDefaultOption(m_defaultRoute, m_defaultRoute);
    m_chooser.addOption(m_itemDefaultRoute, m_itemDefaultRoute);
    m_chooser.addOption(m_itemComplexRoute, m_itemComplexRoute);
    SmartDashboard.putData("Autonomus options", m_chooser);

    m_itemChooser.setDefaultOption(m_cone, m_cone);
    m_itemChooser.addOption(m_cube, m_cube);
    SmartDashboard.putData("Item", m_itemChooser);
    
    CameraServer.startAutomaticCapture();
  }
}
