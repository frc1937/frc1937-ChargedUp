// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports.*;

public class DriveSubsystem extends SubsystemBase {
  private CANSparkMax m_frontLeftMotor = new CANSparkMax(Drive.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_rearLeftMotor = new CANSparkMax(Drive.REAR_LEFT_MOTOR, MotorType.kBrushless);
  private MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeftMotor, m_rearLeftMotor);

  private CANSparkMax m_frontRightMotor = new CANSparkMax(Drive.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax m_rearRightMotor = new CANSparkMax(Drive.REAR_RIGHT_MOTOR, MotorType.kBrushless);
  private MotorControllerGroup m_right = new MotorControllerGroup(m_frontRightMotor, m_rearRightMotor);

  private DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  
  public DriveSubsystem() {
    // Sets the motors as inverted since wpi does not do it already ):
    m_frontRightMotor.setInverted(true);
    m_rearRightMotor.setInverted(true);
  }

  @Override
  public void periodic() {}

  /*
   * Drive the robot by controlling the speed and rotation:
   * @param speed the speed of movement. In range [-1, 1].
   *  Positive values move the robot forwards and negative values move it backwards.
   * @param rotation - rotation of the movement (-180 - 180)
   *  positive turns the robot right whilst negetive left.
   */
  public void ArcadeDrive(double speed, double rotatiion) {
    m_drive.arcadeDrive(speed, rotatiion);
  }

  // Stop the motors on the robot
  public void StopMotor() {
    m_drive.stopMotor();
  }
}
