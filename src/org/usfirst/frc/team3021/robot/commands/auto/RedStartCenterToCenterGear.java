package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class RedStartCenterToCenterGear extends CommandGroup {
	public RedStartCenterToCenterGear() {
		super("[Red] [Center] to [Center Gear]");

		requires(Stanley.robotDrive);
		
		addSequential(new MoveForwardForDistance(0.3, 7));
		addSequential(new TimedCommand(4));
	}
}
