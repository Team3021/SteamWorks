package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;

public class StartLaunchWheel extends LauncherCommand {

	@Override
	protected void execute() {
		Stanley.launcher.startLaunchWheel();
	}

	@Override
	protected void interrupted() {
		Stanley.launcher.stopLaunchWheel();
	}
}
