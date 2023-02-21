// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.Ports.Intake;

public class IntakeSubsystem extends SubsystemBase {
  final static TalonSRX m_angleMotor = new TalonSRX(Intake.INTAKE_ANGLE_MOTOR);
  final static TalonSRX m_leftMotor = new TalonSRX(Intake.LEFT_INTAKE_MOTOR);
  private final TalonSRX m_rightMotor = new TalonSRX(Intake.RIGHT_INTAKE_MOTOR);
  private final DoubleSolenoid m_intakePistons = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Intake.OPEN_PISTONS, Intake.CLOSE_PISTONS);
  private boolean isUp = true;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    m_leftMotor.setInverted(true);
    m_rightMotor.setInverted(false);
    m_angleMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("asdasd", isUp);
    // This method will be called once per scheduler run
  }
  
  // Set the intake angle motor position to given value [revolutions].
  public void setIntakePosition(double position) {
    m_angleMotor.set(ControlMode.Position, position);
  }

  // Open the intake pistons
  public void closeIntake() {
    m_intakePistons.set(Value.kReverse);
  }

  /**
   * Close the intake pistons
   */
  public void openIntake() {
    m_intakePistons.set(Value.kForward);
  }

  /**
   * @param speed - A value that represents the speed of the motors [-1 - 1]
   */
  public void setIntakeWheelSpeed(double speed) {
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, -speed);
  }

  /**
   * @param speed - A value that represents the speed of the motors [-1 - 1]
   */
  public void setIntakeWheelSpeedOposing(double speed) {
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, speed);
  }
  
  /**
   * Go to a position based on the slot of the PID like specified below
   * @param slotIDX - the PID slot needed (0 is for closing and 1 for opening)
   */
  public void movePID(int slotIDX) {
    m_angleMotor.selectProfileSlot(slotIDX, 0);
    double targetPosition = slotIDX == 1 ? IntakeConstants.MAXIMUM_POSITION : IntakeConstants.MINIMUM_POSITION;
    isUp = !isUp;
    m_angleMotor.set(ControlMode.Position, targetPosition);
  }

  // Stop angle motor
  public void stopAngle() {
    m_angleMotor.set(ControlMode.Disabled, 0);
  }

  // Stop the intake wheel motors
  public void stopIntakeWheel() {
    m_leftMotor.set(ControlMode.Disabled, 0);
    m_rightMotor.set(ControlMode.Disabled, 0);
  }

   // Toggle the intake pistons
  public void togglePistons() {
    m_intakePistons.set(m_intakePistons.get() == Value.kForward ? Value.kReverse : Value.kForward);;
  }

  /**
   * Set the speed of the angle motor
   * @param speed
   */
  public void setAngleSpeed(double speed){
    m_angleMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Get whether the angle limit switch is activated
   * @return  true if the limit switch is pressed.
   */
  public boolean getSwitch(){
    return m_angleMotor.isRevLimitSwitchClosed() == 1;
  }

  /**
   * Get whether the lift limit switch is activated
   * @return  true if the limit switch is pressed.
   * @return  false if the limit switch is open
   */
  public boolean getLiftSwitch(){
    return m_angleMotor.isRevLimitSwitchClosed() == 1;
  }

  /**
   * Get the value of the angle position
   * @return  true if the angle is up
   */
  public boolean currentlyUp() {
    return isUp;
  }

  public void resetEncoder() {
    m_angleMotor.setSelectedSensorPosition(0);
  }

  public void setIsUp(boolean isIntakeUp) {
    isUp = isIntakeUp;
  }

  public boolean getIsUp() {
    return isUp;
  }
}
