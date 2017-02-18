package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class Agitate extends Command {

	public Agitate() {
		requires(Stanley.launcher);
	}

	@Override
	protected void initialize() {
		Stanley.launcher.startAgitator();
	}

	@Override
	protected void execute() {
		Stanley.launcher.startAgitator();
	}

	@Override
	protected boolean isFinished() {
		return true;
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
