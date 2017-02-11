package org.usfirst.frc.team4213;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.usfirst.frc.team4213.metallib.MetalRobot;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends MetalRobot {

    @Override
    public void robotInit() {   
        registerTasks();
    }
    
    public void registerTasks() {
    	addTask(RobotMode.AUTO,()->{
    		DriverStation.reportError("I'm in auto 1", false);
    	});
    	addTask(RobotMode.TELEOP,()->{
    		DriverStation.reportError("I'm in teleop 1", false);
    	});
    	addTask(RobotMode.DISABLED,()->{
    		DriverStation.reportError("I'm in disabled 1", false);
    	});
    	addTask(RobotMode.TEST,()->{
    		DriverStation.reportError("I'm in test 1", false);
    	});
    	addTask(RobotMode.AUTO,()->{
    		DriverStation.reportError("I'm in auto 2", false);
    	});
    	addTask(RobotMode.TELEOP,()->{
    		DriverStation.reportError("I'm in teleop 2", false);
    	});
    	addTask(RobotMode.DISABLED,()->{
    		DriverStation.reportError("I'm in disabled 2", false);
    	});
    	addTask(RobotMode.TEST,()->{
    		DriverStation.reportError("I'm in test 2", false);
    	});
    }
}