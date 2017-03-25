package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.drives.DualDriveCommand;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

/**
 * Created by aaron on 11/18/16.
 */
public class DriveSystem implements Subsystem {
    
    public enum State {
    	DRIVE, BRAKE;
    }
    
    public enum DirectionState {
    	FORWARD, REVERSE;
    }
    
    public enum Speed {
    	SLOW, DEFAULT, FAST;
    }
    
    private State state;
    private DirectionState direction;
    private Speed speed;
    
    DualDriveCommand command;
    
    public DriveSystem () {
    	state = State.DRIVE;
    	direction = DirectionState.FORWARD;
    	speed = Speed.DEFAULT;
    }
    
    public void setDrive(DualDriveCommand command){
    	this.command = command;
    }
    
    private double scaleFunction (double wheel) {
		double wheelNonLinearity = 0.5;
		return Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
				/ Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	}
    
    private double scalePower(double power){
    	for(int i = 0; i < 2; i++){
    		power = scaleFunction(power);
    	}
    	return power;
    }
    
    public void drive(){
    	state = State.DRIVE;
    }
    
    public void brake(){
    	state = State.BRAKE;
    }
    
    public void setForward() {
    	direction = DirectionState.FORWARD;
    }
    
    public void setReverse() {
    	direction = DirectionState.REVERSE;
    }
    
    public void setFast(){
    	speed = Speed.FAST;
    }
    
    public void setNormal(){
    	speed = Speed.DEFAULT;
    }
    
    public void setSlow(){
    	speed = Speed.SLOW;
    }
    
	@Override
	public void run() {	
		
		double multiplier;
		switch (speed) {
		case SLOW:
			multiplier = PropertyStore.INSTANCE.getDouble("drive.speed.slow");
			break;
		case FAST:
			multiplier = PropertyStore.INSTANCE.getDouble("drive.speed.fast");
			break;
		case DEFAULT:
		default:
			multiplier = PropertyStore.INSTANCE.getDouble("drive.speed.default");
		}
		
		double leftPower = 0;
		double rightPower = 0;
		
		switch (state) {
		case DRIVE:
			leftPower = scalePower(command.getLeftVoltage()) * multiplier;
			rightPower = scalePower(command.getRightVoltage()) * multiplier;
			Drivetrain.INSTANCE.setBrake(false);
			break;
			
		case BRAKE:
			leftPower = 0;
			rightPower = 0;
			Drivetrain.INSTANCE.setBrake(true);
			break;
		}
		
		switch (direction) {
		case FORWARD:
			break;
		case REVERSE:
			double tempPower = leftPower;
			leftPower = -rightPower;
			rightPower = -tempPower;
			break;
		}
		
		Drivetrain.INSTANCE.setLeftSpeed(leftPower);
		Drivetrain.INSTANCE.setRightSpeed(rightPower);

	
	}
	
}