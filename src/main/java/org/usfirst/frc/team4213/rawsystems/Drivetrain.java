package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	public final static Drivetrain INSTANCE = new Drivetrain();

    private final SpeedController leftMotor, rightMotor;
    
    private final Encoder leftFollower, upFollower;
    
    private final DoubleSolenoid brake; 

    private Drivetrain () {
        leftMotor = new Talon(Integer.parseInt(System.getProperty("left.motor.channel")));
        rightMotor = new Talon(Integer.parseInt(System.getProperty("right.motor.channel")));
        brake = new DoubleSolenoid(Integer.parseInt(System.getProperty("brake.solenoid.forward.channel")),Integer.parseInt(System.getProperty("brake.solenoid.reverse.channel")));  
        leftFollower = new Encoder(Integer.parseInt(System.getProperty("left.follower.encoder.channelA")), Integer.parseInt(System.getProperty("left.follower.encoder.channelB")));
        upFollower = new Encoder(Integer.parseInt(System.getProperty("right.follower.encoder.channelA")), Integer.parseInt(System.getProperty("right.follower.encoder.channelB")));
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
