package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveTest extends CommandGroup {

	public DriveTest() {
		requires(Stanley.robotDrive);
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Drive Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Drive Test");
	}
}
