package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;

public class Collect extends LauncherCommand {

	public Collect(double seconds) {
		setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Stanley.collector.startMotor();
	}

	@Override
	protected void end() {
		Stanley.collector.stopMotor();
	}

	@Override
	protected void interrupted() {
		Stanley.collector.stopMotor();
	}
}
