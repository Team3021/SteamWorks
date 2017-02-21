package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.device.StartCollector;
import org.usfirst.frc.team3021.robot.commands.device.StopCollector;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class CollectorTest extends CommandGroup {

	public CollectorTest() {
		requires(Stanley.collector);
		
		addSequential(new StartCollector());
		addSequential(new TimedCommand(5));
		
		addSequential(new StopCollector());
		addSequential(new TimedCommand(5));
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Collector Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Collector Test");
	}
}
