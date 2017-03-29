package org.usfirst.frc.team4213.commands;

import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateDrive extends Command {
	PIDController angle = new PIDController("RotAngle", -3, 0, 0, 1, true);
	final double maxSpeed;

    public RotateDrive(double angle, double maxSpeed) {
        super();
        this.angle.setTarget(angle);
        this.maxSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double offset = angle.feedAndGetValue(Drivetrain.INSTANCE.getYaw());
    	SmartDashboard.putNumber("Turn Speed", offset);
    	cap(offset, maxSpeed);
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
        return Math.abs(angle.getError()) < 0.5;
    }

    // Called once after isFinished returns true
    public void end() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }
}
