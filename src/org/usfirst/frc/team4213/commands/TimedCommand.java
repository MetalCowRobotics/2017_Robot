package org.usfirst.frc.team4213.commands;

import edu.wpi.first.wpilibj.Timer;

public abstract class TimedCommand extends Command {

	Timer timer = new Timer();
	double period;

	public TimedCommand(double seconds){
		this.period = seconds;
	}
	
	@Override
	protected void initialize() {
		timer.start();
		start();
	}
	
	protected abstract void start();
	
	@Override
	public final boolean isFinished() {
		return timer.hasPeriodPassed(period);
	}

	@Override
	public void end() {
		timer.stop();
		timer.reset();
		stop();
	}
	
	protected abstract void stop();

}
