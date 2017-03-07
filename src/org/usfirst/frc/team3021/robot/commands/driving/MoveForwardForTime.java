package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

public class MoveForwardForTime extends DriveCommand {
	
	protected double speed = 0;
	protected double time = 0;
	protected String direction = FORWARD;
	
	public MoveForwardForTime(double speed, double seconds) {
		super();
		
		this.speed = speed;
		this.time = seconds;
		
		setTimeout(time);
	}

	@Override
	protected void initialize() {
		System.out.println("Start moving " + direction + " for time : " + time);
		
		super.initialize();
	}
	
	@Override
	protected void execute() {
		double moveValue = speed;
		
		if (direction.equals(BACKWARD)) {
			moveValue = -1.0 * moveValue;
		}
		
		Stanley.robotDrive.moveWithGyro(moveValue);
	}
	
	@Override
	protected void end() {
		System.out.println("End moving " + direction + " for time : " + time);
	}
	
	@Override
	protected boolean isFinished() {
		return (isTimedOut() || !isMoving());
	}
}
