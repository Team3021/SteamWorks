package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueStartCenterToCenterGear extends CommandGroup {
	public BlueStartCenterToCenterGear() {
		super("[Blue] [Center] to [Center Gear]");

		requires(Stanley.robotDrive);
		
		double speed = DriveCommand.getAutonomousMoveSpeed();
		
		addSequential(new MoveForwardForDistance(speed, 7));
	}
}
