package org.usfirst.frc.team4213;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.usfirst.frc.team4213.contollers.DriveTrainController;
import org.usfirst.frc.team4213.contollers.GamePadController;
import org.usfirst.frc.team4213.metallib.MetalRobot;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends MetalRobot {

    private GamePadController gamePadController;
    private DriveTrainController driveTrainController;

    @Override
    public void robotInit() {
        loadProperties();

        driveTrainController = DriveTrainController.INSTANCE;
        gamePadController = GamePadController.INSTANCE;
        
        registerTasks();
        
    }
    
    public void registerTasks() {
    	
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