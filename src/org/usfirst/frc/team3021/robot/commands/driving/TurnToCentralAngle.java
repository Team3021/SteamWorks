package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.controller.GyroController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToCentralAngle extends DriveCommand {
	
	protected double desiredAngle;

	protected TurnToCentralAngle() {
		
	}
	
	public TurnToCentralAngle(double angle) {
		super();
		
		desiredAngle = angle;
	}

	@Override
	protected void initialize() {
		System.out.println("Start TurnToAngle");
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.turnToCentralAngle(desiredAngle);
	}
	
	@Override
	protected void end() {
		System.out.println("End TurnToAngle");
	}
	
	@Override
	protected boolean isFinished() {
		
		double angleDifference = Math.abs(Stanley.robotDrive.getGyroCentralAngle()) -  Math.abs(desiredAngle);
		
		SmartDashboard.putNumber("TurnToAngle : angle difference", angleDifference);
		
		return (Math.abs(angleDifference) <= GyroController.kToleranceDegrees);
	}
	
	@Override
	public synchronized boolean isInterruptible() {
		return false;
	}
}
