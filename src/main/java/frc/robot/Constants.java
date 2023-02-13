// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class TrackConstants {
      // The velocity of the track retraction and opening. In range [-1, 1].
      public static final double TRACK_MOVEMENT_SPEED = 0.5;
      public static final int MAX_MOTOR_POS = -1;
  }

  public static class IntakeConstants {
    public static final double INTAKE_WHEEL_SPEED = 0.5;
    public static final double MINIMUM_POSITION = 0;
    public static final double MAXIMUM_POSITION = 20000;
  }

  public static class DriveConstants {
    public static final double CONTROLLER_SENSETIVITY = 1;
  }

  public static class PhysicalProperties {}

  public static class Ports {
    // Ports for the differential drive
    public static class Drive {
      public static final int FRONT_LEFT_MOTOR = 8;
      public static final int REAR_LEFT_MOTOR = 9;
      public static final int FRONT_RIGHT_MOTOR = 1;
      public static final int REAR_RIGHT_MOTOR = 2;
    }

    public static class Intake {
      public static final int INTAKE_ANGLE_MOTOR = 6;
      public static final int RIGHT_INTAKE_MOTOR = 4;
      public static final int LEFT_INTAKE_MOTOR = 5;

      public static final int OPEN_PISTONS = 7;
      public static final int CLOSE_PISTONS = 0; 
    }

    public static class Elevator {}
  
    public static class Track {
      public static final int TRACK_MOTOR = 7;
      public static final int OPEN_TRACK_SOLENOID = 1;
      public static final int CLOSE_TRACK_SOLENOID = 4;
    }

    public static class Controllers {
      public static final int DRIVER_CONTROLLER = 0;
      public static final int OPERATOR_CONTROLLER  = 1;
    }

  }

}
