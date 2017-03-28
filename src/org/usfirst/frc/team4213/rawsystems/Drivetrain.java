package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import com.ctre.CANTalon;
import com.ctre.PigeonImu;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public enum Drivetrain {
	INSTANCE;

//    private final CANTalon leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
	private final SpeedController leftMotor, rightMotor;
    
    private final DoubleSolenoid brake; 

    private final PigeonImu gyro;
    
    private final double gyroYawZero;
    
    private final Encoder rightWheel, leftWheel;
    
    private Drivetrain() {
        leftMotor = ComponentBuilder.buildMotor(MotorType.TALONSRX, "left.motor.channel", "left.motor.reverse");
        leftWheel = ComponentBuilder.buildEncoder("left.encoder.channel.a", "left.encoder.channel.b");
        leftWheel.setReverseDirection(true);

        rightMotor = ComponentBuilder.buildMotor(MotorType.TALONSRX, "right.motor.channel", "right.motor.reverse");
        rightWheel = ComponentBuilder.buildEncoder("right.encoder.channel.a", "right.encoder.channel.b");
        
        gyro = new PigeonImu(0);
        gyro.SetFusedHeading(0);
        
        double[] ypr = new double[3];
        gyro.GetYawPitchRoll(ypr);
        gyroYawZero = ypr[0];
        
        
        brake = ComponentBuilder.buildDoubleSolenoid("brake.solenoid.forward.channel", "brake.solenoid.reverse.channel");   
    }

    public void setLeftSpeed(double speed){
//    	leftFrontMotor.set(speed);
//    	leftBackMotor.set(speed);
    	leftMotor.set(speed);
    }
    
    public void setRightSpeed(double speed){
//    	rightFrontMotor.set(speed);
//    	rightBackMotor.set(speed);
    	rightMotor.set(speed);
    }
    
    public double getLeftPos() {
//    	return leftBackMotor.getEncPosition();
    	return leftWheel.getDistance();
    }
    
    public double getRightPos() {
//    	return rightBackMotor.getEncPosition();
    	return rightWheel.getDistance();
    }
    
    public double getLeftVel() {
//    	return leftBackMotor.getSpeed();
    	return leftWheel.getRate();
    }
    
    public double getRightVel() {
//    	return rightBackMotor.getSpeed();
    	return rightWheel.getRate();
    }
    
    public double getYaw() {
    	double []xyz = new double [3];
    	gyro.GetYawPitchRoll(xyz);
    	return xyz[0] - gyroYawZero;
    }
   
    public void setBrake (boolean open) {
    	if (open) {
    		brake.set(Value.kForward); 
    	} else {
    		brake.set(Value.kReverse);
    	}
    }

	public void resetEncPos() {
		rightWheel.reset();
		leftWheel.reset();
	}
  
	
}
