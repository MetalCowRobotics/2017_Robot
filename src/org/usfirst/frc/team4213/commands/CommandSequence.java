package org.usfirst.frc.team4213.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandSequence {
	
	private List<Command> commands = new ArrayList<Command>();
	
	private int index = 0;
	
	public void addCommand (Command command) {
		commands.add(command);
	}
	
	public void reset() {
		index = 0;
	}
	
	public void run() {
		if (index >= commands.size()) {
			return;
		}
		
		Command currentCommand = commands.get(index);
		currentCommand.run();
		
		if (currentCommand.isFinished()) {
			currentCommand.end();
			index++;
		}
	}
}