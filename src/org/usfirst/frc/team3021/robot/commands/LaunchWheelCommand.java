package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class LaunchWheelCommand extends Command {
	
	public LaunchWheelCommand() {
		super();
		
		requires(Stanley.launcher);
	}
}