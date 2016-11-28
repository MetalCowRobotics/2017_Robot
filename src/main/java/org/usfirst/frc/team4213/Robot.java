package org.usfirst.frc.team4213;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.usfirst.frc.team4213.contollers.DriveTrainController;
import org.usfirst.frc.team4213.contollers.GamePadController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends IterativeRobot {

    private GamePadController gamePadController;

    private DriveTrainController driveTrainController;

    @Override
    public void robotInit() {
        loadProperties();
        driveTrainController = DriveTrainController.INSTANCE;
        gamePadController = GamePadController.INSTANCE;
    }

    @Override
    public void teleopPeriodic() {
       //TODO: Implement
    }

    @Override
    public void autonomousInit() {
        //TODO: Implement
    }

    @Override
    public void autonomousPeriodic() {
        //TODO: Implement
    }

    @Override
    public void testPeriodic() {
        //TODO: Implement
    }

    @Override
    public void disabledInit() {
        //TODO: Implement
    }

    @Override
    public void disabledPeriodic() {
        //TODO: Implement
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