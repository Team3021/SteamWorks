package org.usfirst.frc.team3021.robot.commands;

import org.usfirst.frc.team3021.robot.Stanley;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LauncherTest extends Command {

	public LauncherTest() {
		requires(Stanley.launcher);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Stanley.launcher.stopWheel();
		Stanley.launcher.stopIndexer();
		Stanley.launcher.stopAgitator();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Stanley.launcher.startWheel();
		Timer.delay(20);
		Stanley.launcher.stopWheel();

		Timer.delay(20);
		
		Stanley.launcher.startIndexer();
		Timer.delay(20);
		Stanley.launcher.stopIndexer();
		
		Timer.delay(20);

		Stanley.launcher.stopAgitator();
		Timer.delay(20);
		Stanley.launcher.startAgitator();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		
	}
}
