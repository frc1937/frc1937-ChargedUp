// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PhysicalProperties.BeakConstants;
import frc.robot.Constants.Ports.Beak;

public class BeakSubsystem extends SubsystemBase {
  private CANSparkMax m_beakMotor = new CANSparkMax(Beak.BEAK_MOTOR_PORT, MotorType.kBrushless);
  private RelativeEncoder m_encoder = m_beakMotor.getEncoder();

  private double m_startPos = 0;
  private boolean isOpen = false;
  
  /** Creates a new BeakSubsystem. */
  public BeakSubsystem() {
    m_encoder.setPosition(m_startPos);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Stop the beaks movement
  public void stopMotor() {
    m_beakMotor.stopMotor();
  }

  /* check if the beak is closed
   * @param   isUp - true if the beak is up, false if down
   * @return  true if reached the close pos, otherwise false
   */
  public boolean reachedClosed(boolean isUp) {
    return m_encoder.getPosition() <= m_startPos && isUp;
  }

  /* check if the beak is open
   * @param   isUp - true if the beak is up, false if down
   * @return  true if reached the open pos, otherwise false
   */
  public boolean reachedOpen(boolean isUp) {
    return m_encoder.getPosition() >= BeakConstants.BEAK_MAX_POS && !isUp;
  }
  /* set the speed of the beak motor
   * @params  speed - double value of the speed of the beak motor 
  */
  public void setSpeed(double speed) {
    m_beakMotor.set(speed);
    isOpen = !isOpen;
  }

  // set the value of the isOpen to false if true and true if false
  public void setInverted() {
    m_beakMotor.setInverted(isOpen);
  }

  /* give the value of isOpen
   * @return  the value of isOpen
   */
  public boolean getIsOpen() {
    return isOpen;
  }
}
