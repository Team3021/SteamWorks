package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.test.SubsystemTest;
import org.usfirst.frc.team3021.robot.controller.Controller;
import org.usfirst.frc.team3021.robot.subsystem.Climber;
import org.usfirst.frc.team3021.robot.subsystem.Collector;
import org.usfirst.frc.team3021.robot.subsystem.Drive;
import org.usfirst.frc.team3021.robot.subsystem.Launcher;
import org.usfirst.frc.team3021.robot.subsystem.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Stanley extends IterativeRobot {
	// Member Attributes
	public static Configuration configuration;
	
	public static Drive robotDrive;
	
	public static Launcher launcher;
	public static Collector collector;
	public static Climber climber;
	
	public static Vision vision;
	
	public static Controller mainController;
	public static Controller auxController;

	public Stanley() {
		super();
		
		configuration = new Configuration();
		
		vision = new Vision();
		
		robotDrive = new Drive();
		launcher = new Launcher();
		collector = new Collector();
		climber = new Climber();
		
		configuration.addSubsystemsToDashboard();
		
		configuration.addCommandsToDashboard();
		
		configuration.addControllerChoices();
		
		configuration.addAutonmousChoices();
	}
	
	@Override
	public void robotInit() {
		System.out.println("Robot initializing...");
		
		mainController = configuration.initializeMainController();

		auxController = configuration.initializeAuxController();

		robotDrive.setControllers(mainController, auxController);
		launcher.setControllers(mainController, auxController);
		collector.setControllers(mainController, auxController);
		climber.setControllers(mainController, auxController);
		vision.setControllers(mainController, auxController);
	}

	@Override
	public void robotPeriodic() {
		// Do nothing to prevent warnings
	}

	@Override
	public void autonomousInit() {
		// Stop any commands that might be left running from another mode
		Scheduler.getInstance().removeAll();
		
		String autoMode = configuration.getAutonomousMode();
		
		System.out.println("Autonomous mode: " + autoMode);
		
		Command autoCommand = configuration.getAutonomousCommand();
		
		Scheduler.getInstance().add(autoCommand);
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// Stop any commands that might be left running from another mode
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		robotDrive.teleopPeriodic();
		launcher.teleopPeriodic();
		collector.teleopPeriodic();
		climber.teleopPeriodic();
		vision.teleopPeriodic();
	}

	@Override
	public void testInit() {
		// Stop any commands that might be left running from another mode
		Scheduler.getInstance().removeAll();

		Scheduler.getInstance().add(new SubsystemTest());
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
		// Do nothing to prevent warnings
	}

	@Override
	public void disabledPeriodic() {
		// Do nothing to prevent warnings
	}
}

