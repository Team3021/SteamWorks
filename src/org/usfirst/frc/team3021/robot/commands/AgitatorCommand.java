package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class AgitatorCommand extends Command {
	
	public AgitatorCommand() {
		super();
		
		requires(Stanley.launcher);
	}
}