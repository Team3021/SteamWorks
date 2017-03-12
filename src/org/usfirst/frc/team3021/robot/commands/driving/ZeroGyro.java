package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class ZeroGyro extends DriveCommand {
	
	@Override
	protected void execute() {
		Stanley.robotDrive.zeroGyro();
	}
}
