package org.usfirst.frc.team4213.controllers;

import java.util.Optional;

import org.usfirst.frc.team4213.metallib.controllers.CowGamepad;
import org.usfirst.frc.team4213.metallib.controllers.GamepadButton;
import org.usfirst.frc.team4213.systems.ClimberSystem;
import org.usfirst.frc.team4213.systems.FeederSystem;
import org.usfirst.frc.team4213.systems.GearIntakeSystem;
import org.usfirst.frc.team4213.systems.RollerIntakeSystem;
import org.usfirst.frc.team4213.systems.ShooterSystem;
import org.usfirst.frc.team4213.systems.Subsystem;

public class OperatorController implements Runnable {

	// Create a reference to a controller so we can read buttons
	private CowGamepad controller;

	private static Optional<ClimberSystem> climber;
	private static Optional<ShooterSystem> shooter;
	private static Optional<GearIntakeSystem> gearIntake;
	private static Optional<FeederSystem> feeder;
	private static Optional<RollerIntakeSystem> rollerIntake;

	public OperatorController(CowGamepad controller, ClimberSystem climber, ShooterSystem shooter, GearIntakeSystem gearIntake, FeederSystem feeder, RollerIntakeSystem rollerIntake) {
		this.controller = controller;
		OperatorController.climber = Optional.ofNullable(climber);
		OperatorController.shooter = Optional.ofNullable(shooter);
		OperatorController.gearIntake = Optional.ofNullable(gearIntake);
		OperatorController.feeder = Optional.ofNullable(feeder);
		OperatorController.rollerIntake = Optional.ofNullable(rollerIntake);
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
			gearIntake.ifPresent(GearIntakeSystem::openTop);
		} else {
			gearIntake.ifPresent(GearIntakeSystem::closeTop);
		}
	}
	
	public void climb(){
		if (controller.getButton(GamepadButton.DPADUP)) {
			climber.ifPresent(ClimberSystem::climb);
		} else {
			climber.ifPresent(ClimberSystem::idle);
		}
	}
	
	public void gearHold(){
		if (controller.getButton(GamepadButton.LT)) {
			gearIntake.ifPresent(GearIntakeSystem::dropGear);
		} else {
			gearIntake.ifPresent(GearIntakeSystem::holdGear);
		}
	}
	
	public void moveHood() {
		if (controller.getButton(GamepadButton.Y)) {
			//shooter.ifPresent(shooter -> shooter.bumpHoodAngle(0.5));
			shooter.ifPresent(ShooterSystem::bumpUp);
		}
		else if (controller.getButton(GamepadButton.A)) {
			//shooter.ifPresent(shooter -> shooter.bumpHoodAngle(-0.5));
			shooter.ifPresent(ShooterSystem::bumpDown);
		}else {
			shooter.ifPresent(ShooterSystem::noBump);
		}
	}
	
	public void shoot(){
		if (controller.getButton(GamepadButton.RT)) {
			shooter.ifPresent(ShooterSystem::shoot);
			if (controller.getButton(GamepadButton.RB)) {
				feeder.ifPresent(FeederSystem::feed);
			} else {
				feeder.ifPresent(FeederSystem::idle);
			}
		} else {
			shooter.ifPresent(ShooterSystem::idle);
			feeder.ifPresent(FeederSystem::idle);
		}
	}
	
	public void idleIntake(){
		if (controller.getButton(GamepadButton.DPADUP)) {
			rollerIntake.ifPresent(RollerIntakeSystem::idle);
		} else if (controller.getButton(GamepadButton.DPADRIGHT)){
			rollerIntake.ifPresent(RollerIntakeSystem::idle);
		} else if (controller.getButton(GamepadButton.DPADLEFT)){
			rollerIntake.ifPresent(RollerIntakeSystem::intake);
		}
	}
	
	
	public void runSystems(){
		gearIntake.ifPresent(Subsystem::run);
		shooter.ifPresent(Subsystem::run);
		climber.ifPresent(Subsystem::run);
		rollerIntake.ifPresent(Subsystem::run);
		feeder.ifPresent(Subsystem::run);
		
	}

}
