package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class CollectorCommand extends Command {
	
	public CollectorCommand() {
		super();
		
		requires(Stanley.collector);
	}
}