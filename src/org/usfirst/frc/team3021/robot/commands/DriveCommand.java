package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.Preferences;

public class DriveCommand extends Command {
	
	private static final String AUTONOMOUS_MOVE_SPEED = "Drive.autonomouns.move.speed";
	private static final String AUTONOMOUS_MOVE_TIME = "Drive.autonomouns.move.time";
	private static final String AUTONOMOUS_MOVE_DISTANCE = "Drive.autonomouns.move.distance";
	
	private static final double AUTONOMOUS_MOVE_SPEED_DEFUALT = 0.3;
	private static final double AUTONOMOUS_MOVE_TIME_DEFUALT = 3;
	private static final double AUTONOMOUS_MOVE_DISTANCE_DEFUALT = 3;
	
	protected static final String FORWARD = "forward";
	protected static final String BACKWARD = "backward";
	
	public DriveCommand() {
		super();
		
		requires(Stanley.robotDrive);
	}

	public static double getAutonomousMoveSpeed() {
		return Preferences.getInstance().getDouble(AUTONOMOUS_MOVE_SPEED, AUTONOMOUS_MOVE_SPEED_DEFUALT);
	}

	public static double getAutonomousMoveTime() {
		return Preferences.getInstance().getDouble(AUTONOMOUS_MOVE_TIME, AUTONOMOUS_MOVE_TIME_DEFUALT);
	}

	public static double getAutonomousMoveDistance() {
		return Preferences.getInstance().getDouble(AUTONOMOUS_MOVE_DISTANCE, AUTONOMOUS_MOVE_DISTANCE_DEFUALT);
	}

}