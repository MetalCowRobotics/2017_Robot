package org.usfirst.frc.team4213.controllers;

import java.util.Optional;

import org.usfirst.frc.team4213.rawsystems.Drivetrain;
import org.usfirst.frc.team4213.systems.DriveSystem;
import org.usfirst.frc.team4213.systems.FeederSystem;
import org.usfirst.frc.team4213.systems.GearIntakeSystem;
import org.usfirst.frc.team4213.systems.RollerIntakeSystem;
import org.usfirst.frc.team4213.systems.ShooterSystem;
import org.usfirst.frc.team4213.systems.Subsystem;
import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.metallib.drives.TankDriveCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController implements Runnable{

    private static Optional<DriveSystem> driveSystem;
	private static Optional<ShooterSystem> shooter;
	private static Optional<GearIntakeSystem> gearIntake;
	private static Optional<FeederSystem> feeder;
	private static Optional<RollerIntakeSystem> rollerIntake;
	double pulsePerRev = 1440;
	int currentState = 3;
	PIDController leftWheel = new PIDController("wheel", 20,0,0,1, false);
	PIDController rightWheel = new PIDController("wheel", 20,0,0,1, false);
	PIDController angleController = new PIDController("angle", 5, 0, 0,1, true);
	double startLPos;
	double startRPos;
	public AutonomousController(DriveSystem driveSystem, ShooterSystem shooter, GearIntakeSystem gearIntake, FeederSystem feeder, RollerIntakeSystem rollerIntake){
		AutonomousController.driveSystem = Optional.ofNullable(driveSystem);
		AutonomousController.shooter = Optional.ofNullable(shooter);
		AutonomousController.gearIntake = Optional.ofNullable(gearIntake);
		AutonomousController.feeder = Optional.ofNullable(feeder);
		AutonomousController.rollerIntake = Optional.ofNullable(rollerIntake);
	}
	
	@Override
	public void run() {
		switch(currentState){
		case 0:
			System.out.println("Startin auto");
			Drivetrain.INSTANCE.resetEncPos();
			System.out.println("reset enc");
			startLPos = Drivetrain.INSTANCE.getLeftPos();
			startRPos = Drivetrain.INSTANCE.getRightPos();
			currentState++;
		case 1:
		double target = getRevPerDist(SmartDashboard.getNumber("travel", 36)) * pulsePerRev;
		System.out.println("Target: " + target);
		leftWheel.setTarget(target);
		rightWheel.setTarget(target);
		System.out.println("Pos L: " + getL());
		System.out.println("Pos R: " + (-getR()));
		double leftSpeed = leftWheel.feedAndGetValue(getL());
		double rightSpeed = rightWheel.feedAndGetValue(-getR());
		System.out.println("lVolt: "+leftSpeed);
		System.out.println("rVolt: "+rightSpeed);
		if(rightSpeed >= 0){
			rightSpeed = Math.min(rightSpeed, .5);
		}else {
			rightSpeed = Math.max(rightSpeed, -.5);
		}
		Drivetrain.INSTANCE.setLeftSpeed(rightSpeed);
		Drivetrain.INSTANCE.setRightSpeed(rightSpeed);
		if(Math.abs(rightWheel.getError()) < 80){
			currentState++;
		}
		break;
		case 2:
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
			currentState++;
		case 3: 
			angleController.setTarget(90);
			double power = angleController.feedAndGetValue(Drivetrain.INSTANCE.getAngle());
			Drivetrain.INSTANCE.setLeftSpeed(power);
			Drivetrain.INSTANCE.setRightSpeed(-power);
		break;
		case 4:
			// place gear
		break;
		case 5:
			// back off
		break;
		case 6:
			// turn to angle
		break;
		case 7:
			// drive straight
		break;
		case 8:
			// rotate
		break;
		case 9:
			// spin up
			// set hood
		break;
		case 10:
			// shoot
		break;
		case 11:
			
		break;
		}
		
	}
	
	public double getR(){
		return (Drivetrain.INSTANCE.getRightPos() - startRPos); 
	}
	
	
	public double getL(){
		return (Drivetrain.INSTANCE.getLeftPos() - startLPos); 
	}
	
	
	public void runSystems(){
		gearIntake.ifPresent(Subsystem::run);
		shooter.ifPresent(Subsystem::run);
		rollerIntake.ifPresent(Subsystem::run);
		feeder.ifPresent(Subsystem::run);
		driveSystem.ifPresent(Subsystem::run);
	}
	public double getRevPerDist(double dist){
		return dist/(4*Math.PI);
	}

}
