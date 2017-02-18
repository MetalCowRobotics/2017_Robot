package org.usfirst.frc.team4213.metallib;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

import org.usfirst.frc.team4213.metallib.util.TaskRunner;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public abstract class MetalRobot extends RobotBase {

	public enum RobotMode {
		AUTO, TELEOP, TEST, DISABLED, UNSET;
	}

	protected RobotMode runState;

	protected TaskRunner runner;

	private final static int DELAY_RATE = 10;

	private HashMap<RobotMode, HashSet<Runnable>> tasks = new HashMap<RobotMode, HashSet<Runnable>>();

	private DriverStation ds = DriverStation.getInstance();

	public MetalRobot() {
		EnumSet.allOf(RobotMode.class).forEach((mode) -> tasks.put(mode, new HashSet<Runnable>()));

		final int cores = Runtime.getRuntime().availableProcessors();
		runner = new TaskRunner(cores);
		
		runState = RobotMode.UNSET;
	}

	protected void addTask(RobotMode mode, Runnable task) {
		tasks.get(mode).add(task);
	}

	@Override
	public final void startCompetition() {
		FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();
		robotInit();
		runModeChecker();
		Thread.currentThread().suspend();
	}

	public abstract void robotInit();

	private void runModeChecker() {
		runner.scheduleTask(() -> {
			try{
				if (ds.isDisabled()) {
					if(runState != RobotMode.DISABLED){
						setMode(RobotMode.DISABLED);
					}
				} else if (ds.isAutonomous()) {
					if(runState != RobotMode.AUTO){
						setMode(RobotMode.AUTO);
					}
				} else if (ds.isTest()) {
					if(runState != RobotMode.TEST){
						setMode(RobotMode.TEST);
					}
				} else {
					if(runState != RobotMode.TELEOP){
						setMode(RobotMode.TELEOP);
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		}, DELAY_RATE, false);
	}

	private void setMode(RobotMode mode) {
		System.out.println("Entered" + mode.toString());
		updateDSState(runState, false);
		runState = mode;
		updateDSState(runState, true);
		startMode(runState);
	}

	private void startMode(RobotMode mode) {
		runner.clearTasks();
		runner.scheduleTaskSet(tasks.get(mode), DELAY_RATE, true);
	}

	private void updateDSState(RobotMode mode, boolean entering) {
		switch (mode) {
		case DISABLED:
			ds.InDisabled(entering);
			break;
		case AUTO:
			ds.InAutonomous(entering);
			break;
		case TEST:
			ds.InTest(entering);
			break;
		case TELEOP:
			ds.InOperatorControl(entering);
			break;
		default:
			break;
		}
	}
}