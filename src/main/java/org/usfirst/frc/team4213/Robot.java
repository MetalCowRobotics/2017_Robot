package org.usfirst.frc.team4213;

import org.usfirst.frc.team4213.controllers.DriverController;
import org.usfirst.frc.team4213.controllers.OperatorController;
import org.usfirst.frc.team4213.metallib.MetalRobot;
import org.usfirst.frc.team4213.metallib.controllers.AIRFLOController;
import org.usfirst.frc.team4213.metallib.controllers.Xbox360Controller;
import org.usfirst.frc.team4213.metallib.util.PropertyStore;
import org.usfirst.frc.team4213.rawsystems.Climber;
import org.usfirst.frc.team4213.rawsystems.Drivetrain;
import org.usfirst.frc.team4213.rawsystems.Shooter;
import org.usfirst.frc.team4213.systems.ClimberSystem;
import org.usfirst.frc.team4213.systems.DriveSystem;
import org.usfirst.frc.team4213.systems.FeederSystem;
import org.usfirst.frc.team4213.systems.GearIntakeSystem;
import org.usfirst.frc.team4213.systems.RollerIntakeSystem;
import org.usfirst.frc.team4213.systems.ShooterSystem;
import org.usfirst.frc.team4213.systems.Subsystem;



import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by aaron on 11/21/16.
 */
public class Robot extends MetalRobot {

	//Gamepads
	AIRFLOController driverGamepad;
	Xbox360Controller operatorGamepad;
	
	//Systems
	DriveSystem drivetrain;
	ClimberSystem climber;
	FeederSystem feeder;
	GearIntakeSystem gearIntake;
	RollerIntakeSystem rollerIntake;
	ShooterSystem shooter;
		
	// Controllers
	DriverController driver;
	OperatorController operator;
	
	{	
		initGamepads();
		drivetrain = initSubsystem(DriveSystem.class);
		climber = initSubsystem(ClimberSystem.class);
		feeder = initSubsystem(FeederSystem.class);
		gearIntake = initSubsystem(GearIntakeSystem.class);
		rollerIntake = initSubsystem(RollerIntakeSystem.class);
		shooter = initSubsystem(ShooterSystem.class);
		initControllers();
		
	}
	
	public void initGamepads(){
		driverGamepad = new AIRFLOController(0);
		operatorGamepad = new Xbox360Controller(1);
	}
	
	public void initControllers(){
		driver = new DriverController(driverGamepad,drivetrain);
		operator = new OperatorController(operatorGamepad, climber, shooter, gearIntake, feeder, rollerIntake);
	}
	
	
    @Override
    public void robotInit() {
        registerTasks();
        Compressor compressor = new Compressor();
        compressor.start();
    }
    
    public void registerTasks() {
    	addTask(RobotMode.TELEOP, driver);
    	addTask(RobotMode.TELEOP, operator);
    	
    }
    
	public <T extends Subsystem> T initSubsystem(Class<T> subsystem){
		String systemName = subsystem.getSimpleName();
		T system = null;
		if(PropertyStore.INSTANCE.getBool(systemName.toLowerCase() + ".enabled")){
			try {
				system = subsystem.newInstance();
				System.out.println(systemName + " is Enabled");
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println(systemName + " is Disabled");
		}
		return system;
	}
	
}