package org.usfirst.frc.team4213.controllers;

import java.util.Optional;

import org.usfirst.frc.team4213.metallib.controlloops.PIDController;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;
import org.usfirst.frc.team4213.systems.DriveSystem;
import org.usfirst.frc.team4213.systems.FeederSystem;
import org.usfirst.frc.team4213.systems.GearIntakeSystem;
import org.usfirst.frc.team4213.systems.RollerIntakeSystem;
import org.usfirst.frc.team4213.systems.ShooterSystem;
import org.usfirst.frc.team4213.systems.Subsystem;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController implements Runnable{

    private static Optional<DriveSystem> driveSystem;
	private static Optional<ShooterSystem> shooter;
	private static Optional<GearIntakeSystem> gearIntake;
	private static Optional<FeederSystem> feeder;
	private static Optional<RollerIntakeSystem> rollerIntake;
	double pulsePerRev = 1440;
	int currentState = 0;
	PIDController wheel = new PIDController("wheel", 20,0,0,1, false);
	PIDController angleController = new PIDController("angle", 3, 0.6, 0,1, true);
	double startLPos;
	double startRPos;
	Timer timer = new Timer();
	int autonMode = 0;
	boolean firstRun = true;
	SendableChooser<AutonMode> auton;
	
	
	private enum AutonMode {
		STRAIGHT_SIMPLE, STRAIGHT_GEAR, STRAIGHT_GEAR_OFFSET, STRAIGHT_GEAR_LINE ,LEFT_GEAR, RIGHT_GEAR, LEFT_SHOOT, RIGHT_SHOOT
	}
	
	public AutonomousController(DriveSystem driveSystem, ShooterSystem shooter, GearIntakeSystem gearIntake, FeederSystem feeder, RollerIntakeSystem rollerIntake){
		AutonomousController.driveSystem = Optional.ofNullable(driveSystem);
		AutonomousController.shooter = Optional.ofNullable(shooter);
		AutonomousController.gearIntake = Optional.ofNullable(gearIntake);
		AutonomousController.feeder = Optional.ofNullable(feeder);
		AutonomousController.rollerIntake = Optional.ofNullable(rollerIntake);
		
		auton = new SendableChooser<AutonMode>();
		auton.addDefault("Straight Simple", AutonMode.STRAIGHT_SIMPLE);
		auton.addObject("Straight Gear", AutonMode.STRAIGHT_GEAR);
		auton.addObject("Straight Gear and Line", AutonMode.STRAIGHT_GEAR_LINE);
		auton.addObject("Straight Gear offset", AutonMode.STRAIGHT_GEAR_OFFSET);
		SmartDashboard.putData("Autonomous Selection", auton);
		
	}
	
	@Override
	public void run() {
		if(firstRun){
			timer.start();
			firstRun = false;
		}
//		switch (autonMode) {
//		case 3:
//			break;
//		case 2:
//			break;
//		case 1:
//			straightAutoSimple();
//		default:
//			straightAuto();
//		}
		
		switch(auton.getSelected()){
		case STRAIGHT_GEAR:
			straightGearAutoTimed();
			break;
		case STRAIGHT_GEAR_LINE:
			straightGearLineAutoTimed();
			break;
		case STRAIGHT_GEAR_OFFSET:
			straightGearAutoTimedOffset();
			break;
		case LEFT_GEAR:
		case RIGHT_GEAR:
		case LEFT_SHOOT:
		case RIGHT_SHOOT:
		case STRAIGHT_SIMPLE:
		default:
			straightAutoSimple();
		}
		runSystems();
		SmartDashboard.putNumber("gyro.angle", Drivetrain.INSTANCE.getAngle());
	}
	
	public void straightAutoSimple(){
		//System.out.println("Running straight auto");
		if(timer.get() <3){
			Drivetrain.INSTANCE.setLeftSpeed(-0.3);
			Drivetrain.INSTANCE.setRightSpeed(-0.3);
		}else if(timer.get() > 3){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}
		return;
	}
	
	public void straightGearAutoTimed(){
		if(timer.get()<3){
			Drivetrain.INSTANCE.setLeftSpeed(-0.3);
			Drivetrain.INSTANCE.setRightSpeed(-0.3);
			gearIntake.ifPresent(GearIntakeSystem::closeTop);
		}else if(timer.get() < 4){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}else if(timer.get() < 5.5){
			gearIntake.ifPresent(GearIntakeSystem::dropGear);
		}else if(timer.get() < 7){
			Drivetrain.INSTANCE.setLeftSpeed(0.3);
			Drivetrain.INSTANCE.setRightSpeed(0.3);
		}else if(timer.get() < 7.5){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}
	}
	
	public void straightGearAutoTimedOffset(){
		double offset = Preferences.getInstance().getDouble("auto.offset", 0)/100;
		if(timer.get()<4){
			gearIntake.ifPresent(GearIntakeSystem::closeTop);
			Drivetrain.INSTANCE.setLeftSpeed(-0.3+offset);
			Drivetrain.INSTANCE.setRightSpeed(-0.3);
		}else if(timer.get() < 4.2){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}else if(timer.get() < 5.5){
			gearIntake.ifPresent(GearIntakeSystem::dropGear);
		}else if(timer.get() < 7){
			Drivetrain.INSTANCE.setLeftSpeed(0.3-offset);
			Drivetrain.INSTANCE.setRightSpeed(0.3);
		}else if(timer.get() < 7.5){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}
	}
	
	public void straightGearLineAutoTimed(){
		if(timer.get()<3){
			angleController.setTarget(0);
			double power = angleController.feedAndGetValue(Drivetrain.INSTANCE.getAngle());
			Drivetrain.INSTANCE.setLeftSpeed(-0.3+(power*0.5));
			Drivetrain.INSTANCE.setRightSpeed(-0.3-(power*0.5));
			gearIntake.ifPresent(GearIntakeSystem::closeTop);
		}else if(timer.get() < 4){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}else if(timer.get() < 5){
			gearIntake.ifPresent(GearIntakeSystem::dropGear);
		}else if(timer.get() < 9){
			angleController.setTarget(0);
			double power = angleController.feedAndGetValue(Drivetrain.INSTANCE.getAngle());
			Drivetrain.INSTANCE.setLeftSpeed(0.3+(power*0.5));
			Drivetrain.INSTANCE.setRightSpeed(0.3-(power*0.5));
		}else if(timer.get() < 9.5){
			Drivetrain.INSTANCE.setLeftSpeed(0);
			Drivetrain.INSTANCE.setRightSpeed(0);
		}else if(timer.get() < 11){
			angleController.setTarget(45);
			double power = angleController.feedAndGetValue(Drivetrain.INSTANCE.getAngle());
			Drivetrain.INSTANCE.setLeftSpeed(power * 0.5);
			Drivetrain.INSTANCE.setRightSpeed(-power * 0.5);
		}else if(timer.get() < 15){
			angleController.setTarget(45);
			double power = angleController.feedAndGetValue(Drivetrain.INSTANCE.getAngle());
			Drivetrain.INSTANCE.setLeftSpeed(-0.3+power);
			Drivetrain.INSTANCE.setRightSpeed(-0.3-power);
		}
	}
	
	
	public void calibrate() {
		System.out.println("Startin auto");
		Drivetrain.INSTANCE.resetEncPos();
		System.out.println("reset enc");
		startLPos = Drivetrain.INSTANCE.getLeftPos();
		startRPos = Drivetrain.INSTANCE.getRightPos();
		currentState++;
	}
	
	public void rotate (double angle) {
		angleController.setTarget(angle);
		double power = angleController.feedAndGetValue(Drivetrain.INSTANCE.getAngle());
		Drivetrain.INSTANCE.setLeftSpeed(power);
		Drivetrain.INSTANCE.setRightSpeed(-power);
		if (Math.abs(angleController.getError()) < 2) {
			currentState++;
		}
	}
	
	public void straightDrive (double dist) {
		double target = getRevPerDist(dist) * pulsePerRev;
		System.out.println("Target: " + target);
		wheel.setTarget(target);
		System.out.println("Pos R: " + (-getR()));
		double speed = wheel.feedAndGetValue(-getR());
		System.out.println("rVolt: "+speed);
		if (speed >= 0) {
			speed = Math.min(speed, .5);
		} else {
			speed = Math.max(speed, -.5);
		}
		Drivetrain.INSTANCE.setLeftSpeed(speed);
		Drivetrain.INSTANCE.setRightSpeed(speed);
		if (Math.abs(wheel.getError()) < 80){
			currentState++;
		}
	}
	
	public void dropGear(){
		gearIntake.ifPresent(GearIntakeSystem::closeTop);
		runSystems();
		Timer.delay(0.25);
		gearIntake.ifPresent(GearIntakeSystem::dropGear);
		runSystems();
		Timer.delay(0.5);
		currentState++;
	}
	
	public void stopDrive(){
		Drivetrain.INSTANCE.setLeftSpeed(0);
		Drivetrain.INSTANCE.setRightSpeed(0);
		currentState++;
	}
	
	public void straightAuto() {
		switch (currentState) {
			case 0:
				calibrate();
			case 1:
				straightDrive(111-36);
				break;
			case 2:
				rotate(0);
				break;
			case 3: 
				break;
			case 4:
				dropGear();
				break;
			case 5:
				straightDrive(-20);
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
		//driveSystem.ifPresent(Subsystem::run);
	}
	public double getRevPerDist(double dist){
		return dist/(4*Math.PI);
	}

}
