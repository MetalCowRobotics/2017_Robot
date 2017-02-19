package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public enum Drivetrain {
	INSTANCE;

    private final CANTalon leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    
    private final DoubleSolenoid brake; 

    private Drivetrain () {
        leftFrontMotor = (CANTalon)ComponentBuilder.buildMotor(MotorType.CANTALON, "left.motor.front.channel","left.motor.front.reverse");
        leftBackMotor = (CANTalon)ComponentBuilder.buildMotor(MotorType.CANTALON, "left.motor.back.channel","left.motor.back.reverse");

        rightFrontMotor = (CANTalon)ComponentBuilder.buildMotor(MotorType.CANTALON, "right.motor.front.channel","right.motor.front.reverse");
        rightBackMotor = (CANTalon)ComponentBuilder.buildMotor(MotorType.CANTALON, "right.motor.back.channel","right.motor.back.reverse");

        brake = ComponentBuilder.buildDoubleSolenoid("brake.solenoid.forward.channel", "brake.solenoid.reverse.channel");    }

    public void setLeftSpeed(double speed){
    	leftFrontMotor.set(speed);
    	leftBackMotor.set(speed);
    }
    
    public void setRightSpeed(double speed){
    	rightFrontMotor.set(speed);
    	rightBackMotor.set(speed);
    }
    
    public void setBrake(boolean open){
    	if(open) {
    		brake.set(Value.kForward); 
    	} else {
    		brake.set(Value.kReverse);
    	}
    }
  
}
