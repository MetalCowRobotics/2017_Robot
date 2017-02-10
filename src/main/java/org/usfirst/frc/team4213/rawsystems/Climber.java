package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class Climber {
	public final static Climber INSTANCE = new Climber(); 
	
	private final SpeedController climberMotor; 
	
	private Climber() {
		climberMotor = new CANTalon(Integer.parseInt(System.getProperty("climber.motor.channel")));
	}

	public void setClimberSpeed(double speed){
		climberMotor.set(speed);
	}
}
