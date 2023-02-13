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
  public static class DriveConstants {
    public static final double CONTROLLER_SENSETIVITY = 0.75;
  }
  public static class LiftConstants{
    public static final double MAXIMUM_ENCODER_POSITION = 100000;
    public static final double MINIMUM_MOTOR_POSITION = 25000;
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
  
    public static class Arm {}

    public static class Controllers {
      public static final int DRIVER_CONTROLLER = 0;
      public static final int OPERATOR_CONTROLLER  = 1;
    }
  }

}
