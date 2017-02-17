package org.usfirst.frc.team4213.rawsystems;

import org.usfirst.frc.team4213.metallib.ComponentBuilder;
import org.usfirst.frc.team4213.metallib.ComponentBuilder.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	public final static Drivetrain INSTANCE = new Drivetrain();

    private final SpeedController leftMotor, rightMotor;
    
//    private final Encoder leftFollower, upFollower;
    
    private final DoubleSolenoid brake; 

    private Drivetrain () {
        leftMotor = ComponentBuilder.buildMotor(MotorType.TALON, "left.motor.channel","left.motor.reverse");
        rightMotor = ComponentBuilder.buildMotor(MotorType.TALON, "right.motor.channel","right.motor.reverse");
        brake = ComponentBuilder.buildDoubleSolenoid("brake.solenoid.forward.channel", "brake.solenoid.reverse.channel");
//        leftFollower = new Encoder(Integer.parseInt(System.getProperty("left.follower.encoder.channelA")), Integer.parseInt(System.getProperty("left.follower.encoder.channelB")));
//        upFollower = new Encoder(Integer.parseInt(System.getProperty("right.follower.encoder.channelA")), Integer.parseInt(System.getProperty("right.follower.encoder.channelB")));
    }

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
