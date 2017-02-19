package org.usfirst.frc.team3021.robot.commands.test;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;

import edu.wpi.first.wpilibj.Timer;

public class LauncherTest extends LauncherCommand {

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
		System.out.println("Executing Launcher Test");
		
		Stanley.launcher.startWheel();
		Timer.delay(5);
		Stanley.launcher.stopWheel();

		Timer.delay(5);
		
		Stanley.launcher.startIndexer();
		Timer.delay(5);
		Stanley.launcher.stopIndexer();
		
		Timer.delay(5);

		Stanley.launcher.startAgitator();
		Timer.delay(5);
		Stanley.launcher.stopAgitator();
		
		Timer.delay(5);
	}
}
