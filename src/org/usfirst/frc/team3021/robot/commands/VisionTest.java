package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class VisionTest extends org.usfirst.frc.team3021.robot.commands.Command {

	public VisionTest() {
		requires(Stanley.vision);
	}

	@Override
	protected void execute() {
		
	}
}
