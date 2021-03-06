package org.usfirst.frc.team4213.metallib.controlloops;

public class TBHController extends ErrorController{
	
	private double lastPWM;
	private double desiredRPS;
	private double lastError;
	private double ki;
	private double kH;
	
	// Throw Half Back Controller ( Google The Name )
	
	public TBHController(String name, Double ki, Double kh){
		super(name);
		this.kH = kh;
		this.ki = ki/1000;
		lastPWM = 0;
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
		final double newError = desiredRPS - currentRPS;
		final double newPWM;
		
		if(newError*lastError < 0){
			newPWM = (currentPWM+lastPWM)/kH;
		}else if(newError * lastError > 0){
			newPWM = (lastError*ki) +currentPWM;
		}else{
			newPWM = currentPWM;
		}

		lastError = newError;
		lastPWM = currentPWM;
		return newPWM;
	}
	
}
