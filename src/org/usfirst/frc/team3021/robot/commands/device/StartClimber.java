package org.usfirst.frc.team3021.robot.commands.device;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.ClimberCommand;

public class StartClimber extends ClimberCommand {

	@Override
	protected void execute() {
		Stanley.climber.startMotor();
	}

	@Override
	protected void interrupted() {
		Stanley.climber.stopMotor();
	}
}
