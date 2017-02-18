package org.usfirst.frc.team3021.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SubsystemTest extends CommandGroup {

	public SubsystemTest() {
		addSequential(new LauncherTest());
		addSequential(new CollectorTest());
	}
}
