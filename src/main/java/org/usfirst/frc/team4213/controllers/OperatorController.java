package org.usfirst.frc.team4213.controllers;

import org.usfirst.frc.team4213.metallib.controllers.CowGamepad;
import org.usfirst.frc.team4213.metallib.controllers.GamepadButton;
import org.usfirst.frc.team4213.systems.ClimberSystem;
import org.usfirst.frc.team4213.systems.FeederSystem;
import org.usfirst.frc.team4213.systems.GearIntakeSystem;
import org.usfirst.frc.team4213.systems.RollerIntakeSystem;
import org.usfirst.frc.team4213.systems.ShooterSystem;

public class OperatorController implements Runnable {

	// Create a reference to a controller so we can read buttons
	private CowGamepad controller;

	private static ClimberSystem climber;
	private static ShooterSystem shooter;
	private static GearIntakeSystem gearIntake;
	private static FeederSystem feeder;
	private static RollerIntakeSystem rollerIntake;

	public OperatorController(CowGamepad controller) {
		this.controller = controller;
		
	}

	@Override
	public void run() {
		topIntake();
		climb();
		gearHold();
		moveHood();
		shoot();
		idleIntake();
		runSystems();
	}
	
	
	public void topIntake(){
		if (controller.getButton(GamepadButton.LB)) {
			gearIntake.openTop();
		} else {
			gearIntake.closeTop();
		}
	}
	
	public void climb(){
		if (controller.getButton(GamepadButton.DPADUP)) {
			climber.climb();
		} else {
			climber.idle();
		}
	}
	
	public void gearHold(){
		if (controller.getButton(GamepadButton.LT)) {
			gearIntake.dropGear();
		} else {
			gearIntake.holdGear();
		}
	}
	
	public void moveHood() {
		if (controller.getButton(GamepadButton.Y)) {
			shooter.bumpHoodAngle(0.5);
		}
		else if (controller.getButton(GamepadButton.A)) {
			shooter.bumpHoodAngle(-0.5);
		}
	}
	
	public void shoot(){
		if (controller.getButton(GamepadButton.RT)) {
			shooter.shoot();
			if (controller.getButton(GamepadButton.RB)) {
				feeder.feed();
			} else {
				feeder.idle();
			}
		} else {
			shooter.idle();
			feeder.idle();
		}
	}
	
	public void idleIntake(){
		if (controller.getButton(GamepadButton.DPADUP)) {
			rollerIntake.idle();
		} else if (controller.getButton(GamepadButton.RT)) {
			rollerIntake.idle();
		} else {
			rollerIntake.intake();
		}
	}
	
	public void runSystems(){
		gearIntake.run();
		shooter.run();
		climber.run();
		rollerIntake.run();
		feeder.run();
		
	}

}
