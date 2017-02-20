package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class DriveForTime extends DriveCommand {
	
	protected double speed = 0;
	
	public DriveForTime(double speed, double seconds) {
		super();
		
		this.speed = speed;
		
		setTimeout(seconds);
	}

	@Override
	protected void initialize() {
		System.out.println("Start DriveForTime");
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.driveForwardWithGyro(speed);
	}
	
	@Override
	protected void end() {
		System.out.println("End DriveForTime");
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
