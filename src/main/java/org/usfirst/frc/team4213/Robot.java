package org.usfirst.frc.team4213;

import org.usfirst.frc.team4213.controllers.DriverController;
import org.usfirst.frc.team4213.controllers.OperatorController;
import org.usfirst.frc.team4213.metallib.MetalRobot;
import org.usfirst.frc.team4213.metallib.controllers.Xbox360Controller;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.systems.ClimberSystem;
import org.usfirst.frc.team4213.systems.DriveSystem;
import org.usfirst.frc.team4213.systems.FeederSystem;
import org.usfirst.frc.team4213.systems.GearIntakeSystem;
import org.usfirst.frc.team4213.systems.RollerIntakeSystem;
import org.usfirst.frc.team4213.systems.ShooterSystem;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends MetalRobot {

	//Gamepads
	Xbox360Controller driverGamepad = new Xbox360Controller(0);
	Xbox360Controller operatorGamepad = new Xbox360Controller(1);

	
	//Systems
	DriveSystem drivetrain;
	ClimberSystem climber;
	FeederSystem feeder;
	GearIntakeSystem gearIntake;
	RollerIntakeSystem rollerIntake;
	ShooterSystem shooter;
	
	// Controllers
	DriverController driver;
	OperatorController operator;
	
	{	
		if(PropertyStore.INSTANCE.getBool("drivesystem.enabled")){
			drivetrain = new DriveSystem();
			System.out.println("Drivetrain Initialized");
		}else {
			System.out.println("Drivetrain Disabled");
		}
		if(PropertyStore.INSTANCE.getBool("climbersystem.enabled")){
			climber = new ClimberSystem();
			System.out.println("Climber Initialized");
		}else {
			System.out.println("Climber Disabled");
		}
		if(PropertyStore.INSTANCE.getBool("feedersystem.enabled")){
			feeder = new FeederSystem();
			System.out.println("Feeder Initialized");
		}else {
			System.out.println("Feeder Disabled");
		}
		if(PropertyStore.INSTANCE.getBool("gearintakesystem.enabled")){
			gearIntake = new GearIntakeSystem();
			System.out.println("Gear Intake Initialized");
		}else {
			System.out.println("Gear Intake Disabled");
		}
		if(PropertyStore.INSTANCE.getBool("rollerintakesystem.enabled")){
			rollerIntake = new RollerIntakeSystem();
			System.out.println("Roller Intake Initialized");
		}else {
			System.out.println("Roller Intake Disabled");
		}
		if(PropertyStore.INSTANCE.getBool("shootersystem.enabled")){
			shooter = new ShooterSystem();
			System.out.println("Shooter Initialized");
		}else {
			System.out.println("Shooter Disabled");
		}
		
		driver = new DriverController(driverGamepad,drivetrain);
		operator = new OperatorController(operatorGamepad, climber, shooter, gearIntake, feeder, rollerIntake);		
		
	}
	
    @Override
    public void robotInit() {
        registerTasks();
    }
    
    public void registerTasks() {
    	addTask(RobotMode.TELEOP, driver);
    	addTask(RobotMode.TELEOP, operator);
    }
}