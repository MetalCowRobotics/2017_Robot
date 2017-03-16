package org.usfirst.frc.team4213.metallib.controlloops;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TBHController {
	
	String name;
	private double H0;
	private double desiredRPS;
	private double lastError;
	private double lastPWM;
	private double ki;
//	private double kh;
	boolean tuneable;
	// Throw Half Back Controller ( Google The Name )
	
	public TBHController(String name, Double ki, boolean tuneable){
		this.name = name;
		this.ki = ki;
		this.tuneable = tuneable;
		H0 = 0;
	}
	public void setTarget(double rps){
		desiredRPS = rps;
	}

	public double feedAndGetValue(double currentRPS, double currentPWM) {
		
		if(desiredRPS == 0){
			return 0;
		}
		
		if(lastError == 0){
			lastError =  desiredRPS - currentRPS;
		}

		return computePower(currentRPS, currentPWM);
	}
	
	double computePower(double currentRPS, double currentPWM){
		
		double newKi = ki/1000.0;
		if(tuneable){
			newKi = Preferences.getInstance().getDouble(name + ".ki",ki)/1000.0;
		}
		System.out.println("KI IS " + newKi);
		final double newError = desiredRPS - currentRPS;
		
		double output = lastPWM + (newKi*newError);
		
		if(Math.signum(newError) != Math.signum(lastError)){
			H0 = output = (output + H0) / 2;
		}
//		double newPWM=H0;
//		final double newKh =  SmartDashboard.getNumber(name + ".kh",kh)/100.0;
//		System.out.println("kh is" + newKh);
//				
//		newPWM+=newError*newKi;
//		if(newError*lastError < 0){
//			newPWM = (H0+newPWM)/newKh;
//			H0 = newPWM;
//		}
//		
//		currentPWM=newPWM;
//		return newPWM;
		
		lastPWM = currentPWM;
		lastError = newError;
		
		return output;
	}
	
}
