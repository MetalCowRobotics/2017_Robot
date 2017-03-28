package org.usfirst.frc.team4213.commands;

import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedStraightDrive extends TimedCommand {

	private final double speed;
	PIDController angle = new PIDController("angle", -3, -0.5, -0.1, 1, true);

	
    public TimedStraightDrive(double timeout, double speed) {
        super(timeout);
        this.speed = speed;
        angle.setTarget(0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {		
    	double offset = angle.feedAndGetValue(Drivetrain.INSTANCE.getYaw());
		Drivetrain.INSTANCE.setLeftSpeed(speed + offset);
		Drivetrain.INSTANCE.setRightSpeed(speed - offset);
    }

    // Called once after timeout
    protected void end() {
    	Drivetrain.INSTANCE.setLeftSpeed(0);
    	Drivetrain.INSTANCE.setRightSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
