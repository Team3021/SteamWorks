package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.DriveWithJoystick;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToCentralAngle;
import org.usfirst.frc.team3021.robot.controller.GyroController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Drive extends Subsystem {

	// Member Attributes
	
	// main drive
	private RobotDrive robotDrive;
	
	// motor controllers
	private CANTalon RightRear;
	private CANTalon RightFront;
	private CANTalon LeftRear;
	private CANTalon LeftFront;

	private GyroController gyroController;

	private DriveCommand defaultCommand;

	private DriveCommand autonomousCommand;
	
	// gyro controller
	
	public Drive() {
		
		LeftFront = new CANTalon(25);
		LeftRear = new CANTalon(24);
		RightFront = new CANTalon(22);
		RightRear = new CANTalon(23);
		
		robotDrive = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);

		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
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
		robotDrive.arcadeDrive(moveValue, turnValue, false);
	}	

	// ****************************************************************************
	// **********************             TELE OP            **********************
	// ****************************************************************************

	@Override
	public void teleopPeriodic() {
		
		// update the SmartDashboard
		gyroController.printAngle();
		gyroController.printCentralAngle();
		
        if (controller.isResettingNavx()) {
        	resetGyro();
        }
        
        if (autonomousCommand != null && autonomousCommand.isRunning()) {
        	return;
        }
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
        
        // Enable DriveWithJoystick if there are no other commands
        if (autonomousCommand != null) {
        	Scheduler.getInstance().removeAll();
        	Scheduler.getInstance().add(autonomousCommand);
        }
	}
}
