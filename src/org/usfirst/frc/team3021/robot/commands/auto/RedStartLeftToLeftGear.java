package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedStartLeftToLeftGear extends CommandGroup {
	public RedStartLeftToLeftGear() {
		super("[Red] [Left] to [Left Gear]");
		
		requires(Stanley.robotDrive);
		
		double speed = DriveCommand.getAutonomousMoveSpeed();
		
		addSequential(new MoveForwardForDistance(speed, 7.75));
		addSequential(new TurnRightToAngle(56));
		addSequential(new MoveForwardForDistance(speed, 1.75));
	}
}
