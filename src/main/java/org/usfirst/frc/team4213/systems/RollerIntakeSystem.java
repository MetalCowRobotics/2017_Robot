package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.rawsystems.RollerIntake;

public class RollerIntakeSystem implements Subsystem{
	
	public enum State{
		IDLE,RUNNING;
	}
	
	private State state;
	
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
			RollerIntake.INSTANCE.setRollerSpeed(Double.parseDouble(System.getProperty("rollerintake.speed")));
			break;
		}
		
	}
	

}
