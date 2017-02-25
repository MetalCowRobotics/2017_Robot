package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

public enum RollerIntake {
	
	INSTANCE;
	
	private final SpeedController rollerMotor; 
	
	private RollerIntake() {
		rollerMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "roller.motor.channel", "roller.motor.reverse");
	}
	
	public void setRollerSpeed(double speed){
		rollerMotor.set(speed); 
	}

}
