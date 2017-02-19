package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class LauncherCommand extends Command {
	
	public LauncherCommand() {
		super();
		
		requires(Stanley.launcher);
	}
}