/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team4213.metallib.controlloops;

import java.util.Enumeration;
import java.util.Vector;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An ErrorController implemented with a simple
 * Proportional-(limited)Integral-Derivative controller
 * 
 * @author hughest1
 */
public class PIDController_Discrete extends ErrorController {

	public double kp, ki, kd, integralLifespan;

	// Previous position data
	Vector<PositionDataPoint> positionData = new Vector<PositionDataPoint>();

	// One of the data points for positions
	class PositionDataPoint {
		public double value;
		public Timer time;

		/**
		 * Creates a new PositionDataPoint and starts its timer.
		 * 
		 * @param val
		 *            the value at time of creation
		 */
		public PositionDataPoint(double val) {
			this.value = val;
			time = new Timer();
			time.start();
		}
	}

	public PIDController_Discrete(String name, double kp, double ki, double kd, double integralLifespan) {
		super(name);
		this.kp = kp;
		this.ki = ki;
		this.integralLifespan = integralLifespan;
		this.kd = kd;
		positionData.addElement(new PositionDataPoint(0));
	}
	
	public double getTarget(){
		return target;
	}
	
	public void bumpTarget(double by) {
		target+=by;
	}
	
	public double getError() {
		try{
			return target-((PositionDataPoint)positionData.lastElement()).value;
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

	
	public double feedAndGetValue(double currentPosition, double p2) {		
		kp = SmartDashboard.getNumber(name+".kp",kp);
		ki = SmartDashboard.getNumber(name+".ki",ki);
		kd = SmartDashboard.getNumber(name+".kd",kd);
		integralLifespan = SmartDashboard.getNumber(name+".iLife",integralLifespan);
		
		positionData.addElement(new PositionDataPoint(currentPosition));
		deleteOutdatedPoints();
		
		return computePID(currentPosition, computePositionIntegral(), computePositionDerivative());
		
	}
	
	double computePID(double current, double integral, double derivative){
		return (target - current) * kp / 1000 + integral * ki / 1000 + derivative * kd / 1000; 
	}
	
//	double computePositionDerivative(){
//		if(positionData.size() < 2){
//			return 0;
//		}
//		return (positionData.get(positionData.size()-1).value-positionData.get(positionData.size()-2).value)/(positionData.get(positionData.size()-1).time.get()-positionData.get(positionData.size()-2).time.get());
//	}
	
	double computePositionIntegral(){
		
		if(positionData.size() < 2){
			return 0;
		}
		
		final PositionDataPoint lastPosition = positionData.firstElement();
		return positionData.stream()
					.mapToDouble((position)-> {
						return (target - position.value) * (lastPosition.time.get() - position.time.get());
					})
					.sum();
	}
	
	double computePositionDerivative(){
		
		if(positionData.size() < 2){
			return 0;
		}
		
		final PositionDataPoint secondLastPoint = positionData.get(positionData.size());
		final PositionDataPoint lastPoint = positionData.lastElement();
		return (secondLastPoint.value - lastPoint.value) / secondLastPoint.time.get();
	}
	
	void deleteOutdatedPoints(){
			positionData.removeIf((point)->{
				return point.time.get() > integralLifespan;
			});
				
	}
}
