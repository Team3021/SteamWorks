package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.Preferences;

public abstract class DriveCommand extends Command {
	
	private static final String PREF_AUTONOMOUS_MOVE_SPEED = "Drive.autonomouns.move.speed";
	private static final String PREF_AUTONOMOUS_MOVE_TIME = "Drive.autonomouns.move.time";
	private static final String PREF_AUTONOMOUS_MOVE_DISTANCE = "Drive.autonomouns.move.distance";
	
	private static final double AUTONOMOUS_MOVE_SPEED_DEFUALT = 0.3;
	private static final double AUTONOMOUS_MOVE_TIME_DEFUALT = 3;
	private static final double AUTONOMOUS_MOVE_DISTANCE_DEFUALT = 3;
	
	protected static final String FORWARD = "forward";
	protected static final String BACKWARD = "backward";
	
	protected boolean hasMoved = false;
	
	public DriveCommand() {
		super();
		
		requires(Stanley.robotDrive);
	}

	@Override
	protected void initialize() {
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();

		hasMoved = false;
	}
	
	public static double getAutonomousMoveSpeed() {
		return Preferences.getInstance().getDouble(PREF_AUTONOMOUS_MOVE_SPEED, AUTONOMOUS_MOVE_SPEED_DEFUALT);
	}

	public static double getAutonomousMoveTime() {
		return Preferences.getInstance().getDouble(PREF_AUTONOMOUS_MOVE_TIME, AUTONOMOUS_MOVE_TIME_DEFUALT);
	}

	public static double getAutonomousMoveDistance() {
		return Preferences.getInstance().getDouble(PREF_AUTONOMOUS_MOVE_DISTANCE, AUTONOMOUS_MOVE_DISTANCE_DEFUALT);
	}

	protected boolean isMoving() {
		// Assume there is motion 
		// (not necessarily the case, but this is required for proper function of turnToAngle).
		boolean isMoving = true;
		
		// Checks to see if the robot has started moving.
		if (Stanley.robotDrive.isGyroMoving() && hasMoved == false) {
			isMoving = true;
			hasMoved = true;
		}
		// False will not be returned unless the robot has already started moving.
		else if (!Stanley.robotDrive.isGyroMoving() && hasMoved == true) {
			isMoving = false;
			hasMoved = false;
		}
		return isMoving;
	}

}