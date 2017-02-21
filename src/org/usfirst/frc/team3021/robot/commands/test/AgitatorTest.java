package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.device.StartAgitator;
import org.usfirst.frc.team3021.robot.commands.device.StopAgitator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class AgitatorTest extends CommandGroup {

	public AgitatorTest() {
		requires(Stanley.collector);
		
		addSequential(new StartAgitator());
		addSequential(new TimedCommand(5));
		
		addSequential(new StopAgitator());
		addSequential(new TimedCommand(5));
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Agitator Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Agitator Test");
	}
}
