package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.RollerIntake;

public class RollerIntakeSystem implements Subsystem{
	
	public enum State{
		IDLE,RUNNING;
	}
	
	private State state;
	private double runSpeed;
	
	public RollerIntakeSystem(){
		state = State.RUNNING;
		runSpeed = PropertyStore.INSTANCE.getDouble("rollerintake.speed");
	}
	
	public void intake() {
		state = State.RUNNING; 
	}
	
	public void idle() {
		state = State.IDLE; 
	}
	
	@Override
	public void run() {
		switch(state){
		case IDLE: 
			RollerIntake.INSTANCE.setRollerSpeed(0);
			break;
		case RUNNING:
			RollerIntake.INSTANCE.setRollerSpeed(runSpeed);
			break;
		}
		
	}
	

}
