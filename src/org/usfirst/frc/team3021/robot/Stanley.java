package org.usfirst.frc.team3021.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Stanley extends IterativeRobot {
	// Member Attributes
	final String defaultAuto = "Default";
	final String THRUSTMASTER = "ThrustMaster";
	final String XBOX360 = "Xbox360";
	
	String autoSelected;
	String selectedController;
	
	SendableChooser<String> autonomousChooser = new SendableChooser<>();
	SendableChooser<String> controllerChooser = new SendableChooser<>();
	Drive robotDrive;
	Launcher launcher;
	Controller controller;

	@Override
	public void robotInit() {
		
		autonomousChooser.addDefault("Default Auto", defaultAuto);
		SmartDashboard.putData("Auto choices", autonomousChooser);
		
		controllerChooser.addDefault("ThrustMaster Joystick", THRUSTMASTER );
		controllerChooser.addObject("Xbox 360 Controller", XBOX360);
		
		selectedController = controllerChooser.getSelected();
		
		if(selectedController == THRUSTMASTER){
			controller = new ThrustMasterController(0);
		}
		else if (selectedController == XBOX360) {
			controller = new Xbox360Controller(0);
		}
		
		robotDrive = new Drive(controller);
		launcher = new Launcher(controller);
	}

	@Override
	public void autonomousInit() {
		autoSelected = autonomousChooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	@Override
	public void teleopPeriodic() {
		robotDrive.teleopPeriodic();
		launcher.teleopPeriodic();
	}

	@Override
	public void testPeriodic() {
	}
}

