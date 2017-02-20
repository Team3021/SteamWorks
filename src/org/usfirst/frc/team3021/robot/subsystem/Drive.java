package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.DriveWithJoystick;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngle;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToCentralAngle;
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
	// **********************           ROBOT DRIVE          **********************
	// ****************************************************************************
	
	public void drive(double moveValue, double turnValue) {
		driveController.drive(moveValue, turnValue);
	}
	
	// Drive forward using the gyro to maintain course
	// This assumes that forward is set to zero degrees
	// and thus the gyro offset is is a deviaiton from going straight forward
	public void driveForwardWithGyro(double moveValue) {
		drive(moveValue, getGyroOffset());
	}

	// ****************************************************************************
	// **********************             TELEOP            **********************
	// ****************************************************************************

	@Override
	public void teleopPeriodic() {
		
		// update the SmartDashboard
		gyroController.printAngle();
		gyroController.printCentralAngle();
		
        if (controller.isResettingNavx()) {
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
        
        if (controller.isRotateToZero()) {
        	autonomousCommand = new TurnToCentralAngle(0.0);
        }
        else if (controller.isRotatingToNinety()) {
        	autonomousCommand = new TurnToCentralAngle(90.0);
        }
        else if (controller.isRotatingToNegativeNinety()) {
        	autonomousCommand = new TurnToCentralAngle(-90.0);
        }
        else if (controller.isRotatingToOneHundredEighty()) {
        	autonomousCommand = new TurnToCentralAngle(180.0);
        }
        else if (controller.isRotatingCustom()) {
        	double newAngle = getGyroAngle() + 45;
        	
        	// Limit the angle to our range of 0 to 360
        	if (newAngle > 360) {
        		newAngle = newAngle - 360;
        	}
        	
        	autonomousCommand = new TurnToAngle(newAngle);
        }
        else if (controller.isRotatingCustomNegative()) {
        	double newAngle = getGyroAngle() - 45;
        	
        	// Limit the angle to our range of 0 to 360
        	if (newAngle < 0) {
        		newAngle = newAngle + 360;
        	}

        	autonomousCommand = new TurnToAngle(newAngle);
        }
        
        // Updates the scheduler to the selected autonomous command. 
        // If none is chosen, the scheduler runs the default command, driving with the joystick.
        if (autonomousCommand != null) {
        	Scheduler.getInstance().removeAll();
        	Scheduler.getInstance().add(autonomousCommand);
        }
	}
}
