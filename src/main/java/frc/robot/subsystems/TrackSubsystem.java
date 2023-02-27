// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TrackConstants;
import frc.robot.Constants.Ports.Track;

public class TrackSubsystem extends SubsystemBase {
  private final TalonSRX m_trackMotor = new TalonSRX(Track.TRACK_MOTOR);
  private final DoubleSolenoid m_trackPiston = new DoubleSolenoid(
    PneumaticsModuleType.CTREPCM, Track.OPEN_TRACK_SOLENOID, Track.CLOSE_TRACK_SOLENOID);
  private boolean isOpen = false;
  
  /** Creates a new TrackSubsystem. */
  public TrackSubsystem() {
    m_trackMotor.config_kP(0, TrackConstants.K_P);
    m_trackMotor.config_kD(0, 0);
    m_trackMotor.config_kI(0, 0);
    m_trackMotor.configPeakOutputForward(0.6);
    m_trackMotor.configPeakOutputReverse(-0.6);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Close the piston
  public void closePiston() {
    isOpen = false;
    m_trackPiston.set(Value.kReverse);
  }

  // Open the piston
  public void openPiston() {
    isOpen = true;
    m_trackPiston.set(Value.kForward);
  }

  // Start the movement of the track using @param speed which is the speed of the track [-1 - 1].
  public void setSpeed(double speed) {
    m_trackMotor.set(ControlMode.PercentOutput, speed);
  }

  // Stops the movement of the track
  public void stopMotor() {
    m_trackMotor.set(ControlMode.Disabled, 0);
  }

  // Check if the track has hit the micro switch at maximum position
  public boolean reachedMaxSwitch() {
    return m_trackMotor.isFwdLimitSwitchClosed() == 1;
  }

  // Check if the track has hit the micro switch at max position
  public boolean reachedMinSwitch() {
    return m_trackMotor.isRevLimitSwitchClosed() == 1;
  }

  // Check if the motor has arrived in its maximum position
  // @return  true if the motor has passed the position and false if it has yet arrived.
  public boolean reachedMaxPos() {
    return m_trackMotor.getSelectedSensorPosition() >= TrackConstants.MAXIMUM_MOTOR_POS;
  }

  /**
   * Check whether the track is opened or closed
   * @return true if the track is open
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Move the track in its position
   * @param position  the target position the motor will arrive to
   */
  public void setPosition(double position) {
    m_trackMotor.set(ControlMode.Position, position);
  }

  public double getPosition() {
    return m_trackMotor.getSelectedSensorPosition();
  }

  public void resetEncoder() {
    m_trackMotor.setSelectedSensorPosition(0);
  }
}
