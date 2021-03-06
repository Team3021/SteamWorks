package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.device.StartClimber;
import org.usfirst.frc.team3021.robot.commands.device.StopClimber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class ClimberTest extends CommandGroup {

	public ClimberTest() {
		requires(Stanley.collector);
		
		addSequential(new StartClimber());
		addSequential(new TimedCommand(5));
		
		addSequential(new StopClimber());
		addSequential(new TimedCommand(5));
	}

	@Override
	protected void initialize() {
		System.out.println("Starting Climber Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending Climber Test");
	}
}
