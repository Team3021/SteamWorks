package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionTest extends CommandGroup {

	public VisionTest() {
		requires(Stanley.vision);
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Vision Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Vision Test");
	}
}
