package org.usfirst.frc.team4213.systems;

import org.usfirst.frc.team4213.metallib.drives.DualDriveCommand;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;

/**
 * Created by aaron on 11/18/16.
 */
public class DriveSystem implements Subsystem {

    public final static DriveSystem INSTANCE = new DriveSystem();
    
    DualDriveCommand command;
    
    private DriveSystem () {}
    
    public void setDrive(DualDriveCommand command){
    	this.command = command;
    }
    
    private double scaleFunction (double wheel) {
		double wheelNonLinearity = 0.5;
		return Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
				/ Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	}
    
    private double scalePower(double power){
    	for(int i = 0; i<2; i++){
    		power = scaleFunction(power);
    	}
    	return power;
    }
    
	@Override
	public void run() {	
		
		double leftPower = scalePower(command.getLeftVoltage());
		double rightPower = scalePower(command.getRightVoltage());
		
		Drivetrain.INSTANCE.setLeftSpeed(leftPower);
		Drivetrain.INSTANCE.setRightSpeed(rightPower);
	}
	
}