package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.driving.DriveWithJoystick;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngle;
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
		
		defaultCommand = new DriveWithJoystick();
	}
	
	@Override
	protected void initDefaultCommand() {
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
        if (controller.isResettingNavx()) {
        	resetGyro();
        }
        
        if (controller.isRotateToZero()) {
        	Scheduler.getInstance().add(new TurnToAngle(0.0));
        }
        else if (controller.isRotatingToNinety()) {
        	Scheduler.getInstance().add(new TurnToAngle(90.0));
        }
        else if (controller.isRotatingToNegativeNinety()) {
        	Scheduler.getInstance().add(new TurnToAngle(-90.0));
        }
        else if (controller.isRotatingToOneHundredEighty()) {
        	Scheduler.getInstance().add(new TurnToAngle(180.0));
        } else {
        	Scheduler.getInstance().add(defaultCommand);
        }
	}
}
