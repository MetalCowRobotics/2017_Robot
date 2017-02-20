package org.usfirst.frc.team4213.controllers;

import java.util.Optional;

import org.usfirst.frc.team4213.metallib.controllers.CowGamepad;
import org.usfirst.frc.team4213.metallib.controllers.GamepadButton;
import org.usfirst.frc.team4213.metallib.controllers.Xbox360Controller;
import org.usfirst.frc.team4213.metallib.drives.ArcadeDriveCommand;
import org.usfirst.frc.team4213.metallib.drives.DualDriveCommand;
import org.usfirst.frc.team4213.metallib.drives.TankDriveCommand;
import org.usfirst.frc.team4213.systems.DriveSystem;

/**
 * Drive Train Controller
 */
public class DriverController implements Runnable {

	public enum DriveMode {
		ARCADE, TANK;
	}
	
    private static CowGamepad controller;
    
    private static Optional<DriveSystem> driveSystem;
    
    private DriveMode mode;

    public DriverController(CowGamepad controller, DriveSystem driveSystem ) {
        DriverController.controller = controller;
    	DriverController.driveSystem = Optional.ofNullable(driveSystem);
    	mode = DriveMode.TANK;
    }

	@Override
	public void run() {
		
		double left = controller.getLY();
		double right;
		
		if(controller.getButton(GamepadButton.A)){
			driveSystem.ifPresent(DriveSystem::brake);
		}else{
			driveSystem.ifPresent(DriveSystem::drive);
		}
		
		if(controller.getButton(GamepadButton.DPADLEFT)){
			mode = DriveMode.TANK;
		}else if (controller.getButton(GamepadButton.DPADRIGHT)){
			mode = DriveMode.ARCADE;
		}
		
		if(controller.getButton(GamepadButton.RT)){
			driveSystem.ifPresent(DriveSystem::setFast);
		}else if(controller.getButton(GamepadButton.LT)){
			driveSystem.ifPresent(DriveSystem::setSlow);
		}else {
			driveSystem.ifPresent(DriveSystem::setNormal);
		}
		
		DualDriveCommand command;
		
		switch(mode){
		case ARCADE:
			right = controller.getRX();		
			command = new ArcadeDriveCommand(left,right);
			break;
		case TANK:
		default:
			right = controller.getRY();
			command = new TankDriveCommand(left,right);
		}
		
		final DualDriveCommand finalCommand = command;
		
		driveSystem.ifPresent(drive -> drive.setDrive(finalCommand));
		driveSystem.ifPresent(DriveSystem::run);
	}

	public static void setController(CowGamepad controller) {
		 DriverController.controller = controller;
	}
}