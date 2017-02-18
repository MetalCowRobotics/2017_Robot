package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.SpeedController;

public class RollerIntake {
	public final static RollerIntake INSTANCE = new RollerIntake(); 
	private final SpeedController rollerMotor; 
	
	private RollerIntake() {
		rollerMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "roller.motor.channel", "roller.motor.reverse");
	}
	
	public void setRollerSpeed(double speed){
		rollerMotor.set(speed); 
	}

}
