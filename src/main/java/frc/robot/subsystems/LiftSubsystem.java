// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Ports;

public class LiftSubsystem extends SubsystemBase {
  private WPI_TalonFX m_liftMotor = new WPI_TalonFX(Ports.Lift.DOWN_MOTOR);
  private DigitalInput m_switch = new DigitalInput(Ports.Lift.SWITCH);
  private boolean isToggleLift = false;
  
  /** Creates a new LiftSubsystem. */
  public LiftSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //moves motor
  public void startMotor(double speed){
    m_liftMotor.set(speed);
  }

  //stops motor 
  public void stopMotor(){
    m_liftMotor.stopMotor();
  }

   // Check if the lift has hit the micro switch at min position
   public boolean reachedMinSwitch() {
    return m_switch.get();
  }

  ///chek if  lift is in the micro switch at min position
  public boolean reachedMaxPossion(){
    return m_liftMotor.getSelectedSensorPosition() >= Constants.LiftConstants.MAXIMUM_ENCODER_POSITION;
  }

  //get if up or down
  public boolean isToggleLift(){
    return this.isToggleLift;
  }

  //set up or down
  public void setIsToggleLift(){
    isToggleLift = !isToggleLift;
  }
}
