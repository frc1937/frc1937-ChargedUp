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
  /**
   * The values of the {@link frc.robot.subsystems.TrackSubsystem} data, 
   * such as positions, velocities, mathematical constants and more 
   */
  public static class TrackConstants {
    /** The track retraction and opening speed */
      public static final double TRACK_MOVEMENT_SPEED = 0.5;

      /** The maximum position of the track */
      public static final int MAXIMUM_MOTOR_POS = 20000;

      /** The minimum position of the track */
      public static final int MINIMUM_MOTOR_POS = 500;

      /** the K_p value of the tracks PID controller */
      public static final double K_P = 0.5;

      /** the K_i value of the tracks PID controller */
      public static final double K_I = 0;
      
      /** the K_d value of the tracks PID controller */
      public static final double K_D = 0;
      
      /** the K_f value of the tracks PID controller */
      public static final double K_F = 0.1;

      /** The maximum error allowed for the opening of the track */
      public static final int MAXIMUM_TOLERANCE = 500;
  }

  /**
   * The values of the {@link frc.robot.subsystems.IntakeSubsystem} data, 
   * such as positions, velocities, mathematical constants and more 
   */
  public static class IntakeConstants {
    /**
     * The speed the intake wheels move at, a value between 1 and -1 
     */
    public static final double INTAKE_WHEEL_SPEED = 0.4;

    /**
     * The intake angle's minimum position
     */
    public static final double MINIMUM_POSITION = 0;

    /**
     * The intake angle's maximum position
     */
    public static final double MAXIMUM_POSITION = 8000;

    /** The middle positon of the encoder */
    public static final double MIDDLE_POSITION = 4000;

    /**
     * The k_p value of the angle's PIDFF
     */
    public static final double ANGLE_KP_0 = 0.3;

    /**
     * The k_i value of the angle's PIDFF
     */
    public static final double ANGLE_KI_0 = 0;

    /**
     * The k_d value of the angle's PIDFF
     */
    public static final double ANGLE_KD_0 = 0;

    /**
     * The k_p value of the angle's PIDFF
     */
    public static final double ANGLE_KF_0 = 0.4;

    /**
     * The k_p value of the angle's PIDFF
     */
    public static final double ANGLE_KP_1 = 0.075;

    /**
     * The k_i value of the angle's PIDFF
     */
    public static final double ANGLE_KI_1 = 0;

    /**
     * The k_d value of the angle's PIDFF
     */
    public static final double ANGLE_KD_1 = 0;

    /**
     * The k_p value of the angle's PIDFF
     */
    public static final double ANGLE_KF_1 = 0.025;
  }

  /**
   * The drive values for {@link frc.robot.subsystems.DriveSubsystem}
   */
  public static class DriveConstants {
    /**
     * The controller sensetivity for the driver controller for both x and y axis
     */
    public static final double CONTROLLER_SENSETIVITY = 0.75;
    public static final double MAX_RAMP_ANGLE = 15;
    public static final double TOLERANCE_DEGREES = 7;
  }

  /**
   * The lift values for {@link frc.robot.subsystems.LiftSubsystem}
   */
  public static class LiftConstants{
    /**
     * The maximum lift position for dispensing cones and cubes
     */
    public static final double MAXIMUM_LIFT_POSITION = 80000;

    /**
     * The minimum lift position for transferring the game object from the intake to the beak
     */
    public static final double MINIMUM_LIFT_POSITION = 0;

    /** The K_p value for the lift motor for the 0 pid slot (trl) */
    public static final double K_P_0 = 0.2;

    /** The K_i value for the lift motor for the 0 pid slot (trl) */
    public static final double K_I_0 = 0;
    
    /** The K_d value for the lift motor for the 0 pid slot (trl) */
    public static final double K_D_0 = 0;
    
    /** The K_f value for the lift motor for the 0 pid slot (trl) */
    public static final double K_F_0 = 0;
    
    /** The max output value for the lift motor (trl) */
    public static final double K_MAX = 0.3;

    /** The K_p value for the lift motor for the 1 pid slot (trl) */
    public static final double K_P_1 = 0.1;

    /** The K_i value for the lift motor for the 1 pid slot (trl) */
    public static final double K_I_1 = 0;

    /** The K_d value for the lift motor for the 1 pid slot (trl) */
    public static final double K_D_1 = 0;

    /** The K_f value for the lift motor for the 1 pid slot (trl) */
    public static final double K_F_1 = 0.1;
  }

  /**
   * The beak values for {@link frc.robot.subsystems.BeakSubsystem}
   */
  public static class BeakConstants {
    /**
     * The start position of checking whether the object is a cone or a cube
     */
    public static final double BEAK_CUBE_START_POSITION = -120;

    /**
     * The catch position of the beak for the cube
     */
    public static final double BEAK_CUBE_HOLD_POSITION = -135;

    /**
     * The catch position of the beak for the cone
     */
    public static final double BEAK_CONE_HOLD_POSITION = -160;
    
    /**
     * The max position the beak could arrive to whilst catching a cube
     * if the beak passses this location then the object is a cone
     */
    public static final double BEAK_CUBE_MAX_POSITION = -140;

    /**
     * The top position of the Beak
     */
    public static final double BEAK_TOP_POSITION = 0;

    /**
     * The k_p value of the PIDF for the beak
     */
    public static final double K_P = 3.0;

    /**
     * The k_i value of the PIDF for the beak
     */
    public static final double K_I = 0;

    /**
     * The k_d value of the PIDF for the beak
     */
    public static final double K_D = 0;

    /**
     * The k_ff value of the PIDF for the beak
     */
    public static final double K_FF = 0.4;
  }

  /**
   * The robots motor controllers and selenoid ID's for all the differenct subsystems
   */
  public static class Ports {
    public static class Drive {
      public static final int FRONT_LEFT_MOTOR = 8;
      public static final int REAR_LEFT_MOTOR = 9;
      public static final int FRONT_RIGHT_MOTOR = 1;
      public static final int REAR_RIGHT_MOTOR = 2;
      public static final int PIGEON_IMU = 11;
    }

    public static class Intake {
      public static final int INTAKE_ANGLE_MOTOR = 6;
      public static final int RIGHT_INTAKE_MOTOR = 4;
      public static final int LEFT_INTAKE_MOTOR = 5;

      public static final int OPEN_PISTONS = 7;
      public static final int CLOSE_PISTONS = 0; 
    }

    public static class Lift {
      public static final int LIFT_MOTOR = 10;
      public static final int LIFT_SWITCH = 6;
    }
  
    public static class Beak {
      public static final int BEAK_MOTOR = 3;
    }
      
    public static class Track {
      public static final int TRACK_MOTOR = 7;
      public static final int OPEN_TRACK_SOLENOID = 4;
      public static final int CLOSE_TRACK_SOLENOID = 1;
    }

    public static class Controllers {
      public static final int DRIVER_CONTROLLER = 0;
      public static final int OPERATOR_CONTROLLER  = 1;
    }
  }
}
