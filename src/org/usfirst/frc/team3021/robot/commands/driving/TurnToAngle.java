package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.controller.GyroController;

public class TurnToAngle extends DriveCommand {
	
	private double desiredAngle;

	public TurnToAngle(double angle) {
		super();
		
		desiredAngle = angle;
	}

	@Override
	protected void execute() {
		Stanley.robotDrive.turnToAngle(desiredAngle);
	}

	@Override
	protected boolean isFinished() {
		double angleDifference = Math.abs(Stanley.robotDrive.getGyroAngle() - desiredAngle);
		
		return (angleDifference <= GyroController.kToleranceDegrees);
	}
	
	@Override
	public synchronized boolean isInterruptible() {
		return false;
	}
}
