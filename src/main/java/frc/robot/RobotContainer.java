// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.subsystems.BeakSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.Ports.*;
import frc.robot.ElysiumLib.LogView;
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
  private LiftSubsystem m_lift = new LiftSubsystem(m_intake);
  private TrackSubsystem m_track = new TrackSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(Controllers.DRIVER_CONTROLLER);
  private final CommandJoystick m_opController = new CommandJoystick(1);

  /** All options for autonomus */
  private SendableChooser<String> m_chooser = new SendableChooser<>();
  private final String m_defaultRoute = "Exit line";
  private final String m_itemComplexRoute = "Stabilize ramp and put in grid and exit Line";
  private final String m_cube3OutStable = "Put cube in 3 level and stabilize ramp";
  private final String m_coneOut = "cone 2 Out";
  private final String m_cubeOut = "cube 2 Out";
  private final String m_cubeOut3 = "cube 3 Out";

  private String m_selected;
  private Timer timer = new Timer();

  /** All needed buttons on the robot */
  private final Trigger rbButton = m_driverController.rightBumper();
  private final Trigger rtButton = m_driverController.rightTrigger();
  private final Trigger ltButton = m_driverController.leftTrigger();
  private final Trigger lbButton = m_driverController.leftBumper();
  private final Trigger aButton = m_driverController.a();
  private final Trigger bButton = m_driverController.b();
  private final Trigger yButton = m_driverController.y();
  private final Trigger xButton = m_driverController.x();

  private final Trigger J1Button =  m_opController.button(1);
  private final Trigger J2Button = m_opController.button(2);
  private final Trigger j3Button = m_opController.button(3);
  private final Trigger j4Button = m_opController.button(4);
  private final Trigger j8Button = m_opController.button(8);
  private final Trigger j7Button = m_opController.button(7);
  private final Trigger j9Button = m_opController.button(9);
  private final Trigger j11Button = m_opController.button(11);
  private final Trigger POVdown = m_opController.povDown();

  UsbCamera intakeCam;
  UsbCamera headCam;
  VideoSink server;
  private LogView m_view;

  /** Open the lift and track simultaneously */
  private final Command OpenLiftTrack = new OpenIntakePistons(m_intake).alongWith(
    new OpenLift(m_lift)).alongWith(new OpenTrack(m_track));
  

  private final Command closeCubeCommand = new CloseCube(m_beak).alongWith(new RaiseCube(m_intake).withTimeout(0.5));

  /** Close the lift and the track simultaneously */
  private final Command CloseLiftTrack = new CloseLift(m_lift).alongWith(
    new CloseTrack(m_track).alongWith(new OpenIntakePistons(m_intake)));

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

    //rtButton.onTrue(new ToggleOpenIntake(m_intake)); //Button for without vision
    //rbButton.onTrue(new CloseIntake(m_intake)); //Button for without vision
    rtButton.whileTrue(new AutoOpenIntake(m_intake));
    rtButton.onFalse(new WaitCommand(0.4).andThen(new CloseIntake(m_intake)));
    rbButton.whileTrue(new AlignToGamePiece(m_drive));
    ltButton.onTrue(new OpenBeak(m_beak));
    lbButton.onTrue(new CloseCube(m_beak));
    aButton.onTrue(new ShootCubeMiddle(m_intake,m_lift));
    bButton.whileTrue(new SucCone(m_intake, m_lift));
    yButton.onTrue(new ShootCubeTop(m_intake,m_lift));
    xButton.whileTrue(new LowerIntake(m_intake));
    xButton.onFalse(new CloseIntake(m_intake));

    J1Button.onTrue(OpenLiftTrack);
    J2Button.onTrue(CloseLiftTrack);
    j3Button.whileTrue(new ConeLeft(m_intake));
    j4Button.whileTrue(new ConeRight(m_intake));
    j7Button.onTrue(closeCubeCommand);
    j8Button.onTrue(new CloseCone(m_beak));
    j9Button.onTrue(new OpenBeak(m_beak));
    POVdown.whileTrue(new SlowIntake(m_intake));
    j11Button.onTrue(new ToggleSwitchActive(m_intake));
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

    //new SimpleMotorFeedforward(0.30226, 2.6, 0.20779),
    //new PIDConstants(3, 0, 0),

    /** The autonomus route according to the selected on in the smartdashboard */
    switch (m_selected) {
      case m_defaultRoute :
        autonomusCommand = new DriveM(m_drive, 4);
        break;
      case m_itemComplexRoute:
        autonomusCommand = new CloseCone(m_beak)
        .andThen(new WaitCommand(1.5))
        .andThen(OpenLiftTrack).withTimeout(3)
        .andThen(new WaitCommand(0.8))
        .andThen(new OpenBeak(m_beak))
        .andThen(new WaitCommand(0.8))
        .andThen(new DriveM(m_drive, -0.5))
        .andThen(CloseLiftTrack)
        .withTimeout(7.5)
        .andThen(new DriveM(m_drive, -1.5))
        .andThen(new RampBalance(m_drive));
        break;
      case m_cube3OutStable:
        autonomusCommand = new ShootCubeTop(m_intake, m_lift)
        .andThen(new WaitCommand(1))
        .andThen(new DriveM(m_drive, -1.35))
        .andThen(new RampBalance(m_drive));
        break;
      case m_coneOut:
        autonomusCommand = new CloseCone(m_beak)
        .andThen(new WaitCommand(1.5))
        .andThen(OpenLiftTrack).withTimeout(3)
        .andThen(new WaitCommand(0.8))
        .andThen(new OpenBeak(m_beak))
        .andThen(new WaitCommand(0.8))
        .andThen(new DriveM(m_drive, -0.5))
        .andThen(CloseLiftTrack)
        .withTimeout(7.5)
        .andThen(new DriveM(m_drive, -3));
        break;

      case m_cubeOut:
        autonomusCommand = new ShootCubeMiddle(m_intake, m_lift)
        .andThen(new WaitCommand(0.8))
        .andThen(new DriveM(m_drive, -3.75));
        break;
      case m_cubeOut3:
      autonomusCommand = new ShootCubeTop(m_intake, m_lift)
        .andThen(new WaitCommand(1))
        .andThen(new DriveM(m_drive, -3.75));
        break;
    }
    
    return autonomusCommand;
  }

  /** Happens once uppon the robot being disabled */
  public void disabledInit() {
    m_intake.stopIntakeWheel();
    m_intake.stopAngle();
    m_lift.stopMotor();
  }

  /** Happens once upon the codes being deployed */
  public void robotInit() {
    m_chooser.setDefaultOption(m_defaultRoute, m_defaultRoute);
    m_chooser.addOption(m_itemComplexRoute, m_itemComplexRoute);
    m_chooser.addOption(m_cube3OutStable, m_cube3OutStable);
    m_chooser.addOption(m_coneOut, m_coneOut);
    m_chooser.addOption(m_cubeOut, m_cubeOut);
    m_chooser.addOption(m_cubeOut3, m_cubeOut3);

    SmartDashboard.putData("Autonomus options", m_chooser);
    
    headCam = CameraServer.startAutomaticCapture(0);
    headCam.setResolution(320, 240);
    headCam.setFPS(30);
    headCam.setPixelFormat(VideoMode.PixelFormat.kY16);
    server = CameraServer.getServer();

    m_view = new LogView(m_drive.getLeftData(), m_drive.getRightData());
    m_view.start();
    timer.start();
  }

  public void robotPeriodic() {
    if (timer.get() >= 1) {
      m_view.update();
      timer.reset();
    }
  }
}
