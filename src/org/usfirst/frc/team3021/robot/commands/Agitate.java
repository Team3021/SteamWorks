package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class Agitate extends LauncherCommand {

	@Override
	protected void execute() {
		Stanley.launcher.startAgitator();
	}

	@Override
	protected boolean isFinished() {
		return false;
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
