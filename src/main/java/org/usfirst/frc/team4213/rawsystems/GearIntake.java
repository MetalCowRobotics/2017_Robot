package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class GearIntake {

	public final static GearIntake INSTANCE = new GearIntake();
	
	private final DoubleSolenoid topHinge; 
	
	private final DoubleSolenoid frontHinge; 
	
	private GearIntake() { 
		topHinge = new DoubleSolenoid(Integer.parseInt(System.getProperty("topHinge.solenoid.forward.channel")),Integer.parseInt(System.getProperty("topHinge.solenoid.reverse.channel")));
		frontHinge = new DoubleSolenoid(Integer.parseInt(System.getProperty("frontHinge.solenoid.forward.channel")),Integer.parseInt(System.getProperty("frontHinge.solenoid.reverse.channel")));
	}
	
	public void setTopHingeOpen(boolean open) {
		if(open) {
			topHinge.set(Value.kReverse);
		} else {
			topHinge.set(Value.kForward);
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
