package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class TurnToAngle extends DriveCommand {
	
	protected double desiredAngle;

	public TurnToAngle() {
		super();
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
		return Stanley.robotDrive.isGyroOnTarget();
	}
	
	@Override
	public synchronized boolean isInterruptible() {
		return false;
	}
}
