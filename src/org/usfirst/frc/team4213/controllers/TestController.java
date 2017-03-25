package org.usfirst.frc.team4213.controllers;

import org.usfirst.frc.team4213.metallib.gamepad.CowGamepad;
import org.usfirst.frc.team4213.rawsystems.Shooter;



import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class TestController implements Runnable {
	
	CowGamepad driver;
	CowGamepad operator;
	
	public TestController(CowGamepad driver, CowGamepad operator){
		this.driver = driver;
		this.operator = operator;
	}
	
	@Override
	public void run() {
		System.out.println("Hood Speed is 8");
		Shooter.INSTANCE.setHoodSpeed(0.8);
	}
}
