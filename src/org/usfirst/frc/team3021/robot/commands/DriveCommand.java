package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class DriveCommand extends Command {
	
	public DriveCommand() {
		super();
		
		requires(Stanley.robotDrive);
	}
}