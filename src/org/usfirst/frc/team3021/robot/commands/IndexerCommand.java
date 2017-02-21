package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class IndexerCommand extends Command {
	
	public IndexerCommand() {
		super();
		
		requires(Stanley.launcher);
	}
}