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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.Ports.Intake;

/** The intake subsystem for controlling the intake angle and wheels */
public class IntakeSubsystem extends SubsystemBase {
  final static TalonSRX m_angleMotor = new TalonSRX(Intake.INTAKE_ANGLE_MOTOR);
  final static TalonSRX m_leftMotor = new TalonSRX(Intake.LEFT_INTAKE_MOTOR);
  private final TalonSRX m_rightMotor = new TalonSRX(Intake.RIGHT_INTAKE_MOTOR);
  private final DoubleSolenoid m_intakePistons = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Intake.OPEN_PISTONS, Intake.CLOSE_PISTONS);
  private boolean isUp = true;
  public enum intakeWheelState {
    Stop("Stop"), In("In"), Out("Out"), Right("Right"), Left("Left"),
    Slow("Inly"), Outly("Outly");
    String name;
    private intakeWheelState(String name) {
      this.name = name;
    }
  }
  public enum IntakeAngleState {
    Up("Up"), Down("Down"), Middle("Middle");
    String name;
    private IntakeAngleState(String name) {
      this.name = name;
    }
  }
  private intakeWheelState intakeState;
  private IntakeAngleState intakeAngleState;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    this.intakeState = intakeWheelState.Stop;
    this.intakeAngleState = IntakeAngleState.Up;
    
    m_leftMotor.setInverted(true);
    m_rightMotor.setInverted(false);
    m_angleMotor.setNeutralMode(NeutralMode.Brake);
    
    m_angleMotor.configPeakOutputForward(0.6);
    m_angleMotor.configPeakOutputReverse(-0.6);
    m_angleMotor.config_kF(0, IntakeConstants.ANGLE_KF_0);
    m_angleMotor.config_kP(0, IntakeConstants.ANGLE_KP_0);

    m_angleMotor.config_kF(1, IntakeConstants.ANGLE_KF_1);
    m_angleMotor.config_kP(1, IntakeConstants.ANGLE_KP_1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setWheelMotorToState();
    setAngleMotorToState();
  }

  /**
   * Get the current state of the inatke wheels
   * @return  The current state of the intake wheels, Out, In, Right, Left, Stop.
   */
  public intakeWheelState getState() {
    return intakeState;
  }
  
  /** Set the wheel speed according to the state the enunm states */
  private void setWheelMotorToState() {
    switch (intakeState) {
      case Stop:
        stopIntakeWheel();
        break;
      case In:
        setIntakeWheelSpeed(IntakeConstants.INTAKE_WHEEL_SPEED);
        break;
      case Left:
        setIntakeWheelSpeedOposing(IntakeConstants.INTAKE_WHEEL_SPEED);
        break;
      case Out:
        setIntakeWheelSpeed(-IntakeConstants.INTAKE_WHEEL_SPEED);
        break;
      case Right:
        setIntakeWheelSpeedOposing(-IntakeConstants.INTAKE_WHEEL_SPEED);
        break;
      case Slow:
        setIntakeWheelSpeed(0.2);
        break;  
      case Outly:
        setIntakeWheelSpeed(-0.1);
    }
  }
   
  /** Set the intake angle state according to the current one */
  private void setAngleMotorToState() {
    Double targetPosition;
    switch (intakeAngleState) {
      case Up:
        targetPosition = IntakeConstants.MINIMUM_POSITION;
        m_angleMotor.selectProfileSlot(0, 0);
        m_angleMotor.set(ControlMode.Position, targetPosition);
        break;
      case Down:
        targetPosition = IntakeConstants.MAXIMUM_POSITION;
        m_angleMotor.selectProfileSlot(1, 0);
        m_angleMotor.set(ControlMode.Position, targetPosition);
        break;
      case Middle:
        targetPosition = IntakeConstants.MIDDLE_POSITION;
        m_angleMotor.selectProfileSlot(0, 0);
        m_angleMotor.set(ControlMode.Position, targetPosition);
        break;
    }
  }

  /**
   * Set the wheel state to the given one
   * @param state The new state of the wheels
   */
  public void setWheelState(intakeWheelState state) {
    intakeState = state;
  }

  /** Set the intake angle motor position to given value [revolutions]. */
  public void setIntakePosition(double position) {
    m_angleMotor.set(ControlMode.Position, position);
  }

  // Open the intake pistons
  public void closeIntake() {
    m_intakePistons.set(Value.kForward);
  }


  /**
   * Close the intake pistons
   */

  public void openIntake() {
    m_intakePistons.set(Value.kReverse);
  }

  /**
   * Move the wheels to center the object and capture it
   * @param speed - A value that represents the speed of the motors [-1 - 1]
   */
  public void setIntakeWheelSpeed(double speed) {
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, -speed);
  }

  /**
   * Move the wheels in oposing directions for centering it.
   * @param speed - A value that represents the speed of the motors [-1 - 1]
   */
  public void setIntakeWheelSpeedOposing(double speed) {
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, speed);
  }
  
  /**
   * Set the intake angle state
   * @param state   The new desired state.
   */
  public void setAngleState(IntakeAngleState state) {
    intakeAngleState = state;
  }

  /** Stop the angle motor */
  public void stopAngle() {
    m_angleMotor.set(ControlMode.Disabled, 0);
  }

  /** Stop the wheels motors */
  public void stopIntakeWheel() {
    m_leftMotor.set(ControlMode.Disabled, 0);
    m_rightMotor.set(ControlMode.Disabled, 0);
  }

  /** Toggle the pistons */
  public void togglePistons() {
    m_intakePistons.set(m_intakePistons.get() == Value.kForward ? Value.kReverse : Value.kForward);
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
   * @return  true if the limit switch is pressed and false otherwise
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

  /** Reset the intake angle encoder */
  public void resetEncoder() {
    m_angleMotor.setSelectedSensorPosition(0);
  }

  /**
   * Set the calue of the intakes position
   * @param isIntakeUp  the new value of the intake position
   */
  public void setIsUp(boolean isIntakeUp) {
    isUp = isIntakeUp;
  }

  public boolean getIsUp() {
    return isUp;
  }
}
