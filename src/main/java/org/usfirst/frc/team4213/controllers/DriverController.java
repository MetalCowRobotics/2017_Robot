package org.usfirst.frc.team4213.controllers;

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
    
    private static DriveSystem driveSystem;
    
    private DriveMode mode;

    public DriverController(CowGamepad controller, DriveSystem driveSystem ) {
        DriverController.controller = controller;
    	DriverController.driveSystem = driveSystem;
    	mode = DriveMode.TANK;
    }

	@Override
	public void run() {
		
		double left = controller.getLY();
		double right;
		
		if(controller.getButton(GamepadButton.A)){
			driveSystem.brake();
		}else{
			driveSystem.drive();
		}
		
		if(controller.getButton(GamepadButton.DPADLEFT)){
			mode = DriveMode.TANK;
		}else if (controller.getButton(GamepadButton.DPADRIGHT)){
			mode = DriveMode.ARCADE;
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
		
		driveSystem.setDrive(command);
		driveSystem.run();
	}

	public static void setController(CowGamepad controller) {
		 DriverController.controller = controller;
	}
}