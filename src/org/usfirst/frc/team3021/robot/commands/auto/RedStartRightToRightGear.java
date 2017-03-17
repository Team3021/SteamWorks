package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedStartRightToRightGear extends CommandGroup {
	
	public RedStartRightToRightGear() {
		super("[Red] [Right] to [Right Gear]");
		
		requires(Stanley.robotDrive);
		
		double speed = DriveCommand.getAutonomousMoveSpeed();
		
		addSequential(new MoveForwardForDistance(speed, 7.00));
		addSequential(new TurnLeftToAngle(60));
		addSequential(new MoveForwardForDistance(speed, 1.25));
		
		// 5.75

	}
}
