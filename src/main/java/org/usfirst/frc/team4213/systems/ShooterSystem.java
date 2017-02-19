package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.controlloops.ErrorController;
import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.metallib.controlloops.TBHController;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.Shooter;

public class ShooterSystem implements Subsystem{
	
	public enum State{
		IDLE, SHOOTING;
	}
	
	public enum HoodState{
		IDLE,BUMPINGUP, BUMPINGDOWN;
	}
	
	HoodState hoodState;
	State state;
	
	double defaultHoodSpeed;
	
	double defaultRunSpeed;
	
//	boolean controlShooter;
//	boolean controlHood;
	
//	ErrorController shooterTBH;
//	PIDController hoodPID;
	
	public ShooterSystem(){
		state = State.IDLE;
//		controlShooter = true;
//		controlHood = true;
//		double tbhKi = PropertyStore.INSTANCE.getDouble("shooter.tbh.ki");
//		shooterTBH = new TBHController("Shooter",tbhKi);
//		double kp=1;
//		double ki=0;
//		double kd=0;
//		double kiLife = 1;
//		hoodPID = new PIDController("Hood",kp,ki,kd,kiLife);
		defaultRunSpeed = PropertyStore.INSTANCE.getDouble("shooter.defaultspeed");
		defaultHoodSpeed = PropertyStore.INSTANCE.getDouble("hood.defaultspeed");
	}
	
//	void runShooterSpeedLoop(){
//		final double rps = Shooter.INSTANCE.getFlywheelSpeed();
//		final double pwm = Shooter.INSTANCE.getShooterPower();
//		final double power = shooterTBH.feedAndGetValue(rps,pwm);
//		Shooter.INSTANCE.setFlywheelSpeed(power);
//	}
	
//	void runHoodPIDLoop(){
//		final double pos = Shooter.INSTANCE.getFlywheelEncoderPosition();
//		final double vel = Shooter.INSTANCE.getFlywheelSpeed();
//		final double power = hoodPID.feedAndGetValue(pos, vel);
//		Shooter.INSTANCE.setHoodSpeed(power);
//	}

	public void bumpUp(){
		hoodState = HoodState.BUMPINGUP;
	}
	
	public void bumpDown(){
		hoodState = HoodState.BUMPINGDOWN;
	}
	
	public void noBump(){
		hoodState = HoodState.IDLE;
	}
	
	public void shoot(){
		state = State.SHOOTING;
	}
	
	public void idle(){
		state = State.IDLE;
	}
//	public void bumpHoodAngle(double angle) {
//		hoodPID.bumpTarget(angle);
//	}
	
	@Override
	public void run() {
		switch(state){ 
	    case IDLE:  
//	    	shooterTBH.setTarget(0);
	    	Shooter.INSTANCE.setFlywheelSpeed(0);
	    	break; 
	    case SHOOTING:
//	    	shooterTBH.setTarget(defaultRunSpeed);
	    	Shooter.INSTANCE.setFlywheelSpeed(defaultRunSpeed);
		    break;
		}
		
		switch(hoodState){
		case IDLE:
			Shooter.INSTANCE.setHoodSpeed(0);
			break;
		case BUMPINGUP:
			Shooter.INSTANCE.setHoodSpeed(defaultHoodSpeed);
			break;
		case BUMPINGDOWN:
			Shooter.INSTANCE.setHoodSpeed(-defaultHoodSpeed);
			break;
		}
		
//		if(controlShooter){
//			runShooterSpeedLoop();
//		}
//		if(controlHood){
//			runHoodPIDLoop();
//		}
	}
	
}
