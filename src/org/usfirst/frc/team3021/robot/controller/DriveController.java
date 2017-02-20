package org.usfirst.frc.team3021.robot.controller;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveController {

	// DRIVE SYSTEM
	private RobotDrive robotDrive;
	
	// TALON PORTS
	private static final int RIGHT_FRONT_PORT = 22;
	private static final int RIGHT_REAR_PORT = 23;
	private static final int LEFT_REAR_PORT = 24;
	private static final int LEFT_FRONT_PORT = 25;

	// TALONS
	private CANTalon rightRearTalon;
	private CANTalon rightFrontTalon;
	private CANTalon leftRearTalon;
	private CANTalon leftFrontTalon;
	
	// ENCODER CHANNELS
	private static final int LEFT_ENCODER_CHANNEL_A = 0;
	private static final int LEFT_ENCODER_CHANNEL_B = 1;
	
	private static final int RIGHT_ENCODER_CHANNEL_A = 2;
	private static final int RIGHT_ENCODER_CHANNEL_B = 3;
	
	// ENCODERS
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	// DISTANCE
	private static final int PULSE_PER_ROTATION = 256;
	private static final int WHEEL_DIAMETER = 4;
	private static final double WHEEL_CIRCUMFERAECE = WHEEL_DIAMETER * Math.PI;
	private static final double DISTANCE_PER_PULSE = WHEEL_CIRCUMFERAECE / PULSE_PER_ROTATION;


	public DriveController() {
		// TALONS
		leftFrontTalon = new CANTalon(LEFT_FRONT_PORT);
		leftRearTalon = new CANTalon(LEFT_REAR_PORT);
		rightFrontTalon = new CANTalon(RIGHT_FRONT_PORT);
		rightRearTalon = new CANTalon(RIGHT_REAR_PORT);
		
		// DRIVE DECLARATION
		robotDrive = new RobotDrive(leftFrontTalon, leftRearTalon, rightFrontTalon, rightRearTalon);
		robotDrive.setExpiration(0.1);

		// MOTOR INVERSIONS
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
		// ENCODERS
		leftEncoder = new Encoder(LEFT_ENCODER_CHANNEL_A, LEFT_ENCODER_CHANNEL_B, false, Encoder.EncodingType.k4X);
		leftEncoder.setMinRate(10);
		leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		
		rightEncoder = new Encoder(RIGHT_ENCODER_CHANNEL_A, RIGHT_ENCODER_CHANNEL_B, false, Encoder.EncodingType.k4X);
		rightEncoder.setMinRate(10);
		rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

		LiveWindow.addActuator("Drive", "Front Left Talon", leftFrontTalon);
		LiveWindow.addActuator("Drive", "Front Right Talon", rightFrontTalon);
		LiveWindow.addActuator("Drive", "Rear Left Talon", leftRearTalon);
		LiveWindow.addActuator("Drive", "Rear Right Talon", rightRearTalon);

		LiveWindow.addSensor("Drive Encoders", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Encoders", "Right Encoder", rightEncoder);
	}

	// ****************************************************************************
	// **********************            ENCODERS            **********************
	// ****************************************************************************
	
	public void printEncoderValues() {
		SmartDashboard.putNumber("Drive : Encoder Speed Left", leftEncoder.getRate());
		SmartDashboard.putNumber("Drive : Encoder Speed Right", rightEncoder.getRate());
	}

	public void printEncoderDistance(double distance) {
		SmartDashboard.putNumber("Drive : Encoder Distance", distance);
	}
	
	public double getEncoderDistance() {
		double distanceTraveledAverage = (getDistanceTraveled(rightEncoder)
				+ getDistanceTraveled(leftEncoder)) / 2;
		
		printEncoderDistance(distanceTraveledAverage);
		
		return distanceTraveledAverage;
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getDistanceTraveled(Encoder driveEncoder) {
		return driveEncoder.getDistance();
	}

	// ****************************************************************************
	// **********************            ACTIONS             **********************
	// ****************************************************************************

	public void drive(double moveValue, double turnValue) {
		robotDrive.arcadeDrive(moveValue, turnValue, false);
	}
	
	public void stop() {
		robotDrive.arcadeDrive(0, 0, false);
	}
	
}
