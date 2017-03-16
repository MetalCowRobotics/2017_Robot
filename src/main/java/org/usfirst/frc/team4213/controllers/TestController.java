package org.usfirst.frc.team4213.controllers;

import org.usfirst.frc.team4213.metallib.controllers.CowGamepad;
import org.usfirst.frc.team4213.rawsystems.Shooter;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class TestController implements Runnable {
	
	CowGamepad driver;
	CowGamepad operator;
	ADXRS450_Gyro imu;
	
	public TestController(CowGamepad driver, CowGamepad operator){
		this.driver = driver;
		this.operator = operator;
		imu = new ADXRS450_Gyro();
	}
	
	@Override
	public void run() {
	}
}
