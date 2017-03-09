package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.DriveWithJoystick;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle45;
import org.usfirst.frc.team3021.robot.commands.driving.TurnLeftToAngle90;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle45;
import org.usfirst.frc.team3021.robot.commands.driving.TurnRightToAngle90;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngle180;
import org.usfirst.frc.team3021.robot.controller.DriveController;
import org.usfirst.frc.team3021.robot.controller.GyroController;
import org.usfirst.frc.team3021.robot.controller.TargetController;

import edu.wpi.first.wpilibj.command.Scheduler;

public class Drive extends Subsystem {

	private DriveController driveController;
	
	private GyroController gyroController;
	
	private TargetController targetController;

	private DriveCommand defaultCommand;

	private DriveCommand autonomousCommand;
	
	public Drive() {
		driveController = new DriveController();
		
		gyroController = new GyroController();
		
		if (Stanley.vision.isVisionEnabled()) {
			targetController = new TargetController();
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		defaultCommand = new DriveWithJoystick();
		setDefaultCommand(defaultCommand);
	}

	// ****************************************************************************
	// **********************              MOVE              **********************
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
	// and thus the gyro offset is is a deviation from going straight forward
	public void moveWithGyro(double moveValue) {
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

	public void turnToTarget(double desirecPosition) {
		setDesiredTargetPostion(desirecPosition);
		
		double turnValue = getTargetTurnValue();
		
		drive(0, turnValue);
	}

	// ****************************************************************************
	// **********************              GYRO              **********************
	// ****************************************************************************

	public void resetGyro() {
		gyroController.resetGyro();
	}

	private void setGyroDesiredAngle(double angle) {
		gyroController.setDesiredAngle(angle);
	}

	private double getGyroTurnValue() {
		return gyroController.getTurnValue();
	}

	public boolean isGyroOnTarget() {
		return gyroController.isOnTarget();
	}

	public boolean isGyroRotating() {
		return gyroController.isRotating();
	}

	public boolean isGyroMoving() {
		return gyroController.isMoving();
	}

	// ****************************************************************************
	// **********************             TARGET             **********************
	// ****************************************************************************

	public double getDesiredTargetPosition() {
		if (!Stanley.vision.isVisionEnabled()) {
			return 0;
		}
		
		return targetController.calculateDesiredPosition();
	}
	
	private void setDesiredTargetPostion(double value) {
		if (!Stanley.vision.isVisionEnabled()) {
			return;
		}
		
		targetController.setDesiredPosition(value);
	}
	
	private double getTargetTurnValue() {
		if (!Stanley.vision.isVisionEnabled()) {
			return 0;
		}
		
		return targetController.getTurnValue();
	}
	
	// ****************************************************************************
	// **********************             TELEOP            **********************
	// ****************************************************************************

	@Override
	public void teleopPeriodic() {
		
		driveController.getEncoderDistance();
		
        if (mainController.isStoppingCommands() || auxController.isStoppingCommands()) {
        	Scheduler.getInstance().removeAll();
        }
		
        if (mainController.isResettingNavx() || auxController.isResettingNavx()) {
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
        	autonomousCommand = new TurnRightToAngle90();
        }
        else if (mainController.isRotatingToNegativeNinety()) {
        	autonomousCommand = new TurnLeftToAngle90();
        }
        else if (mainController.isRotatingToOneHundredEighty()) {
        	autonomousCommand = new TurnToAngle180();
        }
        else if (mainController.isRotatingRight45()) {
        	autonomousCommand = new TurnRightToAngle45();
        }
        else if (mainController.isRotatingLeft45()) {
        	autonomousCommand = new TurnLeftToAngle45();
        }
        
        // Updates the scheduler to the selected autonomous command. 
        // If none is chosen, the scheduler runs the default command, driving with the joystick.
        if (autonomousCommand != null) {
        	Scheduler.getInstance().removeAll();
        	Scheduler.getInstance().add(autonomousCommand);
        }
	}
}
