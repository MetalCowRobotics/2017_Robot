package org.usfirst.frc.team4213.controllers;

import java.util.Optional;

import org.usfirst.frc.team4213.metallib.drives.ArcadeDriveCommand;
import org.usfirst.frc.team4213.metallib.drives.DualDriveCommand;
import org.usfirst.frc.team4213.metallib.drives.TankDriveCommand;
import org.usfirst.frc.team4213.metallib.gamepad.CowGamepad;
import org.usfirst.frc.team4213.metallib.gamepad.GamepadButton;
import org.usfirst.frc.team4213.metallib.gamepad.Xbox360Controller;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;
import org.usfirst.frc.team4213.systems.DriveSystem;

/**
 * Drive Train Controller
 */
public class DriverController implements Runnable {

	public enum DriveMode {
		ARCADE, TANK, DEVIN;
	}
	
    private static CowGamepad controller;
    
    private static Optional<DriveSystem> driveSystem;
    
    private DriveMode mode;

    public DriverController(CowGamepad controller, DriveSystem driveSystem) {
        DriverController.controller  = controller;
    	DriverController.driveSystem = Optional.ofNullable(driveSystem);
    	mode = DriveMode.TANK;
    }

	@Override
	public void run() {
		double left;
		double right;
		
//		if (controller.getButton(GamepadButton.B)) {
//			driveSystem.ifPresent(DriveSystem::brake);
//		} else {
			driveSystem.ifPresent(DriveSystem::drive);
//		}
		
		if (controller.getButton(GamepadButton.DPADLEFT)) {
			mode = DriveMode.TANK;
		} else if (controller.getButton(GamepadButton.DPADRIGHT)) {
			mode = DriveMode.ARCADE;
		} else if (controller.getButton(GamepadButton.DPADUP)) {
			mode = DriveMode.DEVIN;
		}
		
//		if (controller.getButton(GamepadButton.RT)) {
//			driveSystem.ifPresent(DriveSystem::setFast);
//		} else if (controller.getButton(GamepadButton.RB)) {
//			driveSystem.ifPresent(DriveSystem::setSlow);
//		} else {
//			driveSystem.ifPresent(DriveSystem::setNormal);
//		}
//		
//		if (controller.getButtonToggled(GamepadButton.LT)) {
//			driveSystem.ifPresent(DriveSystem::setForward);
//		} else {
//			driveSystem.ifPresent(DriveSystem::setReverse);
//		}
		
		if (controller.getButton(GamepadButton.RB)) {
			driveSystem.ifPresent(DriveSystem::setFast);
		} else if (controller.getButton(GamepadButton.LB)) {
			driveSystem.ifPresent(DriveSystem::setSlow);
		} else {
			driveSystem.ifPresent(DriveSystem::setNormal);
		}
		
		if (controller.getButtonToggled(GamepadButton.Y)) {
			driveSystem.ifPresent(DriveSystem::setForward);
		} else {
			driveSystem.ifPresent(DriveSystem::setReverse);
		}
		
		DualDriveCommand command;
		
		switch (mode) {
		case ARCADE:
			left  = controller.getLY();
			right = controller.getRX();		
			command = new ArcadeDriveCommand(left, right);
			break;
		case DEVIN:
			left  = ((Xbox360Controller)controller).getRT() - ((Xbox360Controller)controller).getLT();
			right = controller.getLX();
			command = new ArcadeDriveCommand(left, right);
			break;
		case TANK:
		default:
			left  = controller.getLY();
			right = controller.getRY();
			if(controller.getButton(GamepadButton.A)){
				right = left;
			}
			command = new TankDriveCommand(left, right);
		}
		
		final DualDriveCommand finalCommand = command;
		
		driveSystem.ifPresent(drive -> drive.setDrive(finalCommand));
		driveSystem.ifPresent(DriveSystem::run);
	}

	public static void setController (CowGamepad controller) {
		 DriverController.controller = controller;
	}
}