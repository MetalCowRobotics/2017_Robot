/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team4213.metallib.controlloops;

import java.util.Enumeration;
import java.util.Vector;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class PIDController extends ErrorController {

	public double kp, ki, kd, integralLifespan;
	public boolean tuneable;
	
	// Previous position data
	Vector<PositionDataPoint> positionData = new Vector<PositionDataPoint>();

	// One of the data points for positions
	private class PositionDataPoint {
		public double value;
		public Timer time;

		public PositionDataPoint(double val) {
			this.value = val;
			time = new Timer();
			time.reset();
			time.start();
		}
	}


	public PIDController(String name, double kp, double ki, double kd, double integralLifespan,boolean tuneable) {
		super(name);
		this.kp = kp;
		this.ki = ki;
		this.integralLifespan = integralLifespan;
		this.kd = kd;
		this.tuneable = tuneable;
		positionData.add(new PositionDataPoint(0));
	}
	
	public double getTarget(){
		return target;
	}
	
	public void bumpTarget(double by) {
		target+=by;
	}
	
	public double getError() {
		try{
			return target-positionData.lastElement().value;
		} catch(Exception e){
			return 0;
		}
		
	}

	/**
	 * Resets the past data points to nothing.
	 */
	public void reset() {
		positionData = new Vector<PositionDataPoint>();
	}

	@Override
	public double feedAndGetValue(double currentValue) {
		// Read constants values off of the CowDash
		double newKp = kp/1000.0;
		double newKi = ki/1000.0;
		double newKd = kd/1000.0;
		if(tuneable){
			newKp = Preferences.getInstance().getDouble(name+".kp",kp)/1000.0;
			newKi = Preferences.getInstance().getDouble(name+".ki",ki)/1000.0;
			newKd = Preferences.getInstance().getDouble(name+".kd",kd)/1000.0;
			integralLifespan = Preferences.getInstance().getDouble(name+".iLife",integralLifespan);
		}

//		System.out.println(name + " kp is " + newKp);
//		System.out.println(name + " ki is " + newKi);
//		System.out.println(name + " kd is " + newKd);
//		System.out.println(name + " life is " + integralLifespan);
		// Current error is target minus current value
		PositionDataPoint thisValue = new PositionDataPoint(currentValue);

		double integral = 0;
		double derivative = 0;
		// If we have data on the past, integral and derivative can be computed.
		if (positionData.size() > 2) {
			// Compute integral by summing up all errors contained within the
			// positionData, weighted by time inbetween each.
			Enumeration<PositionDataPoint> e = positionData.elements();
			PositionDataPoint lastElement = e.nextElement();
			while (e.hasMoreElements()) {
				PositionDataPoint currentElement = e.nextElement();
				integral += (target - currentElement.value) * (lastElement.time.get() - currentElement.time.get());
			}

			// Compute derivative by subtracting current value from last
			// recorded, divided by time inbetween.
			PositionDataPoint lastData = positionData.lastElement();
			derivative = (lastData.value - thisValue.value) / lastData.time.get();
		}

		// Add this data point to the position data history
		positionData.addElement(thisValue);
		// Trim old entries from the position data history
		while (positionData.firstElement().time.get() > integralLifespan)
			positionData.removeElementAt(0);

		// Log info about the integral and derivative
		// Return summed terms: Proportional, Integral, Derivative
		return (target - thisValue.value) * newKp + integral * newKi + derivative * newKd;

	}
}
