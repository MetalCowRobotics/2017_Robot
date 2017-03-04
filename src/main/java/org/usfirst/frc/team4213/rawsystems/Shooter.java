package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public enum Shooter {

	INSTANCE;
	
	private final SpeedController flywheelMotor, hoodMotor; 
	
	private final Encoder hoodEncoder;
	
	private Shooter() {
		flywheelMotor = ComponentBuilder.buildMotor(MotorType.JAGUAR, "flywheel.motor.channel", "flywheel.motor.reverse");
		hoodMotor = ComponentBuilder.buildMotor(MotorType.JAGUAR, "hood.motor.channel", "hood.motor.reverse");
		hoodEncoder = new Encoder(2,3);
		//flywheelEncoder.setDistancePerPulse(1/100);
	}
	public void setFlywheelSpeed(double speed){
		flywheelMotor.set(speed);
	}
	
	public void setHoodSpeed(double speed){
		hoodMotor.set(speed);
	}
	
//	public double getFlywheelEncoderPosition(){
//		return flywheelEncoder.getDistance();
//	}
//	
	public double getHoodEncoderPosition(){
		return hoodEncoder.getDistance();
	}
//	
//	public double getFlywheelSpeed(){
//		return flywheelEncoder.getRate();
//	}
//	
	public double getHoodSpeed(){
		return hoodEncoder.getRate();
	}
	
	public double getShooterPower(){
		return flywheelMotor.get();
	}
	
}
