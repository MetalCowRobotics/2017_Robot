package org.usfirst.frc.team4213;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.usfirst.frc.team4213.controllers.DriverController;
import org.usfirst.frc.team4213.metallib.MetalRobot;
import org.usfirst.frc.team4213.metallib.controllers.Xbox360Controller;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends MetalRobot {

	Xbox360Controller driverController = new Xbox360Controller(1);
	
    @Override
    public void robotInit() {
        loadProperties();
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

    private void loadProperties() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        if(null != inputStream) {
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
                System.setProperties(properties);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
}