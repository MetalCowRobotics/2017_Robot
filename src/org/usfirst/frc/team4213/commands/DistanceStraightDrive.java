package org.usfirst.frc.team4213.commands;

import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;


/**
 *
 */
public class DistanceStraightDrive extends Command{

	private final double maxSpeed;
	PIDController angle = new PIDController("angle", -3, -0.5, -0.1, 1, true);
	PIDController drive = new PIDController("dist", 0.1, 0,0,1,true);

	
	
    public DistanceStraightDrive(double dist, double maxSpeed) {
        super();
        drive.setTarget(dist);
        this.maxSpeed = Math.abs(maxSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	
    	System.out.println("This command is runnin");
    	double offset = angle.feedAndGetValue(Drivetrain.INSTANCE.getYaw());
    	double speed = drive.feedAndGetValue(Drivetrain.INSTANCE.getLeftPos());
    	
    	double leftPower = cap(speed + offset, maxSpeed);
    	double rightPower = cap(speed - offset, maxSpeed);
    	
    	Drivetrain.INSTANCE.setLeftSpeed(leftPower);
    	Drivetrain.INSTANCE.setRightSpeed(rightPower);
    	
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
        return drive.getError() < 10;
    }

    // Called once after isFinished returns true
    public void end() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }
}
