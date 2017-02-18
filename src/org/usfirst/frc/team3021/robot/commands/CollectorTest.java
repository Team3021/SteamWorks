package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CollectorTest extends Command {

	public CollectorTest() {
		requires(Stanley.collector);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Stanley.collector.stopMotor();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// turn on the motor
		Stanley.collector.startMotor();
		
		// run the motor for a time
		Timer.delay(20);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// turn off the motor
		Stanley.collector.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		// turn off the motor
		Stanley.collector.stopMotor();
	}
}
