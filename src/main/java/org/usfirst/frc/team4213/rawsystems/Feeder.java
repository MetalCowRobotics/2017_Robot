package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

public enum Feeder {
	INSTANCE;
		
	private final SpeedController feederMotor; 
	
	private Feeder() {
		feederMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "feeder.motor.channel", "feeder.motor.reverse");
	}
	
	public void setMotorSpeed(double speed){
		feederMotor.set(speed);
	}

}
