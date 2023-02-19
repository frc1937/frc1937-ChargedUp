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
  public static class PhysicalProperties {
    public static class DriveConstants {
      public static final double CONTROLLER_SENSETIVITY = 0.5;
    }
    
    public static class TrackConstants {
      /** The maximum position of the track motor */
      // TODO Check this value.
      public static final int MAX_MOTOR_POS = -1;
    }

    public static class Beak {
      // the velocity of the motor
      public static final double BEAK_MOTOR_SPEED = 0.4;

      // The value of the encoder position when starting to detect objects.
      public static final double BEAK_CUBE_START_POSITION = -120;

      // The encoder value the beak needs to catch the object
      public static final double BEAK_CUBE_HOLD_POSITION = -135;
      public static final double BEAK_CONE_HOLD_POS = -155;
      
      // If the beak passes this value then the object is a cone
      public static final double BEAK_CUBE_MAX_POSITION = -140;

      // The Minimum position of the beak
      public static final double BEAK_MIN_POSITION = 0;

      // PID variables
      public static final double K_P = 3;
      public static final double K_I = 0;
      public static final double K_D = 0.2;
    }
    
  public static class TrackConstants {
      // The velocity of the track retraction and opening. In range [-1, 1].
      public static final double TRACK_MOVEMENT_SPEED = 0.5;
  }

  public static class DriveConstants {
    public static final double CONTROLLER_SENSETIVITY = 0.75;
  }
  public static class LiftConstants{
    public static final double MAXIMUM_LIFT_POSITION = 100000;
    public static final double MINIMUM_LIFT_POSITION = 25000;
  }

  public static class Ports {
    // Ports for the differential drive
    public static class Drive {
      public static final int FRONT_LEFT_MOTOR = 8;
      public static final int REAR_LEFT_MOTOR = 9;
      public static final int FRONT_RIGHT_MOTOR = 1;
      public static final int REAR_RIGHT_MOTOR = 2;
    }

    public static class Intake {}

    public static class Lift {
      public static final int LIFT_MOTOR = 10;
      public static final int LIFT_SWITCH = 8;
    }
  
    public static class Beak {
      public static final int BEAK_MOTOR = 3;
      
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
