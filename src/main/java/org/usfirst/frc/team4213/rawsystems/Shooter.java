package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.CANTalon;

public enum Shooter {

	INSTANCE;
	
	private final CANTalon flywheelMotor, hoodMotor; 
		
	private Shooter() {
		flywheelMotor = (CANTalon) ComponentBuilder.buildMotor(MotorType.CANTALON, "flywheel.motor.channel", "flywheel.motor.reverse");
		hoodMotor = (CANTalon) ComponentBuilder.buildMotor(MotorType.CANTALON, "hood.motor.channel", "hood.motor.reverse");
	}
	public void setFlywheelSpeed(double speed){
		flywheelMotor.set(speed);
	}
	
	public void setHoodSpeed(double speed){
		hoodMotor.set(speed);
	}
	
	public double getFlywheelEncoderPosition(){
		return flywheelMotor.getEncPosition(); 
	}
	
	public double getHoodEncoderPosition(){
		return hoodMotor.getEncPosition();
	}
	
	public double getFlywheelSpeed(){
		return flywheelMotor.getEncVelocity();
	}
	
	public double getHoodSpeed(){
		return hoodMotor.getEncVelocity();
	}
	
	public double getShooterPower(){
		return flywheelMotor.get();
	}
	
}
