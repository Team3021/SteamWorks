package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class MoveForwardForDistance extends DriveCommand {
	
	protected double desiredSpeed = 0;
	protected double desiredDistance = 0;
	protected String direction = FORWARD;
	
	public MoveForwardForDistance(double speed, double distance) {
		super();
		
		this.desiredSpeed = speed;
		this.desiredDistance = distance;
	}

	@Override
	protected void initialize() {
		System.out.println("Start moving " + direction + " for distance : " + desiredDistance);
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();
	}
	
	@Override
	protected void execute() {
		double moveValue = desiredSpeed;
		
		if (direction.equals(BACKWARD)) {
			moveValue = -1.0 * moveValue;
		}
		
		Stanley.robotDrive.moveWithGyro(moveValue);
	}
	
	@Override
	protected void end() {
		System.out.println("End moving " + direction + " for distance : " + desiredDistance);
	}
	
	@Override
	protected boolean isFinished() {
		
		double remaingDifference = Math.abs(desiredDistance) - Math.abs(Stanley.robotDrive.getDistance());
		
		remaingDifference = Math.abs(remaingDifference);
		
		return (remaingDifference < 0.1 || !isMoving());
	}
}
