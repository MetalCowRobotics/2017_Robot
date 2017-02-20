package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.rawsystems.GearIntake;

public class GearIntakeSystem implements Subsystem{
	
	public enum IntakeState{
		OPEN,CLOSE;
	}
	
	public enum HoldState{
		HOLD,DROP;
	}
	
	private IntakeState intakeState;
	private HoldState holdState;
	 
	public GearIntakeSystem(){
		intakeState = IntakeState.CLOSE;
		holdState = HoldState.HOLD;
	}
	
	public void openTop(){
		intakeState = IntakeState.OPEN;
	}
	
	public void closeTop(){
		intakeState = IntakeState.CLOSE;
	}
	
	public void dropGear(){
		holdState = HoldState.DROP;
	}
	
	public void holdGear(){
		holdState = HoldState.HOLD;
	}
	
	@Override
	public void run() {
		
		switch(intakeState){
		case OPEN: 
			GearIntake.INSTANCE.setFronttHingeOpen(true);
			break;
		case CLOSE: 
			GearIntake.INSTANCE.setFronttHingeOpen(false);
			break;
		}
		
		switch(holdState){
		case HOLD:
			GearIntake.INSTANCE.setTopHingeOpen(false);
		break;
		case DROP:
			GearIntake.INSTANCE.setTopHingeOpen(true);
		break;	
		}
		
	}

}
