package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LauncherTest extends CommandGroup {

	public LauncherTest() {
		requires(Stanley.launcher);
		
		addSequential(new AgitatorTest());
		addSequential(new IndexerTest());
		addSequential(new LaunchWheelTest());
	}
	
	@Override
	protected void initialize() {
		System.out.println("Starting Launcher Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Launcher Test");
	}
}
