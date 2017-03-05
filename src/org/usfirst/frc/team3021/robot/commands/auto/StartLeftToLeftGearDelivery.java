package org.usfirst.frc.team3021.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngle;

public class StartLeftToLeftGearDelivery extends CommandGroup {
	public StartLeftToLeftGearDelivery() {
		requires(Stanley.robotDrive);
		addSequential(new MoveForwardForDistance(0.5, 2));
		addSequential(new TurnToAngle(-20));
		addSequential(new MoveForwardForDistance(0.5, 5));
		addSequential(new TurnToAngle(90));
		addSequential(new MoveForwardForDistance(0.3, 4.5));
	}
}
