package org.usfirst.frc.team3021.robot.commands.auto;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Box extends CommandGroup {
	public Box() {
		super("Box");

		requires(Stanley.robotDrive);
		
		double speed = DriveCommand.getAutonomousMoveSpeed();
		
		addSequential(new MoveForwardForDistance(speed, 2));
		addSequential(new TurnRightToAngle(90));
		
		addSequential(new MoveForwardForDistance(speed, 2));
		addSequential(new TurnRightToAngle(90));
		
		addSequential(new MoveForwardForDistance(speed, 2));
		addSequential(new TurnRightToAngle(90));
		
		addSequential(new MoveForwardForDistance(speed, 2));
		addSequential(new TurnRightToAngle(90));
	}
}
