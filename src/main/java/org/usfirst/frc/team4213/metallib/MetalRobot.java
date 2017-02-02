package org.usfirst.frc.team4213.metallib;

import java.util.HashSet;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.SampleRobot;

public class MetalRobot extends SampleRobot {

	private ScheduledThreadPoolExecutor executor;;

	private HashSet<ScheduledFuture<?>> runningTasks = new HashSet<ScheduledFuture<?>>();

	private HashSet<Runnable> autoTasks = new HashSet<Runnable>();
	private HashSet<Runnable> teleopTasks = new HashSet<Runnable>();
	private HashSet<Runnable> testTasks = new HashSet<Runnable>();
	private HashSet<Runnable> disabledTasks = new HashSet<Runnable>();

	protected final static int RUN_RATE = 20;

	public MetalRobot() {
		super();

		executor = new ScheduledThreadPoolExecutor(2);
		executor.setRemoveOnCancelPolicy(true);

	}

	final public void autonomous() {
		clearTasks();
		scheduleTaskSet(autoTasks);
	}

	final public void operatorControl() {
		clearTasks();
		scheduleTaskSet(teleopTasks);
	}

	final public void test() {
		clearTasks();
		scheduleTaskSet(testTasks);
	}

	final protected void disabled() {
		clearTasks();
		scheduleTaskSet(disabledTasks);
	}

	protected void clearTasks() {
		runningTasks.forEach((task) -> {
			task.cancel(false); // false is for whether the task should be
								// interrupted or not
		});
		runningTasks.clear();
		executor.purge();
	}

	protected void scheduleTask(Runnable task, int rate) {
		ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, 0, rate,
				TimeUnit.MILLISECONDS);
		runningTasks.add(future);
	}

	protected void runTask(Runnable task) {
		executor.submit(task);
	}

	protected void scheduleTaskSet(HashSet<Runnable> tasks) {
		tasks.forEach((task) -> {
			scheduleTask(task, RUN_RATE);
		});
	}

	protected void addAutoTask(Runnable task) {
		autoTasks.add(task);
	}

	protected void addTeleopTask(Runnable task) {
		teleopTasks.add(task);
	}

	protected void addTestTask(Runnable task) {
		testTasks.add(task);
	}

	protected void addDisabledTask(Runnable task) {
		disabledTasks.add(task);
	}

}
