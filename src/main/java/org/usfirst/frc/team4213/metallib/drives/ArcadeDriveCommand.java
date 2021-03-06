package org.usfirst.frc.team4213.metallib.drives;

public class ArcadeDriveCommand implements DualDriveCommand {
	
	double throttle;
	double spin;
	
	public ArcadeDriveCommand(double throttle, double spin){
		if(Math.abs(throttle) < 0.08){
			throttle = 0.01;
		}
		this.throttle = throttle;
		this.spin = spin;
	}
	
	@Override
	public double getLeftVoltage() {
		
		double leftPower;
		
		if (throttle > 0.0) {
			if (spin > 0.0) {
				leftPower = Math.max(throttle, spin);
			} else {
				leftPower = throttle + spin;
			}
		} else {
			if (spin > 0.0) {
				leftPower = -Math.max(-throttle, spin);
			} else {
				leftPower = throttle - spin;
			}
		}
		
		return leftPower;
	}

	@Override
	public double getRightVoltage() {
		double rightPower;
		
		if (throttle > 0.0) {
			if (spin > 0.0) {
				rightPower = throttle - spin;
			} else {
				rightPower = Math.max(throttle, -spin);
			}
		} else {
			if (spin > 0.0) {
				rightPower = throttle + spin;
			} else {
				rightPower = -Math.max(-throttle, -spin);
			}
		}
		
		return rightPower;
	}

}
