package org.usfirst.frc.team4213.contollers;

import org.usfirst.frc.team4213.subsystems.DriveTrain;

/**
 * Drive Train Controller
 */
public class DriveTrainController {

    public final static DriveTrainController INSTANCE = new DriveTrainController();

    private final DriveTrain driveTrain;

    private DriveTrainController() {
        driveTrain = DriveTrain.INSTANCE;
    }
}