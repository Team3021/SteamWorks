package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.VisionCommand;

public class VisionTest extends VisionCommand {

	public VisionTest() {
		requires(Stanley.vision);
	}

	@Override
	protected void execute() {
		
	}
}
