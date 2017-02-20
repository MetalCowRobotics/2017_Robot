package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public enum Shooter {

	INSTANCE;
	
	private final CANTalon flywheelMotor, hoodMotor; 
	
	private final Encoder flywheelEncoder;
	
	private Shooter() {
		flywheelMotor = (CANTalon) ComponentBuilder.buildMotor(MotorType.CANTALON, "flywheel.motor.channel", "flywheel.motor.reverse");
		hoodMotor = (CANTalon) ComponentBuilder.buildMotor(MotorType.CANTALON, "hood.motor.channel", "hood.motor.reverse");
		flywheelEncoder = ComponentBuilder.buildEncoder("flywheel.encoder.channel.a", "flywheel.encoder.channel.b");
		flywheelEncoder.setDistancePerPulse(1/100);
	}
	public void setFlywheelSpeed(double speed){
		flywheelMotor.set(speed);
	}
	
	public void setHoodSpeed(double speed){
		hoodMotor.set(speed);
	}
	
	public double getFlywheelEncoderPosition(){
		return flywheelEncoder.getDistance();
	}
//	
//	public double getHoodEncoderPosition(){
//		return hoodMotor.getEncPosition();
//	}
//	
	public double getFlywheelSpeed(){
		return flywheelEncoder.getRate();
	}
//	
//	public double getHoodSpeed(){
//		return hoodMotor.getEncVelocity();
//	}
	
	public double getShooterPower(){
		return flywheelMotor.get();
	}
	
}
