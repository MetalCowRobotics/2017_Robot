package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.rawsystems.Feeder;

public class FeederSystem implements Subsystem {
	
	public enum State {
		IDLE, RUNNING;
	}
	
	private State state;
	
	@Override
	public void run () {
		switch(state){
		case IDLE:
			Feeder.INSTANCE.setMotorSpeed(0);
			break;
		case RUNNING:
			Feeder.INSTANCE.setMotorSpeed(Double.parseDouble(System.getProperty("feeder.speed")));
			break;
		}
	}

}
