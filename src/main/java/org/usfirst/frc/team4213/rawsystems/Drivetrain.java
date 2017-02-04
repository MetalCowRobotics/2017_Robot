package org.usfirst.frc.team4213.rawsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {
	public final static Drivetrain INSTANCE = new Drivetrain();

    private final SpeedController leftMotor;

    private final SpeedController rightMotor;
    
    private final DoubleSolenoid brake; 

    private Drivetrain () {
        leftMotor = new Talon(Integer.parseInt(System.getProperty("left.motor.channel")));
        rightMotor = new Talon(Integer.parseInt(System.getProperty("right.motor.channel")));
        brake = new DoubleSolenoid(Integer.parseInt(System.getProperty("brake.solenoid.forward.channel")),Integer.parseInt(System.getProperty("brake.solenoid.reverse.channel")));
       
    }

    public void setLeftSpeed(double speed){
    	leftMotor.set(speed);
    }
    
    public void setRightSpeed(double speed){
    	rightMotor.set(speed);
    }
    
    public void setBrakeOpen(boolean open){
    	if(open) {
    		brake.set(Value.kReverse); 
    	} else {
    		brake.set(Value.kForward);
    	}
    }
  
}
