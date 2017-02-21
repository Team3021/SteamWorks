package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class ClimberCommand extends Command {
	
	public ClimberCommand() {
		super();
		
		requires(Stanley.climber);
	}
}