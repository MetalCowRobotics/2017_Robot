package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	public final static Shooter INSTANCE = new Shooter();
	
	private final SpeedController flywheelMotor, hoodMotor; 
	
	private final Encoder hoodEncoder, flywheelEncoder; 
	
	private Shooter() {
		flywheelMotor = new CANTalon(Integer.parseInt(System.getProperty("flywheel.motor.channel")));
		hoodMotor = new CANTalon(Integer.parseInt(System.getProperty("hood.motor.channel")));
	
		flywheelEncoder = new Encoder(Integer.parseInt(System.getProperty("flywheel.endcoder.channel.a")),Integer.parseInt(System.getProperty("flywheel.endcoder.channel.b"))); 
		hoodEncoder = new Encoder(Integer.parseInt(System.getProperty("hood.encoder.channel.a")),Integer.parseInt(System.getProperty("hood.encoder.channel.b")));
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
	
	public double getHoodEncoderPosition(){
		return hoodEncoder.getDistance();
	}
	
	public double getFlywheelSpeed(){
		return flywheelEncoder.getRate();
	}
	
	public double getHoodSpeed(){
		return hoodEncoder.getRate();
	}
	
	public double getShooterPower(){
		return flywheelMotor.get();
	}
	
}
