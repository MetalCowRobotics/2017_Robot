package org.usfirst.frc.team4213;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.usfirst.frc.team4213.controllers.DriverController;
import org.usfirst.frc.team4213.metallib.MetalRobot;
import org.usfirst.frc.team4213.metallib.controllers.Xbox360Controller;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends MetalRobot {

	Xbox360Controller driverController = new Xbox360Controller(1);
	
    @Override
    public void robotInit() {
        loadProperties();
        
        DriverController.setController(driverController);
        
        registerTasks();
    }
    
    public void registerTasks() {
    	addTeleopTask(DriverController.INSTANCE);
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