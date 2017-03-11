package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.StopMoving;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueStartRightToRightGear extends CommandGroup {
	
	public BlueStartRightToRightGear() {
		super("[Blue] [Right] to [Right Gear]");
		
		requires(Stanley.robotDrive);
		
		addSequential(new MoveForwardForDistance(0.3, 7.75));
		addSequential(new TurnLeftToAngle(56));
		addSequential(new MoveForwardForDistance(0.3, 1.75));
	}
}
