package org.usfirst.frc.team4213.metallib;

import org.usfirst.frc.team4213.metallib.util.KVStore;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SD540;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

public class ComponentBuilder {
	
	static KVStore store = PropertyStore.INSTANCE;
	
	public static SpeedController buildMotor(MotorType type, String motorChannel){
		int port = store.getInt(motorChannel);
		
		try{
			switch(type){
			case JAGUAR: 
				return new Jaguar(port);
			case SD540:
				return new SD540(port);
			case SPARK:
				return new Spark(port);
			case TALON:
				return new Talon(port);
			case CANTALON:
				return new CANTalon(port);
			case TALONSRX:
				return new TalonSRX(port);
			case VICTOR:
				return new Victor(port);
			case VICTORSP:
				return new VictorSP(port);
			default:
				return null;
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		
		return null;
	}
	
	public static SpeedController buildMotor(MotorType type, String motorChannel, String reverseProperty){
		SpeedController motor = buildMotor(type,motorChannel);
		boolean inverted = store.getBool(reverseProperty);
		
		if(motor != null){
			motor.setInverted(inverted);
		}
		
		return motor;
	}
	
	public enum MotorType {
		JAGUAR,SD540,SPARK,TALON,CANTALON, TALONSRX,VICTOR,VICTORSP;
	}
	
	public static Encoder buildEncoder(String channelA, String channelB){
		int portA = store.getInt(channelA);
		int portB = store.getInt(channelB);
		
		try{
			return new Encoder(portA, portB);
		}catch ( Exception ex ){
			System.err.println(ex.getMessage());
		}
		
		return null;
	}
	
	public static DoubleSolenoid buildDoubleSolenoid(String forwardChannel, String reverseChannel){
		int forwardPort = store.getInt(forwardChannel);
		int reversePort = store.getInt(reverseChannel);
		
		try {
			return new DoubleSolenoid(forwardPort, reversePort);
		}catch ( Exception ex ) {
			System.err.println(ex.getMessage());
		}
		
		return null;
	}
	
	public static DigitalInput buildDigitalIn(String channel){
		int port = store.getInt(channel);
		
		try {
			return new DigitalInput(port);
		} catch ( Exception ex ){
			System.err.println(ex.getMessage());
		}
		
		return null;
	}
	
	public static AnalogInput buildAnalogIn(String channel){
		int port = store.getInt(channel);
		
		try {
			return new AnalogInput(port);
		} catch ( Exception ex ) {
			System.err.println(ex.getMessage());
		}
		
		return null;
	}
	
	public static void setStore(KVStore store){
		ComponentBuilder.store = store;
	}
}
