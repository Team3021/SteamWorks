package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class MoveForwardForTime extends DriveCommand {
	
	protected double speed = 0;
	protected double time = 0;
	
	public MoveForwardForTime() {
		super();
		
		this.speed = getAutonomousMoveSpeed();
		this.time = getAutonomousMoveTime();
		
		setTimeout(time);
	}

	@Override
	protected void initialize() {
		System.out.println("Start moving forwards for time : " + time);
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.moveForwardWithGyro(speed);
	}
	
	@Override
	protected void end() {
		System.out.println("Start moving forwards for time : " + time);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
