package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	public final static Drivetrain INSTANCE = new Drivetrain();

    private final SpeedController leftMotor;

    private final SpeedController rightMotor;

    private Drivetrain () {
        leftMotor = new Talon(Integer.parseInt(System.getProperty("left.motor.channel")));
        rightMotor = new Talon(Integer.parseInt(System.getProperty("right.motor.channel")));
    }

    public void setLeftSpeed(double speed){
    	leftMotor.set(speed);
    }
    
    public void setRightSpeed(double speed){
    	rightMotor.set(speed);
    }
}
