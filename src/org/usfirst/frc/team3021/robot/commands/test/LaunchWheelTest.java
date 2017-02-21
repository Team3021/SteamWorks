package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.device.StartLaunchWheel;
import org.usfirst.frc.team3021.robot.commands.device.StopLaunchWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class LaunchWheelTest  extends CommandGroup {

	public LaunchWheelTest() {
		requires(Stanley.collector);
		
		addSequential(new StartLaunchWheel());
		addSequential(new TimedCommand(5));
		
		addSequential(new StopLaunchWheel());
		addSequential(new TimedCommand(5));
	}

	@Override
	protected void initialize() {
		System.out.println("Starting LaunchWheel Test");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending LaunchWheel Test");
	}
}
