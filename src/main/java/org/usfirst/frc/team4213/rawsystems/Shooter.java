package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class Shooter {
	public final static Shooter INSTANCE = new Shooter();
	
	private final SpeedController flywheelMotor;
	
	private final SpeedController hoodMotor; 
	
	private Shooter() {
		flywheelMotor = new CANTalon(Integer.parseInt(System.getProperty("flywheel.motor.channel")));
		hoodMotor = new CANTalon(Integer.parseInt(System.getProperty("hood.motor.channel")));
	}
	
	public void setFlywheelSpeed(double speed){
		flywheelMotor.set(speed);
	}
	
	public void setHoodSpeed(double speed){
		hoodMotor.set(speed);
	}

}
