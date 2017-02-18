package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.Timer;

public class CollectorTest extends Command {

	public CollectorTest() {
		requires(Stanley.collector);
	}

	@Override
	protected void initialize() {
		Stanley.collector.stopMotor();
	}

	@Override
	protected void execute() {
		System.out.println("Executing Collector Test");
		
		// turn on the motor
		Stanley.collector.startMotor();
		
		// run the motor for a time
		Timer.delay(5);
	}

	@Override
	protected void end() {
		// turn off the motor
		Stanley.collector.stopMotor();
		
		// run the motor for a time
		Timer.delay(5);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		// turn off the motor
		Stanley.collector.stopMotor();
	}
}
