package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedStartLeftToLeftGear extends CommandGroup {
	public RedStartLeftToLeftGear() {
		super("[Red] [Left] to [Left Gear]");
		
		requires(Stanley.robotDrive);
		
		addSequential(new MoveForwardForDistance(0.3, 7.75));
		addSequential(new TurnRightToAngle(56));
		addSequential(new MoveForwardForDistance(0.3, 1.75));
	}
}
