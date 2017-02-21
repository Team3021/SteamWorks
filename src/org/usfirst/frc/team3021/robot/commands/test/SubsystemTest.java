package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SubsystemTest extends CommandGroup {

	public SubsystemTest() {
		requires(Stanley.launcher);
		requires(Stanley.collector);
		requires(Stanley.climber);
		
		addSequential(new LauncherTest());
		addSequential(new CollectorTest());
		addSequential(new ClimberTest());
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Subsystem Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Subsystem Test");
	}
}
