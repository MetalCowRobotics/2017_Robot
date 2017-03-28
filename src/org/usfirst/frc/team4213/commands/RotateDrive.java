package org.usfirst.frc.team4213.commands;

import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

/**
 *
 */
public class RotateDrive extends Command {
	PIDController angle = new PIDController("angle", -3, -0.5, -0.1, 1, true);

    public RotateDrive(double angle) {
        super();
        this.angle.setTarget(angle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double offset = angle.feedAndGetValue(Drivetrain.INSTANCE.getYaw());
    	cap(offset, 0.5);
		Drivetrain.INSTANCE.setLeftSpeed(offset);
		Drivetrain.INSTANCE.setRightSpeed(-offset);
    }
    
    public double cap(double speed, double max){
    	if(speed >= 0){
    		speed = Math.min(speed, max);
    	}else{
    		speed = Math.max(speed, -max);
    	}
    	
    	return speed;
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return angle.getError() < 1;
    }

    // Called once after isFinished returns true
    public void end() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }
}
