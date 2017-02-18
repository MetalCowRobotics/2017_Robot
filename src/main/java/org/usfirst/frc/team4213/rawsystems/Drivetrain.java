package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drivetrain {
	public final static Drivetrain INSTANCE = new Drivetrain();

    private final CANTalon leftMotor, rightMotor;
    
    private final DoubleSolenoid brake; 

    private Drivetrain () {
        leftMotor = (CANTalon)ComponentBuilder.buildMotor(MotorType.CANTALON, "left.motor.channel","left.motor.reverse");
        rightMotor = (CANTalon)ComponentBuilder.buildMotor(MotorType.CANTALON, "right.motor.channel","right.motor.reverse");
        brake = ComponentBuilder.buildDoubleSolenoid("brake.solenoid.forward.channel", "brake.solenoid.reverse.channel");    }

    public void setLeftSpeed(double speed){
    	leftMotor.set(speed);
    }
    
    public void setRightSpeed(double speed){
    	rightMotor.set(speed);
    }
    
    public void setBrake(boolean open){
    	if(open) {
    		brake.set(Value.kForward); 
    	} else {
    		brake.set(Value.kReverse);
    	}
    }
  
}
