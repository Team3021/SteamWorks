package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.controller.GyroController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class TurnToAngle extends DriveCommand {
	
	protected double desiredAngle;

	public TurnToAngle() {
		super();
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
		boolean isRotating = Stanley.robotDrive.isRotating();
		
		return !isRotating;
	}
}
