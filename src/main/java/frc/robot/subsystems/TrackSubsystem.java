// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalProperties.TrackConstants;
import frc.robot.Constants.Ports.Track;

public class TrackSubsystem extends SubsystemBase {
  private final TalonSRX m_trackMotor = new TalonSRX(Track.TRACK_MOTOR);
  private final DoubleSolenoid m_trackPiston = new DoubleSolenoid(
    PneumaticsModuleType.CTREPCM, Track.OPEN_TRACK_SOLENOID, Track.CLOSE_TRACK_SOLENOID);
  private final Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);

  // Is the track closed or opened
  private boolean trackActive = false;
  
  /** Creates a new TrackSubsystem. */
  public TrackSubsystem() {
    m_trackMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    m_trackPiston.set(Value.kReverse);
    m_compressor.disable();
    m_trackMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Position : ", m_trackMotor.getSelectedSensorPosition());
  }

  // Toggle the track piston
  public void togglePiston() {
    m_trackPiston.toggle();
    trackActive = !trackActive;
  }

  public void closePiston() {
    m_trackPiston.set(Value.kReverse);
  }

  // Start the movement of the track using @param speed which is the speed of the track [-1 - 1].
  public void setSpeed(double speed) {
    m_trackMotor.set(ControlMode.PercentOutput, speed);
  }

  // Stop the movement of the track
  public void stopMotor() {
    m_trackMotor.set(ControlMode.Disabled, 0);
  }

  // Check if the track has hit the micro switch at max position
  public boolean reachedMaxSwitch() {
    return m_trackMotor.isFwdLimitSwitchClosed() == 1;
  }

  // Check if the track has hit the micro switch at max position
  public boolean reachedMinSwitch() {
    return m_trackMotor.isRevLimitSwitchClosed() == 1;
  }

  // Check if the motor has arrived in its max position
  // @return  true if the motor has passed the position and false if it has yet arrived.
  public boolean reachedMaxPos() {
    return m_trackMotor.getSelectedSensorPosition() >= TrackConstants.MAX_MOTOR_POS;
  }

  // Check if the track is opened or closed and return that value
  public boolean isTrackActive() {
    return trackActive;
  }
}
