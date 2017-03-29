package org.usfirst.frc.team4213.commands;

import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

/**
 *
 */
public class TimedStraightDrive extends TimedCommand {

	private final double speed;
	PIDController angle = new PIDController("angle", -3, -0.5, -0.1, 1, true);

	
    public TimedStraightDrive(double timeout, double speed) {
        super(timeout);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void start() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    	angle.setTarget(Drivetrain.INSTANCE.getYaw());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {		
    	double offset = cap(angle.feedAndGetValue(Drivetrain.INSTANCE.getYaw()), 0.4*speed);
		Drivetrain.INSTANCE.setLeftSpeed(speed + offset);
		Drivetrain.INSTANCE.setRightSpeed(speed - offset);
    }
    
    public double cap(double speed, double max){
    	if(speed >= 0){
    		speed = Math.min(speed, max);
    	}else{
    		speed = Math.max(speed, -max);
    	}
    	
    	return speed;
    }

    // Called once after timeout
    protected void stop() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }
}
