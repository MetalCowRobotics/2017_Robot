package org.usfirst.frc.team4213.metallib.drives;

public class TankDriveCommand implements DualDriveCommand {
	
	double leftSpeed;
	double rightSpeed;
	
	public TankDriveCommand(double leftSpeed, double rightSpeed){
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}
	
	@Override
	public double getLeftVoltage() {
		return leftSpeed;
	}

	@Override
	public double getRightVoltage() {
		return rightSpeed;
	}

}
