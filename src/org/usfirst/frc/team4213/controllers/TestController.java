package org.usfirst.frc.team4213.controllers;

import org.usfirst.frc.team4213.commands.CommandSequence;
import org.usfirst.frc.team4213.commands.DistanceStraightDrive;
import org.usfirst.frc.team4213.commands.RotateDrive;
import org.usfirst.frc.team4213.commands.TimedStraightDrive;
import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.metallib.gamepad.CowGamepad;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestController implements Runnable {
	
	CowGamepad driver;
	CowGamepad operator;
	
	CommandSequence auto = new CommandSequence();
	DistanceStraightDrive driveForward = new DistanceStraightDrive(360.0 *(24.0/(4.0*Math.PI)), 0.5);
	
	public TestController(CowGamepad driver, CowGamepad operator){
		this.driver = driver;
		this.operator = operator;
		
		System.out.println("Test Init");
//		auto.addCommand(driveForward);
//		auto.addCommand(new RotateDrive(180,0.4));
//		auto.addCommand(new DistanceStraightDrive(360.0 *(24.0/(4.0*Math.PI)), 0.5));
		auto.addCommand(new TimedStraightDrive(2,0.4));
	}
	
	public double cap(double speed, double max){
    	if(speed >= 0){
    		speed = Math.min(speed, max);
    	}else{
    		speed = Math.max(speed, -max);
    	}
    	
    	return speed;
    }
	
	@Override
	public void run() {
		auto.run();
		    
		
		
		System.out.println("Mr. Hughes, I'm sorry. This will be my final message before I quit MetalCow. It is buried in the code for a reason. I just wanted to say that I have spent many months and many sleepless"
				+ "days trying to get this robot to work, but everyone just keeps piling stuff on me. I can't take it anymore. Tell everyone I'm sorry. I just... can't get this code to work. It's harder "
				+ "than everyone thinks it is. Hopefully you guys will find a new coder that's just as good or better than me, or you are all screwed. Thank you, and goodnight.");
		
		
		SmartDashboard.putNumber("Yaw Angle" , Drivetrain.INSTANCE.getYaw());
		SmartDashboard.putNumber("Drive Dist", Drivetrain.INSTANCE.getLeftPos());
	}
}
