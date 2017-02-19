package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.Command;
import org.usfirst.frc.team3021.robot.commands.test.SubsystemTest;
import org.usfirst.frc.team3021.robot.controller.ThrustmasterController;
import org.usfirst.frc.team3021.robot.controller.Xbox360Controller;
import org.usfirst.frc.team3021.robot.subsystem.Collector;
import org.usfirst.frc.team3021.robot.subsystem.Drive;
import org.usfirst.frc.team3021.robot.subsystem.Launcher;
import org.usfirst.frc.team3021.robot.subsystem.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Stanley extends IterativeRobot {
	// Member Attributes
	public static Configuration configuration;
	
	public static Drive robotDrive;
	public static Launcher launcher;
	public static Collector collector;
	public static Vision vision;
	
	public static Controller controller;

	public Stanley() {
		super();
		
		configuration = new Configuration();
		robotDrive = new Drive();
		launcher = new Launcher();
		collector = new Collector();
		vision = new Vision();
	}
	
	@Override
	public void robotInit() {
		
	}

	@Override
	public void autonomousInit() {
		System.out.println("Auto selected: " + configuration.getAutonomousMode());
	}

	@Override
	public void autonomousPeriodic() {
		String autoSelected = configuration.getAutonomousMode();
		
		switch (autoSelected) {
			case Configuration.AUTONOMOUS_DEFALUT:
			default:
				// Put default auto code here
				break;
		}
	}

	@Override
	public void teleopInit() {

		String selectedController = configuration.getJoystickMode();
		int joystickPort = configuration.getJoystickPort();

		if (selectedController.equals(Configuration.THRUSTMASTER)) {
			controller = new ThrustmasterController(joystickPort);
		}
		else if (selectedController.equals(Configuration.XBOX360)) {
			System.out.println("***************XBOX***************");
			controller = new Xbox360Controller(joystickPort);
		}

		robotDrive.setController(controller);
		launcher.setController(controller);
		collector.setController(controller);
		vision.setController(controller);
		
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		robotDrive.teleopPeriodic();
		launcher.teleopPeriodic();
		collector.teleopPeriodic();
		vision.teleopPeriodic();
	}

	@Override
	public void testInit() {
		Scheduler.getInstance().add(new SubsystemTest());
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}

