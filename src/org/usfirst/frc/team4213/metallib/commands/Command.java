package org.usfirst.frc.team4213.metallib.commands;

public abstract class Command {
	
	private boolean isFirstRun = true;
	
	public final void run() {
		if (isFirstRun) {
			init();
			isFirstRun = false;
		}
		run2();
	}	
	
	protected abstract void run2();
	
	public boolean isFinished() {
		return true;
	}
	
	public abstract void stop();
	
	abstract void init();
}
