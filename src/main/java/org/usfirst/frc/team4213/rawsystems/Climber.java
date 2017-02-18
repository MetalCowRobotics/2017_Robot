package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

public enum Climber {
	INSTANCE;
	
	private final SpeedController climberMotor; 
	
	private Climber() {
		climberMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "climber.motor.channel", "climber.motor.reverse");
	}

	public void setClimberSpeed(double speed){
		climberMotor.set(speed);
	}
}
