package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public enum GearIntake {

	INSTANCE;
	
	private final DoubleSolenoid topHinge; 
	
	private final DoubleSolenoid frontHinge; 
	
	private GearIntake() { 
		topHinge = ComponentBuilder.buildDoubleSolenoid("topHinge.solenoid.forward.channel", "topHinge.solenoid.reverse.channel");
		frontHinge = ComponentBuilder.buildDoubleSolenoid("frontHinge.solenoid.forward.channel", "frontHinge.solenoid.reverse.channel");
	}
	
	public void setTopHingeOpen(boolean open) {
		if(open) {
			topHinge.set(Value.kForward);
		} else {
			topHinge.set(Value.kReverse);
		}
	}
	
	
	public void setFronttHingeOpen(boolean open) {
		if(open) {
			frontHinge.set(Value.kReverse);
		} else {
			frontHinge.set(Value.kForward);
		}
	}
}
