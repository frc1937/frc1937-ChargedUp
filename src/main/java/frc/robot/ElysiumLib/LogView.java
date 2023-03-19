// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.ElysiumLib;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.DriverStation;

/** This class is for saving data of motors during the games */
public class LogView {
    private MotorData[] m_motors;
    private String name = "";
    private int second = 0;

    public LogView(MotorData motorController, MotorData... motorControllers) {
        m_motors = new MotorData[motorControllers.length + 1];
        m_motors[0] = motorController;
        System.arraycopy(motorControllers, 0, m_motors, 1, motorControllers.length);
    }

    public void start() {
        LocalDateTime m_date = LocalDateTime.now();
        while(true) {
            int i = 0;
            name = String.format("{0}-{1}-{2}-{3}-{4}",
            m_date.getYear(), m_date.getMonthValue(), m_date.getDayOfMonth(), i);
            try{
                File file = new File(name);
                file.setReadable(true);
                file.setWritable(true);
                break;
            }
            catch (Exception e) {
                i++;
            }
        }
        DriverStation.reportWarning("File uploaded", false);
    }

    public void updateMotor(MotorData m_data) {
        String writeData = "";
        try {
            FileReader reader = new FileReader(name);
            int character = 0;
            String data = "";
            while ((character = reader.read()) != 1) {
                data += character;
            }
            writeData = data;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(name);

            writer.write(writeData + "\n" + m_data.ToString());

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeDate() {
        String writeData = "";
        try {
            FileReader reader = new FileReader(name);
            int character = 0;
            String data = "";
            while ((character = reader.read()) != 1) {
                data += character;
            }
            writeData = data;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(name);

            writer.write(writeData + "\n" + second);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        second++;
    }

    /** Update the values in the file.
     * 
     * <p>The new values are the {@link frc.robot.ElysiumLib.MotorData MotorDatas}
     * given by the user
     */
    public void update() {
        writeDate();
        
        for (int i = 0; i < m_motors.length - 1; i++) {
            updateMotor(m_motors[i]);
        }
    }
}
