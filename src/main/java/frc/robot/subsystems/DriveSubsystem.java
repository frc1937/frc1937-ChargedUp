// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports.*;

public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax left_motor_front = new CANSparkMax(Drive.LEFT_MOTOR_FRONT, MotorType.kBrushless);
  private final CANSparkMax left_motor_rear = new CANSparkMax(Drive.LEFT_MOTOR_REAR, MotorType.kBrushless);
  private final MotorControllerGroup m_left = new MotorControllerGroup(left_motor_front, left_motor_rear);

  private final CANSparkMax right_motor_front = new CANSparkMax(Drive.RIGHT_MOTOR_FRONT, MotorType.kBrushless);
  private final CANSparkMax right_motor_rear = new CANSparkMax(Drive.RIGHT_MOTOR_REAR, MotorType.kBrushless);
  private final MotorControllerGroup m_right = new MotorControllerGroup(right_motor_front, right_motor_rear);

  // differential drive and pigeon
  private final DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  private final WPI_Pigeon2 pigeon = new WPI_Pigeon2(0);

  
  public DriveSubsystem() {
    ResetPigeon();
  }

  @Override
  public void periodic() {}

  /*
   * Move the robot according to variables:
   * @param speed - the speed of the motor (-1 - 1)
   * @param rotation - rotation of the movement (0 - 180)
   */
  public void ArcadeDrive(double speed, double rotatiion) {
    m_drive.arcadeDrive(speed, rotatiion);
  }

  // Stop the motors dead in its tracks
  public void StopMotors() {
    m_drive.stopMotor();
  }

  // Reset the pigeon data
  public void ResetPigeon() {
    pigeon.reset();
  }

  // Get the current Yaw of the pigeon
  public double GetYaw() {
    return pigeon.getYaw();
  }


}
