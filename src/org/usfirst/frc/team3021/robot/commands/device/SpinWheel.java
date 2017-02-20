package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;

public class SpinWheel extends LauncherCommand {

	public SpinWheel(double seconds) {
		setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Stanley.launcher.startWheel();
	}

	@Override
	protected void end() {
		Stanley.launcher.stopWheel();
	}

	@Override
	protected void interrupted() {
		Stanley.launcher.stopWheel();
	}
}
