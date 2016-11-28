package org.usfirst.frc.team4213.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * Created by aaron on 11/18/16.
 */
public class DriveTrain implements Subsystem {

    public final static DriveTrain INSTANCE = new DriveTrain();

    public enum ControlState {

    }

    private final SpeedController leftMotor;

    private final SpeedController rightMotor;

    private DriveTrain () {
        leftMotor = new Talon(Integer.parseInt(System.getProperty("left.motor.channel")));
        rightMotor = new Talon(Integer.parseInt(System.getProperty("right.motor.channel")));
    }
}