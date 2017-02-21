package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.device.StartIndexer;
import org.usfirst.frc.team3021.robot.commands.device.StopIndexer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class IndexerTest extends CommandGroup {

	public IndexerTest() {
		requires(Stanley.collector);
		
		addSequential(new StartIndexer());
		addSequential(new TimedCommand(5));
		
		addSequential(new StopIndexer());
		addSequential(new TimedCommand(5));
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Indexer Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Indexer Test");
	}
}
