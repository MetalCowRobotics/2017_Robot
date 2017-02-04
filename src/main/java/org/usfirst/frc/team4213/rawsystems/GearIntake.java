package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class GearIntake {

	public final static GearIntake INSTANCE = new GearIntake();
	
	private final DoubleSolenoid topHinge; 
	
	private final DoubleSolenoid leftHinge; 
	
	private final DoubleSolenoid rightHinge; 
	
	private GearIntake() { 
		topHinge = new DoubleSolenoid(Integer.parseInt(System.getProperty("topHinge.solenoid.forward.channel")),Integer.parseInt(System.getProperty("topHinge.solenoid.reverse.channel")));
		leftHinge = new DoubleSolenoid(Integer.parseInt(System.getProperty("leftHinge.solenoid.forward.channel")),Integer.parseInt(System.getProperty("leftHinge.solenoid.reverse.channel")));
		rightHinge = new DoubleSolenoid(Integer.parseInt(System.getProperty("rightHinge.solenoid.forward.channel")),Integer.parseInt(System.getProperty("rightHinge.solenoid.reverse.channel")));
	}
	
	public void setTopHingeOpen(boolean open) {
		if(open) {
			topHinge.set(Value.kReverse);
		} else {
			topHinge.set(Value.kForward);
		}
	}
	
	public void setLeftHingeOpen(boolean open) {
		if(open) {
			leftHinge.set(Value.kReverse);
		} else {
			leftHinge.set(Value.kForward);
		}
	}
	public void setRightHingeOpen(boolean open) {
		if(open) {
			rightHinge.set(Value.kReverse);
		} else {
			rightHinge.set(Value.kForward);
		}
	}
}
