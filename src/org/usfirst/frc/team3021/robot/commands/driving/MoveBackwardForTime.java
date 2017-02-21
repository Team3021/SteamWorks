package org.usfirst.frc.team3021.robot.commands.driving;

public class MoveBackwardForTime extends MoveForwardForTime {
	
	public MoveBackwardForTime(double speed, double seconds) {
		super(speed, seconds);
		
		this.direction = BACKWARD;
	}
	
}
