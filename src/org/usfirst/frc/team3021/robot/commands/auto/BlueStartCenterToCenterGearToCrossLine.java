package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class BlueStartCenterToCenterGearToCrossLine extends CommandGroup {
	public BlueStartCenterToCenterGearToCrossLine() {
		super("[Blue] [Center] to [Center Gear] to [Cross Line]");

		requires(Stanley.robotDrive);
		
		addSequential(new MoveForwardForDistance(0.3, 7));
		addSequential(new TimedCommand(5));
		addSequential(new MoveBackwardForDistance(0.3, 3.5));
		addSequential(new TurnRightToAngle(75));
		addSequential(new MoveForwardForDistance(1.0, 7));
		addSequential(new TurnLeftToAngle(75));
		addSequential(new MoveForwardForDistance(1.0, 12));
	}
}
