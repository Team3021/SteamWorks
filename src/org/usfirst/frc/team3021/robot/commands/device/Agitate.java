package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;

public class Agitate extends LauncherCommand {

	public Agitate(double seconds) {
		setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Stanley.launcher.startAgitator();
	}

	@Override
	protected void end() {
		Stanley.launcher.stopAgitator();
	}

	@Override
	protected void interrupted() {
		Stanley.launcher.stopAgitator();
	}
}
