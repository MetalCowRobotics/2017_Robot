package org.usfirst.frc.team4213.controllers;

import org.usfirst.frc.team4213.metallib.controllers.CowGamepad;
import org.usfirst.frc.team4213.metallib.controllers.Xbox360Controller;
import org.usfirst.frc.team4213.metallib.drives.DualDriveCommand;
import org.usfirst.frc.team4213.metallib.drives.TankDriveCommand;
import org.usfirst.frc.team4213.systems.DriveSystem;

/**
 * Drive Train Controller
 */
public class DriverController implements Runnable {

    public final static DriverController INSTANCE = new DriverController();

    private static CowGamepad controller;
    
    private static DriveSystem driveSystem;

    private DriverController() {
        driveSystem = DriveSystem.INSTANCE;
    }

	@Override
	public void run() {
		
		double left = controller.getLY();
		double right = controller.getRY();
		
		DualDriveCommand command = new TankDriveCommand(left,right);
		
		driveSystem.setDrive(command);
		
		driveSystem.run();
	}

	public static void setController(CowGamepad controller) {
		 DriverController.controller = controller;
	}
}