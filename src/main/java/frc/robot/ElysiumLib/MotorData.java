// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.ElysiumLib;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;

/**  This class is for getting data from a motor or encoder, no matter it's type */
public class MotorData {
    private TalonFX m_encoder = null;
    private RelativeEncoder m_revEncoder = null;
    private AbsoluteEncoder m_absEncoder = null;
    private TalonSRX m_tEncoder = null;
    private String m_name;
    
    public MotorData(TalonFX m_encoder, String m_name) {
        this.m_name = m_name;
        this.m_encoder = m_encoder;
    }

    public MotorData(RelativeEncoder m_revEncoder, String m_name) {
        this.m_name = m_name;
        this.m_revEncoder = m_revEncoder;
    }

    public MotorData(AbsoluteEncoder m_absEncoder, String m_name) {
        this.m_name = m_name;
        this.m_absEncoder = m_absEncoder;
    }

    public MotorData(TalonSRX m_tEncoder, String m_name) {
        this.m_name = m_name;
        this.m_tEncoder = m_tEncoder;
    }

    public double getPosition() {
        if (m_encoder != null)
            return m_encoder.getSelectedSensorPosition();
        if (m_absEncoder != null)
            return m_absEncoder.getPosition();
        if (m_tEncoder != null)
            return m_tEncoder.getSelectedSensorPosition();
        return m_revEncoder.getPosition();
    }

    public double getVelocity() {
        if (m_encoder != null)
            return m_encoder.getSelectedSensorVelocity();
        if (m_absEncoder != null)
            return m_absEncoder.getVelocity();
        if (m_tEncoder != null)
            return m_tEncoder.getSelectedSensorVelocity();
        return m_revEncoder.getVelocity();
    }

    public String getName() {
        return m_name;
    }

    public String ToString() {
        return String.format("{0} -> Position = {1} , Velocity = {2}", getName(), getPosition(), getVelocity());
    }
}
