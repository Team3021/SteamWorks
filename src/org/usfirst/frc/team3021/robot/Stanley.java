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
		robotDrive = new Drive();
		launcher = new Launcher();
		
		autonomousChooser.addDefault("Default Auto", defaultAuto);
		SmartDashboard.putData("Auto choices", autonomousChooser);
		
		controllerChooser.addDefault("ThrustMaster Joystick", THRUSTMASTER );
		controllerChooser.addObject("Xbox 360 Controller", XBOX360);
		SmartDashboard.putData("Select Controller", controllerChooser);
		
		SmartDashboard.putNumber("ThrustMaster Port", 0);
		SmartDashboard.putNumber("Xbox 360 Port", 1);
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
	public void teleopInit(){
		selectedController = controllerChooser.getSelected();
		int thrustMasterPort = (int) SmartDashboard.getNumber("ThrustMaster Port", 0);
		int xbox360Port = (int) SmartDashboard.getNumber("Xbox 360 Port", 0);

		if(selectedController == THRUSTMASTER){
			controller = new ThrustMasterController(thrustMasterPort);
		}
		else if (selectedController == XBOX360) {
			controller = new Xbox360Controller(xbox360Port);
		}

		robotDrive.setController(controller);
		launcher.setController(controller);
		
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

