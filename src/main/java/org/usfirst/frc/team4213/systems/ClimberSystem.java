package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.Climber;

public class ClimberSystem implements Subsystem {

	public enum State{
		CLIMBING,IDLE;	
	}
	
	private double climbSpeed;
	
	private State state; 
	
	public ClimberSystem() {
		state = State.IDLE; 
		climbSpeed = PropertyStore.INSTANCE.getDouble("climber.speed");
	}
	
	public void climb() {
		state = State.CLIMBING;
	}
	
	
	public void idle() {
		state = State.IDLE; 
	}
	
	@Override
	public void run() {
		switch(state) {
		case CLIMBING:
			Climber.INSTANCE.setClimberSpeed(climbSpeed);
			break;
		case IDLE:
			Climber.INSTANCE.setClimberSpeed(0);
			break; 
		}
	}	
}
