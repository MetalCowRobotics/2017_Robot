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
	
	State state;
	
	double defaultRunSpeed;
	
	boolean controlShooter;
	boolean controlHood;
	
	ErrorController shooterTBH;
	PIDController hoodPID;
	
	public ShooterSystem(){
		state = State.IDLE;
		controlShooter = true;
		controlHood = true;
		double tbhKi = PropertyStore.INSTANCE.getDouble("shooter.tbh.ki");
		shooterTBH = new TBHController("Shooter",tbhKi);
		double kp=1;
		double ki=0;
		double kd=0;
		double kiLife = 1;
		hoodPID = new PIDController("Hood",kp,ki,kd,kiLife);
		defaultRunSpeed = PropertyStore.INSTANCE.getDouble("shooter.defaultspeed");
	}
	
	void runShooterSpeedLoop(){
		final double rps = Shooter.INSTANCE.getFlywheelSpeed();
		final double pwm = Shooter.INSTANCE.getShooterPower();
		final double power = shooterTBH.feedAndGetValue(rps,pwm);
		Shooter.INSTANCE.setFlywheelSpeed(power);
	}
	
	void runHoodPIDLoop(){
		final double pos = Shooter.INSTANCE.getFlywheelEncoderPosition();
		final double vel = Shooter.INSTANCE.getFlywheelSpeed();
		final double power = hoodPID.feedAndGetValue(pos, vel);
		Shooter.INSTANCE.setHoodSpeed(power);
	}

	public void shoot(){
		state = State.SHOOTING;
	}
	
	public void idle(){
		state = State.IDLE;
	}
	public void bumpHoodAngle(double angle) {
		hoodPID.bumpTarget(angle);
	}
	
	@Override
	public void run() {
		switch(state){ 
	    case IDLE:  
	    	shooterTBH.setTarget(0);
	    	break; 
	    case SHOOTING:
	    	shooterTBH.setTarget(defaultRunSpeed);
		    break;
		}
		if(controlShooter){
			runShooterSpeedLoop();
		}
		if(controlHood){
			runHoodPIDLoop();
		}
	}
	
}
