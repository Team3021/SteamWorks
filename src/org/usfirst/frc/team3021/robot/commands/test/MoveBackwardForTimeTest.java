package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForTime;

public class MoveBackwardForTimeTest extends MoveBackwardForTime {
	
	public MoveBackwardForTimeTest() {
		super(0,0);
	}

	@Override
	protected void initialize() {
		this.speed = DriveCommand.getAutonomousMoveSpeed();
		this.time = DriveCommand.getAutonomousMoveTime();
		
		super.initialize();
	}
}
