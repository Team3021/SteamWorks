package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.controller.GyroController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToCentralAngle extends DriveCommand {
	
	protected double desiredAngle;

	protected TurnToCentralAngle() {
		super();
	}
	
	public TurnToCentralAngle(double angle) {
		super();
		
		desiredAngle = angle;
		
		SmartDashboard.putNumber("TurnToAngle : desired angle", desiredAngle);
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
		
		double currentAbsAngle = Math.abs(Stanley.robotDrive.getGyroCentralAngle());
		double currentAbsDesiredAngle = Math.abs(desiredAngle);
		
		double angleDifference = currentAbsAngle - currentAbsDesiredAngle;
		angleDifference = Math.abs(angleDifference);
		
		SmartDashboard.putNumber("TurnToAngle : angle difference", angleDifference);
		SmartDashboard.putNumber("TurnToAngle : desired angle", currentAbsDesiredAngle);
		SmartDashboard.putNumber("TurnToAngle : current angle", currentAbsAngle);
		
		return (angleDifference <= GyroController.kToleranceDegrees);
	}
}
