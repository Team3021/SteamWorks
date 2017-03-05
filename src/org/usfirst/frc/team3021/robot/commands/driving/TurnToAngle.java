package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public abstract class TurnToAngle extends DriveCommand {
	
	protected double desiredAngle;

	public TurnToAngle(double desiredAngle) {
		super();
		
		this.desiredAngle = desiredAngle;
	}
	
	@Override
	protected void initialize() {
		System.out.println("Start TurnToAngle");
		Stanley.robotDrive.resetGyro();
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.turnToAngle(desiredAngle);
	}
	
	@Override
	protected void end() {
		System.out.println("End TurnToAngle");
	}
	
	@Override
	protected boolean isFinished() {
		return !isMoving();
	}
}
