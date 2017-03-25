package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public enum Shooter {

	INSTANCE;
	
	private final SpeedController flywheelMotor, hoodMotor; 
	
	private final Encoder hoodEncoder, flywheelEncoder;
	
	private Shooter() {
		flywheelMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "flywheel.motor.channel", "flywheel.motor.reverse");
		hoodMotor = ComponentBuilder.buildMotor(MotorType.CANTALON, "hood.motor.channel", "hood.motor.reverse");
		hoodEncoder = new Encoder(4,5);
		hoodEncoder.setDistancePerPulse(1/PropertyStore.INSTANCE.getDouble("hood.cpd"));
		flywheelEncoder = new Encoder(0,1);
		//flywheelEncoder.setDistancePerPulse(1/100);
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
	public double getHoodEncoderPosition(){
		return hoodEncoder.getDistance();
	}
//	
	public double getFlywheelSpeed(){
		return flywheelEncoder.getRate();
	}
//	
	
	public double getFlywheelEncPeriod(){
		return flywheelEncoder.getPeriod();
	}
	public double getHoodSpeed(){
		return hoodEncoder.getRate();
	}
	
	public double getShooterPower(){
		return flywheelMotor.get();
	}
	
}
