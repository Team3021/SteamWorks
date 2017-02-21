package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.DriveWithJoystick;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngle180;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleLeft45;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleLeft90;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleRight45;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleRight90;
import org.usfirst.frc.team3021.robot.controller.DriveController;
import org.usfirst.frc.team3021.robot.controller.GyroController;

import edu.wpi.first.wpilibj.command.Scheduler;

public class Drive extends Subsystem {

	private DriveController driveController;
	
	private GyroController gyroController;

	private DriveCommand defaultCommand;

	private DriveCommand autonomousCommand;
	
	public Drive() {
		driveController = new DriveController();
		
		gyroController = new GyroController();
	}
	
	@Override
	protected void initDefaultCommand() {
		defaultCommand = new DriveWithJoystick();
		setDefaultCommand(defaultCommand);
	}

	// ****************************************************************************
	// **********************              DRIVE             **********************
	// ****************************************************************************

	public void resetEncoders() {
		driveController.resetEncoders();
	}

	public double getDistance() {
		return driveController.getEncoderDistance();
	}

	public void drive(double moveValue, double turnValue) {
		driveController.drive(moveValue, turnValue);
	}
	
	// Drive forward using the gyro to maintain course
	// This assumes that forward is set to zero degrees
	// and thus the gyro offset is is a deviaiton from going straight forward
	public void moveForwardWithGyro(double moveValue) {
		drive(moveValue, gyroController.getGyroOffset());
	}

	public void stop() {
		driveController.stop();
	}

	// ****************************************************************************
	// **********************             TURNING            **********************
	// ****************************************************************************

	public void turnToAngle(double desiredAngle) {
		setGyroDesiredAngle(desiredAngle);
		
		double turnValue = getGyroTurnValue();
		
		drive(0, turnValue);
	}

	// ****************************************************************************
	// **********************              GYRO              **********************
	// ****************************************************************************

	public void resetGyro() {
		gyroController.resetGyro();
	}

	public void setGyroDesiredAngle(double angle) {
		gyroController.setDesiredAngle(angle);
	}

	public double getGyroTurnValue() {
		return gyroController.getTurnValue();
	}

	public boolean isGyroOnTarget() {
		return gyroController.isOnTarget();
	}

	public boolean isGyroRotating() {
		return gyroController.isRotating();
	}

	// ****************************************************************************
	// **********************             TELEOP            **********************
	// ****************************************************************************

	@Override
	public void teleopPeriodic() {
		
        if (mainController.isResettingNavx()) {
        	resetGyro();
        }
        
        // Lets the current autonomous command continue to run.
        if (autonomousCommand != null && autonomousCommand.isRunning()) {
        	return;
        }
        // Clears the current autonomous command if finished.
        else if (autonomousCommand != null && !autonomousCommand.isRunning()) {
        	autonomousCommand = null;
        }
        
        if (mainController.isRotatingToNinety()) {
        	autonomousCommand = new TurnToAngleRight90();
        }
        else if (mainController.isRotatingToNegativeNinety()) {
        	autonomousCommand = new TurnToAngleLeft90();
        }
        else if (mainController.isRotatingToOneHundredEighty()) {
        	autonomousCommand = new TurnToAngle180();
        }
        else if (mainController.isRotatingRight45()) {
        	autonomousCommand = new TurnToAngleRight45();
        }
        else if (mainController.isRotatingLeft45()) {
        	autonomousCommand = new TurnToAngleLeft45();
        }
        
        // Updates the scheduler to the selected autonomous command. 
        // If none is chosen, the scheduler runs the default command, driving with the joystick.
        if (autonomousCommand != null) {
        	Scheduler.getInstance().removeAll();
        	Scheduler.getInstance().add(autonomousCommand);
        }
	}
}
