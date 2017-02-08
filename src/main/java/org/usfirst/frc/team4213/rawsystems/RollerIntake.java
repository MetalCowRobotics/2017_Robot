package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class RollerIntake {
	public final static RollerIntake INSTANCE = new RollerIntake(); 
	private final SpeedController rollerMotor; 
	
	private RollerIntake() {
		rollerMotor = new CANTalon(Integer.parseInt(System.getProperty("roller.motor.channel")));
	}
	
	public void setRollerSpeed(double speed){
		rollerMotor.set(speed); 
	}

}
