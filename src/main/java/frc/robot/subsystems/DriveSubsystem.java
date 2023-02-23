// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Ports.Drive;

/** The drive subsystem for controlling the track */
public class DriveSubsystem extends SubsystemBase {
  private CANSparkMax m_frontLeftMotor = new CANSparkMax(Drive.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_rearLeftMotor = new CANSparkMax(Drive.REAR_LEFT_MOTOR, MotorType.kBrushless);
  private MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeftMotor, m_rearLeftMotor);
  private CANSparkMax m_frontRightMotor = new CANSparkMax(Drive.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_rearRightMotor = new CANSparkMax(Drive.REAR_RIGHT_MOTOR, MotorType.kBrushless);
  private MotorControllerGroup m_right = new MotorControllerGroup(m_frontRightMotor, m_rearRightMotor);
  private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  

  public DriveSubsystem() {
    m_frontLeftMotor.setIdleMode(IdleMode.kBrake);
    m_frontRightMotor.setIdleMode(IdleMode.kBrake);
    m_rearLeftMotor.setIdleMode(IdleMode.kBrake);
    m_rearRightMotor.setIdleMode(IdleMode.kBrake);
    // Invert the direction of all the motors
    m_frontRightMotor.setInverted(false);
    m_rearRightMotor.setInverted(false);
    m_rearLeftMotor.setInverted(true);
    m_frontLeftMotor.setInverted(true);

    m_drive.setSafetyEnabled(false);
    m_drive.feed();
  }

  @Override
  public void periodic() {}

  /**
   * Drive the robot by controlling the speed and rotation
   * 
   * @param speed the speed of movement. In range [-1, 1], Positive values move the robot forwards,
   * negative values move it backwards. @param rotation - rotation of the movement (-180 - 180) positive 
   * turns the robot right whilst negetive left.
   */
  public void arcadeDrive(double speed, double rotatiion) {
    m_drive.arcadeDrive(speed, rotatiion);
  }

  // Stop the motors on the robot
  public void stopMotor() {
    m_drive.stopMotor();
  }
}
