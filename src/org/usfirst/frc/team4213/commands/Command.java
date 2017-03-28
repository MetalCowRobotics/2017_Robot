package org.usfirst.frc.team4213.commands;

public abstract class Command {
	protected abstract void execute();
	protected abstract void initialize();
	public abstract boolean isFinished();
	public abstract void end();
	
	private boolean firstRun = true;
	
	public final void run(){
		if(firstRun){
			initialize();
			firstRun = false;
		}
		execute();
	}
}
