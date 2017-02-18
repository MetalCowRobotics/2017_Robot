package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.Feeder;

public class FeederSystem implements Subsystem {
	
	public enum State {
		IDLE, RUNNING;
	}
	
	public FeederSystem(){
		state = State.IDLE;
		feedSpeed = PropertyStore.INSTANCE.getDouble("feeder.speed");
	}
	
	private double feedSpeed;
	private State state;
	
	public void feed() {
		state = State.RUNNING;  
	}
	
	public void idle() {
		state = State.IDLE; 
	}
	@Override
	public void run() {
		switch(state){
		case IDLE:
			Feeder.INSTANCE.setMotorSpeed(0);
			break;
		case RUNNING:
			Feeder.INSTANCE.setMotorSpeed(feedSpeed);
			break;
		}
	}

}
