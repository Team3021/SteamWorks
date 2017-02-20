package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToDistance extends DriveCommand {
	
	protected double speed = 0;
	protected double desiredDistance = 0;
	
	public DriveToDistance(double speed, double distance) {
		super();
		
		this.speed = speed;
		this.desiredDistance = distance;
	}

	@Override
	protected void initialize() {
		System.out.println("Start DriveToDistance");
		Stanley.robotDrive.resetGyro();
		Stanley.robotDrive.resetEncoders();
	}
	
	@Override
	protected void execute() {
		Stanley.robotDrive.driveForwardWithGyro(speed);
	}
	
	@Override
	protected void end() {
		System.out.println("End DriveToDistance");
	}
	
	@Override
	protected boolean isFinished() {
		
		double remaingDifference = desiredDistance - Stanley.robotDrive.getDistance();
		
		SmartDashboard.putNumber("DriveToDistance : distance remaing", remaingDifference);
		
		return (remaingDifference < 0.5);
	}
	
	@Override
	public synchronized boolean isInterruptible() {
		return false;
	}
}
