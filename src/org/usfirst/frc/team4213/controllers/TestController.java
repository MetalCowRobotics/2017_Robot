package org.usfirst.frc.team4213.controllers;

import org.usfirst.frc.team4213.commands.CommandSequence;
import org.usfirst.frc.team4213.commands.DistanceStraightDrive;
import org.usfirst.frc.team4213.commands.RotateDrive;
import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.metallib.gamepad.CowGamepad;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestController implements Runnable {
	
	CowGamepad driver;
	CowGamepad operator;
	
	PIDController angle = new PIDController("angle", 3, 1, 0, 1, true);
	PIDController drive = new PIDController("dist", 0.1, 0,0,1,true);
	CommandSequence auto = new CommandSequence();
	DistanceStraightDrive driveForward = new DistanceStraightDrive(360.0 *(5/(4.0*Math.PI)), 0.5);
	
	public TestController(CowGamepad driver, CowGamepad operator){
		this.driver = driver;
		this.operator = operator;
		
		System.out.println("Test Init");
		auto.addCommand(driveForward);
		auto.addCommand(new RotateDrive(20));
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
//		System.out.println("We started");
//		if(!driveForward.isFinished()){
//		driveForward.execute();
//		}else {
//			driveForward.end();
//		}
		
//		
//		double dist = 40;
//		drive.setTarget(360.0 *(dist/(4.0*Math.PI)));
//		double speed = driver.getLY();
//		speed = drive.feedAndGetValue(Drivetrain.INSTANCE.getLeftPos());
//		speed = cap(speed, 0.45);
//		
//		angle.setTarget(0);
//		double offset = angle.feedAndGetValue(Drivetrain.INSTANCE.getYaw());
//		
//		int kp = Preferences.getInstance().getInt("auto.proportion", 500);
//		double error = Drivetrain.INSTANCE.getLeftVel() - Drivetrain.INSTANCE.getRightVel();
//		double offset2 = error / kp;
//		
//		
//		Drivetrain.INSTANCE.setLeftSpeed(cap(speed + offset, 0.5));
//		Drivetrain.INSTANCE.setRightSpeed(cap(speed - offset, 0.5));

//		double masterPower = driver.getLY();
//		double slavePower = masterPower;
//		 
//		double error = 0;
//		 
//		//'Constant of proportionality' which the error is divided by. Usually this is a number between 1 and 0 the
//		//error is multiplied by, but we cannot use floating point numbers. Basically, it lets us choose how much 
//		//the difference in encoder values effects the final power change to the motor.
//		int kp = Preferences.getInstance().getInt("auto.proportion", 500);
//		
//		//This is where the magic happens. The error value is set as a scaled value representing the amount the slave
//		//motor power needs to change. For example, if the left motor is moving faster than the right, then this will come
//		//out as a positive number, meaning the right motor has to speed up.
//		error = Drivetrain.INSTANCE.getLeftVel() - Drivetrain.INSTANCE.getRightVel();
//		
//		SmartDashboard.putNumber("Drive Left Vel",Drivetrain.INSTANCE.getLeftVel());
//		SmartDashboard.putNumber("Drive Right Vel",Drivetrain.INSTANCE.getRightVel());
//		
//		//This adds the error to slavePower, divided by kp. The '+=' operator literally means that this expression really says 
//		//"slavePower = slavepower + error / kp", effectively adding on the value after the operator.
//		//Dividing by kp means that the error is scaled accordingly so that the motor value does not change too much or too 
//		//little. You should 'tune' kp to get the best value. For us, this turned out to be around 5. 
//		slavePower += error / kp;
//		
//		Drivetrain.INSTANCE.setLeftSpeed(masterPower);
//		Drivetrain.INSTANCE.setRightSpeed(slavePower);
//		    
//		System.out.println("Mr. Hughes, I'm sorry. This will be my final message before I quit MetalCow. It is buried in the code for a reason. I just wanted to say that I have spent many months and many sleepless"
//				+ "days trying to get this robot to work, but everyone just keeps piling stuff on me. I can't take it anymore. Tell everyone I'm sorry. I just... can't get this code to work. It's harder "
//				+ "than everyone thinks it is. Hopefully you guys will find a new coder that's just as good or better than me, or you are all screwed. Thank you, and goodnight.");
		
		
		SmartDashboard.putNumber("Yaw Angle" , Drivetrain.INSTANCE.getYaw());
		SmartDashboard.putNumber("Drive Dist", Drivetrain.INSTANCE.getLeftPos());
	}
}
