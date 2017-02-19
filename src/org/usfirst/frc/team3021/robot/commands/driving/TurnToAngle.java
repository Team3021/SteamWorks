package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class TurnToAngle extends DriveCommand {
	
	private double desiredAngle;

	public TurnToAngle(double angle) {
		super();
		
		desiredAngle = angle;
	}

	@Override
	protected void initialize() {
		System.out.println("Start TurnToAngle");
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
		
		return ( Math.abs(Stanley.robotDrive.getGyroTurnValue()) < 0.2);
		
//		double angleDifference = Math.abs(Stanley.robotDrive.getGyroAngle()) -  Math.abs(desiredAngle);
//		
//		SmartDashboard.putNumber("TurnToAngle : angle difference", angleDifference);
//		
//		return (Math.abs(angleDifference) <= GyroController.kToleranceDegrees);
	}
	
	@Override
	public synchronized boolean isInterruptible() {
		return false;
	}
}
