package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

public enum Climber {
	INSTANCE;
	
	private final SpeedController climberFrontMotor; 
	private final SpeedController climberBackMotor;
	
	private Climber() {
		climberFrontMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "climber.motor.front.channel", "climber.motor.front.reverse");
		climberBackMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "climber.motor.back.channel", "climber.motor.front.reverse");

	}

	public void setClimberSpeed(double speed){
		climberFrontMotor.set(speed);
		climberBackMotor.set(speed);
	}
}
