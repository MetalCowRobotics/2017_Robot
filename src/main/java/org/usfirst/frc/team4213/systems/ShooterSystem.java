package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.controlloops.ErrorController;
import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.metallib.controlloops.TBHController;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.Shooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSystem implements Subsystem{
	
	public enum State{
		IDLE, SHOOTING;
	}
	
	public enum HoodState{
		IDLE,BUMPINGUP, BUMPINGDOWN;
	}
	
	HoodState hoodState;
	State state;
			
	TBHController shooterTBH;
	PIDController hoodPID;
	
	public ShooterSystem(){
		state = State.IDLE;
		hoodState = HoodState.IDLE;
		
		double tbhKi = PropertyStore.INSTANCE.getDouble("shooter.tbh.ki");
		shooterTBH = new TBHController("shooter",tbhKi, false);
		
		double hoodKp = PropertyStore.INSTANCE.getDouble("hood.kp");
		double hoodKi = PropertyStore.INSTANCE.getDouble("hood.ki");
		double hoodKd = PropertyStore.INSTANCE.getDouble("hood.kd");
		double hoodILife = PropertyStore.INSTANCE.getDouble("hood.ilife");
		hoodPID = new PIDController("hood",hoodKp,hoodKi,hoodKd,hoodILife,false);
	}
	
	double getDesiredFlywheelSpeed(){
		return SmartDashboard.getNumber("shooter.speed", 1500);
	}
	
	double getFlywheelSpeed(){
		return  (1.0/100.0) / Shooter.INSTANCE.getFlywheelEncPeriod();
	}
	
	
	void runShooterSpeedLoop(){
		final double rps = getFlywheelSpeed();
		final double pwm = Shooter.INSTANCE.getShooterPower();
		final double power = shooterTBH.feedAndGetValue(rps,pwm);
		Shooter.INSTANCE.setFlywheelSpeed(power);
	}
	
	void runHoodPIDLoop(){
		final double target = SmartDashboard.getNumber("shooter.hoodtarget",0);
		hoodPID.setTarget(target);
		final double position = Shooter.INSTANCE.getHoodEncoderPosition();
		SmartDashboard.putNumber("shooter.hoodposition", position);
		final double outputPWM = hoodPID.feedAndGetValue(position);
		Shooter.INSTANCE.setHoodSpeed(outputPWM);
	}

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
	public void bumpHoodAngle(double angle) {
		hoodPID.bumpTarget(angle);
		SmartDashboard.putNumber("shooter.hoodtarget", hoodPID.target);
	}
	
	@Override
	public void run() {
		switch(state){ 
	    case IDLE:  
	    	shooterTBH.setTarget(0);
	    	Shooter.INSTANCE.setFlywheelSpeed(0);
	    	break; 
	    case SHOOTING:
	    	shooterTBH.setTarget(getDesiredFlywheelSpeed());
	    	SmartDashboard.putNumber("shooter.currentspeed", getFlywheelSpeed());
		    break;
		}
		
		
		
		switch(hoodState){
		case BUMPINGUP:
			bumpHoodAngle(1);
			break;
		case BUMPINGDOWN:
			bumpHoodAngle(-1);
			break;
		default:
			break;
		}
		
		runShooterSpeedLoop();
		runHoodPIDLoop();

	}
	
}
