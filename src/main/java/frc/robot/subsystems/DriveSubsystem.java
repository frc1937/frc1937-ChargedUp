// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.Ports;

/** The drive subsystem for controlling the track */
public class DriveSubsystem extends SubsystemBase {
  private CANSparkMax m_frontLeftMotor = new CANSparkMax(Ports.Drive.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_rearLeftMotor = new CANSparkMax(Ports.Drive.REAR_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_frontRightMotor = new CANSparkMax(Ports.Drive.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_rearRightMotor = new CANSparkMax(Ports.Drive.REAR_RIGHT_MOTOR, MotorType.kBrushless);
  private MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeftMotor, m_rearLeftMotor);
  private MotorControllerGroup m_right = new MotorControllerGroup(m_frontRightMotor, m_rearRightMotor);
  private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

  private WPI_PigeonIMU m_gyro = new WPI_PigeonIMU(Ports.Drive.PIGEON_IMU);
  //private SlewRateLimiter m_filter = new SlewRateLimiter(0.25);

  private DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
    m_gyro.getRotation2d(),
    getLeftTravelDistanceMetres(),
    getRightTravelDistanceMetres()
  );

  public static final DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(0.44);

  public DriveSubsystem() {
    m_frontLeftMotor.restoreFactoryDefaults();
    m_frontRightMotor.restoreFactoryDefaults();
    m_rearLeftMotor.restoreFactoryDefaults();
    m_rearRightMotor.restoreFactoryDefaults();

    m_frontLeftMotor.setIdleMode(IdleMode.kBrake);
    m_frontRightMotor.setIdleMode(IdleMode.kBrake);
    m_rearLeftMotor.setIdleMode(IdleMode.kBrake);
    m_rearRightMotor.setIdleMode(IdleMode.kBrake);

    m_right.setInverted(true);
    m_left.setInverted(false);



    m_frontLeftMotor.getEncoder().setPositionConversionFactor((1.0 / 42));
    m_frontRightMotor.getEncoder().setPositionConversionFactor((1.0 / 42));
    m_frontLeftMotor.getEncoder().setVelocityConversionFactor((1.0 / 42));
    m_frontRightMotor.getEncoder().setVelocityConversionFactor((1.0 / 42));

    m_frontLeftMotor.getEncoder().setPosition(0);
    m_frontRightMotor.getEncoder().setPosition(0);

    m_frontLeftMotor.setClosedLoopRampRate(0.5);
    m_rearLeftMotor.setClosedLoopRampRate(0.5);
    m_frontRightMotor.setClosedLoopRampRate(0.5);
    m_rearRightMotor.setClosedLoopRampRate(0.5);

    m_drive.setSafetyEnabled(false);
    m_drive.feed();
    m_gyro.reset();
    m_gyro.setYaw(0);
    
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("orientation", m_gyro.getAngle() % 360);
    SmartDashboard.putNumber("speed angle", getLeftTravelDistanceMetres());
    SmartDashboard.putNumber("Angle", getRightTravelDistanceMetres());

    m_odometry.update(
      m_gyro.getRotation2d(),
      getLeftTravelDistanceMetres(),
      getRightTravelDistanceMetres()
    );
  }

  /**
   * Drive the robot by controlling the speed and rotation
   * 
   * @param speed the speed of movement. In range [-1, 1]. Positive values move the robot forwards
   * and negative values move it backwards.
   * @param rotation the value, according to which the robot will turn. In range [-1, 1]. Positive
   * values mean clockwise rotation.
   */
  public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(speed, rotation);
  }

  public void stopMotor() {
    m_drive.stopMotor();
  }

  /**
   * Reset the odometry to a known pose.
   * 
   * This is highly relevant at the start of a match.
   * 
   * @param poseMetres the new pose estimate of the robot.
   */
  public void resetPoseMetres(Pose2d poseMetres) {
    m_odometry.resetPosition(
      m_gyro.getRotation2d(),
      getRightTravelDistanceMetres(),
      getLeftTravelDistanceMetres(),
      poseMetres
    );
  }

  public void resetpos() {
    m_frontLeftMotor.getEncoder().setPosition(0);
  }

  /**
   * @return the total distance in metres the left side of the robot traveled since the last
   * encoder reset
   */
  public double getLeftTravelDistanceMetres() {
    return m_frontLeftMotor.getEncoder().getPosition() * Units.inchesToMeters(6) * Math.PI * 10.71 / 2;
  }

  /**
   * @return the total distance in metres the right side of the robot traveled since the last
   * encoder reset
   */
  public double getRightTravelDistanceMetres() {
    return - m_frontRightMotor.getEncoder().getPosition() * Units.inchesToMeters(6) * Math.PI * 10.71 / 2;
  }

  /**
   * @return the total velocity in metres per second the left side of the robot traveled since the
   * last encoder reset
   */
  public double getLeftTravelVelocityMetresPerSecond() {
    return m_frontLeftMotor.getEncoder().getVelocity() / 60 * Units.inchesToMeters(6) * Math.PI * 10.71 / 2;
  }

  /**
   * @return the total velocity in metres per second the right side of the robot traveled since the
   * last encoder reset
   */
  public double getRightTravelVelocityMetresPerSecond() {
    return - m_frontRightMotor.getEncoder().getVelocity() / 60 * Units.inchesToMeters(6) * Math.PI * 10.71 / 2;
  }

  /**
   * @return the speed in each side represented by differential drive
   */  
  public DifferentialDriveWheelSpeeds getSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftTravelVelocityMetresPerSecond(), getRightTravelVelocityMetresPerSecond());
  }

  /**
   * Set the speed of the of the motors according to the voltage
   * @param left    the voltage in the left side
   * @param right   the voltage in the right side
   */
  public void setVoltage(double left, double right) {
    m_left.setVoltage(left / 12);
    m_right.setVoltage(right / 12);
  }

  /**
   * @return the current pose estimate of the robot in metres, in field coordinate.
   */
  public Pose2d getPoseMetres() {
    return m_odometry.getPoseMeters();
  }

  public void resetYaw() {
    m_gyro.setYaw(0);
  }

  public double getYaw() {
    return m_gyro.getYaw();
  }

public WPI_PigeonIMU getGyro() {
    return m_gyro;
}
}
