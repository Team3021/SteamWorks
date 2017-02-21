package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.ClimberCommand;

public class Climb extends ClimberCommand {

	public Climb(double seconds) {
		setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Stanley.climber.startMotor();
	}

	@Override
	protected void end() {
		Stanley.climber.stopMotor();
	}

	@Override
	protected void interrupted() {
		Stanley.climber.stopMotor();
	}
}
