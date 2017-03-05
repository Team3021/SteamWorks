package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartCenterToCenterGearDelivery extends CommandGroup {
	public StartCenterToCenterGearDelivery() {
		requires(Stanley.robotDrive);
		
		addSequential(new MoveForwardForDistance(0.4, 7));
		addSequential(new MoveBackwardForDistance(0.3, 3.5));
		addSequential(new TurnLeftToAngle(80));
		addSequential(new MoveForwardForDistance(1.0, 7));
		addSequential(new TurnRightToAngle(80));
		addSequential(new MoveForwardForDistance(1.0, 12));
	}
}
