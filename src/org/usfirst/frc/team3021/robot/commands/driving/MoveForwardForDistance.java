package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class MoveForwardForDistance extends DriveCommand {
	
	protected double speed = 0;
	protected double desiredDistance = 0;
	
	public MoveForwardForDistance() {
		super();
		
		this.speed = getAutonomousMoveSpeed();
		this.desiredDistance = getAutonomousMoveDistance();
		
		// set time out for safety
		setTimeout(5);
	}

	@Override
	protected void initialize() {
		System.out.println("Start moving forwards for distance : " + desiredDistance);
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.moveForwardWithGyro(speed);
	}
	
	@Override
	protected void end() {
		System.out.println("Ending moving forward for distance : " + desiredDistance);
	}
	
	@Override
	protected boolean isFinished() {
		
		double remaingDifference = Math.abs(desiredDistance) - Math.abs(Stanley.robotDrive.getDistance());
		
		remaingDifference = Math.abs(remaingDifference);
		
		return (remaingDifference < 0.1);
	}
}
