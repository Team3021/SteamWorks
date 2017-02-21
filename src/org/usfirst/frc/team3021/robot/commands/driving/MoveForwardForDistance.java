package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class MoveForwardForDistance extends DriveCommand {
	
	protected double speed = 0;
	protected double distance = 0;
	protected String direction = FORWARD;
	
	public MoveForwardForDistance(double speed, double distance) {
		super();
		
		this.speed = speed;
		this.distance = distance;
		
		// set time out for safety
		setTimeout(5);
	}

	@Override
	protected void initialize() {
		System.out.println("Start moving " + direction + " for distance : " + distance);
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();
	}
	
	@Override
	protected void execute() {
		double moveValue = speed;
		
		if (direction.equals(BACKWARD)) {
			moveValue = -1.0 * moveValue;
		}
		
		Stanley.robotDrive.moveWithGyro(moveValue);
	}
	
	@Override
	protected void end() {
		System.out.println("End moving " + direction + " for distance : " + distance);
	}
	
	@Override
	protected boolean isFinished() {
		
		double remaingDifference = Math.abs(distance) - Math.abs(Stanley.robotDrive.getDistance());
		
		remaingDifference = Math.abs(remaingDifference);
		
		return (remaingDifference < 0.1);
	}
}
