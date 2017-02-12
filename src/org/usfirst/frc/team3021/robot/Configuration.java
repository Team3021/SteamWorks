package org.usfirst.frc.team3021.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Configuration {
	public static final String AUTONOMOUS_DEFALUT = "Default";
	
	public static final String THRUSTMASTER = "ThrustMaster";
	public static final String XBOX360 = "Xbox360";
	
	private final String THRUSTMASTER_PORT = THRUSTMASTER + " : Port";
	private final String XBOX360_PORT = XBOX360 + " : Port";
	
	private final int THRUSTMASTER_PORT_DEFAULT = 0;
	private final int XBOX360_PORT_DEFUALT = 0;
	
	private SendableChooser<String> autonomousChooser = new SendableChooser<>();
	private SendableChooser<String> controllerChooser = new SendableChooser<>();

	public Configuration() {
		autonomousChooser.addDefault("Autonmous : " + AUTONOMOUS_DEFALUT, AUTONOMOUS_DEFALUT);
		SmartDashboard.putData("Auto choices", autonomousChooser);
		
		controllerChooser.addDefault("Joystick : " + THRUSTMASTER, THRUSTMASTER );
		controllerChooser.addObject("Joystick : " + XBOX360, XBOX360);
		SmartDashboard.putData("Select Controller", controllerChooser);
		
		SmartDashboard.putNumber(THRUSTMASTER_PORT, THRUSTMASTER_PORT_DEFAULT);
		SmartDashboard.putNumber(XBOX360_PORT, XBOX360_PORT_DEFUALT);
	}

	public String getSelectedAutonomousMode() {
		return autonomousChooser.getSelected();
	}

	public String getSelectedJoystickMode() {
		return controllerChooser.getSelected();
	}
	
	public int getSelectedJoyStickPort() {
		
		int port = 0;

		if (getSelectedJoystickMode() == THRUSTMASTER) {
			port = (int) SmartDashboard.getNumber(THRUSTMASTER_PORT, THRUSTMASTER_PORT_DEFAULT);
		}
		else if (getSelectedJoystickMode() == XBOX360) {
			port = (int) SmartDashboard.getNumber(XBOX360_PORT, XBOX360_PORT_DEFUALT);
		}
		
		return port;
	}
}
