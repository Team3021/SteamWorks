package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.ClimberCommand;

import edu.wpi.first.wpilibj.Timer;

public class ClimberTest extends ClimberCommand {

	@Override
	protected void initialize() {
		Stanley.climber.stopMotor();
	}

	@Override
	protected void execute() {
		System.out.println("Executing Climber Test");
		
		// turn on the motor
		Stanley.climber.startMotor();
		
		// run the motor for a time
		Timer.delay(5);
	}

	@Override
	protected void end() {
		// turn off the motor
		Stanley.climber.stopMotor();
		
		// run the motor for a time
		Timer.delay(5);
	}
}
