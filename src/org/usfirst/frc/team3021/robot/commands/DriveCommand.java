package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

public class DriveCommand extends Command {
	
	private static final double AUTONOMOUS_MOVE_SPEED_DEFUALT = 0.3;
	private static final double AUTONOMOUS_MOVE_TIME_DEFUALT = 3;
	private static final double AUTONOMOUS_MOVE_DISTANCE_DEFUALT = 3;
	
	private static double autonomousMoveSpeed = AUTONOMOUS_MOVE_SPEED_DEFUALT;
	private static double autonomousMoveTimeDefault = AUTONOMOUS_MOVE_TIME_DEFUALT;
	private static double autonomousMoveDistanceDefault = AUTONOMOUS_MOVE_DISTANCE_DEFUALT;
	
	public DriveCommand() {
		super();
		
		requires(Stanley.robotDrive);
	}

	public static double getAutonomousMoveSpeed() {
		return autonomousMoveSpeed;
	}

	public static double getAutonomousMoveTime() {
		return autonomousMoveTimeDefault;
	}

	public static double getAutonomousMoveDistance() {
		return autonomousMoveDistanceDefault;
	}
}