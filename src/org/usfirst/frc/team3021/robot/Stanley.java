package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.controller.*;
import org.usfirst.frc.team3021.robot.subsystem.*;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Stanley extends IterativeRobot {
	// Member Attributes
	private Configuration configuration;
	
	private Drive robotDrive;
	private Launcher launcher;
	private SubSystem collector;
	private Vision vision;
	
	private Controller controller;

	@Override
	public void robotInit() {
		configuration = new Configuration();
		robotDrive = new Drive();
		launcher = new Launcher();
		collector = new Collector();
		vision = new Vision();
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

		if (selectedController == Configuration.THRUSTMASTER) {
			controller = new ThrustmasterController(joystickPort);
		}
		else if (selectedController == Configuration.XBOX360) {
			controller = new Xbox360Controller(joystickPort);
		}

		robotDrive.setController(controller);
		launcher.setController(controller);
		collector.setController(controller);
		vision.setController(controller);
		
	}
	
	@Override
	public void teleopPeriodic() {
		robotDrive.teleopPeriodic();
		launcher.teleopPeriodic();
		collector.teleopPeriodic();
		vision.teleopPeriodic();
	}

	@Override
	public void testInit() {
		super.testInit();
	}

	@Override
	public void testPeriodic() {
		launcher.testPeriodic();
		collector.testPeriodic();
		robotDrive.testPeriodic();
		vision.testPeriodic();
	}
}

