package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class TurnToTarget extends DriveCommand {
	
	protected double desiredPosition;

	public TurnToTarget() {
		super();
		
		this.desiredPosition = Stanley.robotDrive.getDesiredTargetPosition();
	}
	
	@Override
	protected void initialize() {
		System.out.println("Start TurnToTarget");
		
		super.initialize();
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.turnToTarget(desiredPosition);
	}
	
	@Override
	protected void end() {
		System.out.println("End TurnToTarget");
	}
	
	@Override
	protected boolean isFinished() {
		return !isMoving();
	}
}
