package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class RedStartCenterToCenterGearToCrossLine extends CommandGroup {
	public RedStartCenterToCenterGearToCrossLine() {
		super("[Red] [Center] to [Center Gear]");

		requires(Stanley.robotDrive);
		
		double speed = DriveCommand.getAutonomousMoveSpeed();
		
		addSequential(new MoveForwardForDistance(speed, 7));
		addSequential(new TimedCommand(5));
		addSequential(new MoveBackwardForDistance(speed, 3.5));
		addSequential(new TurnLeftToAngle(75));
		addSequential(new MoveForwardForDistance(1.0, 7));
		addSequential(new TurnRightToAngle(75));
		addSequential(new MoveForwardForDistance(1.0, 7));
	}
}
