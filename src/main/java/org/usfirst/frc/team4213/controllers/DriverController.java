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

    private static CowGamepad controller;
    
    private static DriveSystem driveSystem;

    public DriverController(CowGamepad controller, DriveSystem driveSystem ) {
        DriverController.controller = controller;
    	DriverController.driveSystem = driveSystem;
    }

	@Override
	public void run() {
		
		double left = controller.getLY();
		double right = controller.getRX();
		
		DualDriveCommand command = new ArcadeDriveCommand(left,right);
		
		driveSystem.setDrive(command);
		
		if(controller.getButton(GamepadButton.A)){
			driveSystem.brake();
		}else{
			driveSystem.drive();
		}
		
		
		driveSystem.run();
	}

	public static void setController(CowGamepad controller) {
		 DriverController.controller = controller;
	}
}