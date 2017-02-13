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
		System.out.println("Auto selected: " + configuration.getSelectedAutonomousMode());
	}

	@Override
	public void autonomousPeriodic() {
		String autoSelected = configuration.getSelectedAutonomousMode();
		
		switch (autoSelected) {
			case Configuration.AUTONOMOUS_DEFALUT:
			default:
				// Put default auto code here
				break;
		}
	}

	@Override
	public void teleopInit() {

		String selectedController = configuration.getSelectedJoystickMode();
		int joyStickPort = configuration.getSelectedJoyStickPort();

		if (selectedController.equals(Configuration.THRUSTMASTER)) {
			controller = new ThrustmasterController(joyStickPort);
		}
		else if (selectedController.equals(Configuration.XBOX360)) {
			controller = new Xbox360Controller(joyStickPort);
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
		// TODO
	}
}

