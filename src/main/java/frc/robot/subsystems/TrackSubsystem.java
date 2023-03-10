// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TrackConstants;
import frc.robot.Constants.Ports.Track;

public class TrackSubsystem extends SubsystemBase {
  private final DoubleSolenoid m_trackPiston = new DoubleSolenoid(
      PneumaticsModuleType.CTREPCM, Track.OPEN_TRACK_SOLENOID, Track.CLOSE_TRACK_SOLENOID);
  private boolean isOpen = false;

  /** Creates a new TrackSubsystem. */
  public TrackSubsystem() {

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


  /**
   * Check whether the track is opened or closed
   * 
   * @return true if the track is open
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Move the track in its position
   * 
   * @param position the target position the motor will arrive to
   */
}
