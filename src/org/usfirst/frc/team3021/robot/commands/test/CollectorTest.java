package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.CollectorCommand;

import edu.wpi.first.wpilibj.Timer;

public class CollectorTest extends CollectorCommand {

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
}
