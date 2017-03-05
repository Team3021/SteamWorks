package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForTime;

public class MoveForwardForTimeTest extends MoveForwardForTime {
	
	public MoveForwardForTimeTest() {
		super(0,0);
	}

	@Override
	protected void initialize() {
		this.speed = DriveCommand.getAutonomousMoveSpeed();
		this.time = DriveCommand.getAutonomousMoveTime();
		
		super.initialize();
	}
}
