package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class Feeder {
	public final static Feeder INSTANCE = new Feeder();
	
	private final SpeedController feederMotor; 
	
	private Feeder() {
		feederMotor = new CANTalon(Integer.parseInt(System.getProperty("feeder.motor.channel")));
	}
	
	public void setMotorSpeed(double speed){
		feederMotor.set(speed);
	}

}
