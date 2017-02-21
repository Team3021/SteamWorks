package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.test.SubsystemTest;
import org.usfirst.frc.team3021.robot.controller.AuxController;
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
	public static Controller auxController;

	public Stanley() {
		super();
		
		configuration = new Configuration();
		
		robotDrive = new Drive();
		launcher = new Launcher();
		collector = new Collector();
		vision = new Vision();
		
		
		configuration.addSubsystemsToDashboard();
		
		configuration.addCommandsToDashboard();

	}
	
	@Override
	public void robotInit() {
		
	}

	@Override
	public void autonomousInit() {
		// Stop any commands that might be left running from another mode
		Scheduler.getInstance().removeAll();
		
		String autoSelected = configuration.getAutonomousMode();
		
		System.out.println("Auto selected: " + autoSelected);
		
		switch (autoSelected) {
			case Configuration.AUTONOMOUS_DEFALUT:
			default:
				// Put default auto code here
				break;
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// Stop any commands that might be left running from another mode
		Scheduler.getInstance().removeAll();

		String selectedController = configuration.getMainControllerMode();
		int mainControllerPort = configuration.getMainControllerPort();

		if (selectedController.equals(Configuration.THRUSTMASTER)) {
			controller = new ThrustmasterController(mainControllerPort);
		}
		else if (selectedController.equals(Configuration.XBOX360)) {
			System.out.println("***************XBOX***************");
			controller = new Xbox360Controller(mainControllerPort);
			
			if (!controller.isXbox()) {
				System.out.println("WARNING !!! NOT XBOX CONTROLLER");
			}
		}

		auxController = new AuxController(configuration.getAuxPanelPort());
		
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
		// Stop any commands that might be left running from another mode
		Scheduler.getInstance().removeAll();

		Scheduler.getInstance().add(new SubsystemTest());
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}

