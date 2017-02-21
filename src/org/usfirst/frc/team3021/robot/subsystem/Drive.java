package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
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
	public void driveForwardWithGyro(double moveValue) {
		drive(moveValue, getGyroOffset());
	}

	public void stop() {
		driveController.stop();
	}

	// ****************************************************************************
	// **********************             TURNING            **********************
	// ****************************************************************************

	public void turnToAngle(double desiredAngle) {
		setGyroAngle(desiredAngle);
		
		double turnValue = getGyroTurnValue();
		
		drive(0, turnValue);
	}

	public void turnToCentralAngle(double desiredAngle) {
		setGyroCentralAngle(desiredAngle);
		
		double turnValue = getGyroTurnValue();
		
		drive(0, turnValue);
	}

	// ****************************************************************************
	// **********************              GYRO              **********************
	// ****************************************************************************

	public void resetGyro() {
		gyroController.resetGyro();
	}

	public double getGyroAngle() {
		return gyroController.getAngle();
	}

	public void setGyroAngle(double angle) {
		gyroController.setAngle(angle);
	}

	public double getGyroCentralAngle() {
		return gyroController.getCentralAngle();
	}

	public void setGyroCentralAngle(double angle) {
		gyroController.setCentralAngle(angle);
	}

	public double getGyroTurnValue() {
		return gyroController.getTurnValue();
	}

	private double getGyroOffset() {
		double gyroOffset = (getGyroCentralAngle() * .01111111111);
		
		// IF THE ABOSOLUTE VAL OF THE GYRO OFFSET IS LARGER THAN 1
		if (Math.abs(gyroOffset) > 1) {
			// SET THE GYRO OFFSET TO EITHER 1 OR -1
			gyroOffset =  (-1 * (Math.abs(gyroOffset) / gyroOffset));
		} else {
			gyroOffset =  -gyroOffset;
		}
		
		return gyroOffset;
	}

	// ****************************************************************************
	// **********************             TELEOP            **********************
	// ****************************************************************************

	@Override
	public void teleopPeriodic() {
		
		// update the SmartDashboard
		gyroController.printAngle();
		gyroController.printCentralAngle();
		
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
        else if (mainController.isRotatingCustom()) {
        	autonomousCommand = new TurnToAngleRight45();
        }
        else if (mainController.isRotatingCustomNegative()) {
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
