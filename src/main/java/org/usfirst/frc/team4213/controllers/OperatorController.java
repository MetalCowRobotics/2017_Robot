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

	private static ShooterSystem hoodPID;
	public OperatorController(CowGamepad controller) {
		this.controller = controller;

	}

	@Override
	public void run() {
		//TODO refactor this to methods
		
		
		if (controller.getButton(GamepadButton.DPADUP)) {
			rollerIntake.idle();
		} else if (controller.getButton(GamepadButton.RT)) {
			rollerIntake.idle();
		} else {
			rollerIntake.intake();
		}
		rollerIntake.run();

		if (controller.getButton(GamepadButton.DPADUP)) {
			climber.climb();
		} else {
			climber.idle();
		}
		climber.run();

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
		if (controller.getButton(GamepadButton.Y)) {
			hoodPID.bumpHoodAngle(0.5);
		}
		else if (controller.getButton(GamepadButton.A)) {
			hoodPID.bumpHoodAngle(-0.5);
		}
		else {
			hoodPID.bumpHoodAngle(0);
		}
		
		shooter.run();

		if (controller.getButton(GamepadButton.LB)) {
			gearIntake.openTop();
		} else {
			gearIntake.closeTop();
		}

		if (controller.getButton(GamepadButton.LT)) {
			gearIntake.dropGear();
		} else {
			gearIntake.holdGear();
		}
		gearIntake.run();
		
	}
	

}
