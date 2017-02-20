package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;

public class Index extends LauncherCommand {

	public Index(double seconds) {
		setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Stanley.launcher.startIndexer();
	}

	@Override
	protected void end() {
		Stanley.launcher.stopIndexer();
	}

	@Override
	protected void interrupted() {
		Stanley.launcher.stopIndexer();
	}
}
