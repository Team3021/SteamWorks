package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.device.*;
import org.usfirst.frc.team3021.robot.commands.driving.*;
import org.usfirst.frc.team3021.robot.commands.test.*;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Configuration {
	
	public static final String AUTONOMOUS_DEFALUT = "Default";
	
	public static final String THRUSTMASTER = "ThrustMaster";
	public static final String XBOX360 = "Xbox360";
	public static final String USBBUTTONS = "GameController";

	private static final String VISION_ENABLED = "ON";
	private static final String VISION_DISABLED = "OFF";
	
	private final String THRUSTMASTER_PORT = "ThrustMaster : Port";
	private final String XBOX360_PORT = "Xbox360 : Port";
	private final String USBBUTTONS_PORT = "GameController : Port";
	
	private final int THRUSTMASTER_PORT_DEFAULT = 0;
	private final int XBOX360_PORT_DEFUALT = 0;
	private final int USBBUTTONS_PORT_DEFAULT = 1;
	
	private SendableChooser<String> autonomousChooser = new SendableChooser<>();
	private SendableChooser<String> controllerChooser = new SendableChooser<>();
	private SendableChooser<String> visionChooser = new SendableChooser<>();

	public Configuration() {
		
		addAutonmousChoices();
		
		addControllerChoices();
		
		addVisionChoices();
		
		addSubsystemsToDashboard();
		
		addCommandsToDashboard();
	}

	private void addAutonmousChoices() {
		autonomousChooser.addDefault("Autonmous : " + AUTONOMOUS_DEFALUT, AUTONOMOUS_DEFALUT);
		SmartDashboard.putData("Auto choices", autonomousChooser);
	}

	private void addControllerChoices() {
		controllerChooser.addDefault(THRUSTMASTER, THRUSTMASTER );
		controllerChooser.addObject(XBOX360, XBOX360);
		SmartDashboard.putData("Select Controller", controllerChooser);
		
		SmartDashboard.putNumber(THRUSTMASTER_PORT, THRUSTMASTER_PORT_DEFAULT);
		SmartDashboard.putNumber(XBOX360_PORT, XBOX360_PORT_DEFUALT);
		SmartDashboard.putNumber(USBBUTTONS_PORT, USBBUTTONS_PORT_DEFAULT);
	}

	private void addVisionChoices() {
		visionChooser.addDefault(VISION_DISABLED, VISION_DISABLED);
		visionChooser.addObject(VISION_ENABLED, VISION_ENABLED);
		SmartDashboard.putData("Vision", visionChooser);
	}

	private void addSubsystemsToDashboard() {
		SmartDashboard.putData(Stanley.robotDrive);
		SmartDashboard.putData(Stanley.collector);
		SmartDashboard.putData(Stanley.launcher);
		SmartDashboard.putData(Stanley.vision);
	}
	
	private void addCommandsToDashboard() {
		SmartDashboard.putData(Scheduler.getInstance());
		
		// Add test commands to dashboard
		SmartDashboard.putData(new SubsystemTest());
		SmartDashboard.putData(new DriveTest());
		SmartDashboard.putData(new CollectorTest());
		SmartDashboard.putData(new LauncherTest());
		SmartDashboard.putData(new VisionTest());
		
		// Add drive commands to dashboard
		SmartDashboard.putData(new StopDriving());
		SmartDashboard.putData(new DriveToDistance(0.3, 3));
		SmartDashboard.putData(new TurnToAngle(90.0));
		SmartDashboard.putData(new TurnToCentralAngle(90.0));
		
		// Add device commands to dashboard
		SmartDashboard.putData(new Agitate(5.0));
		SmartDashboard.putData(new Index(5.0));
		SmartDashboard.putData(new SpinWheel(5.0));
		SmartDashboard.putData(new Collect(5.0));
	}
	
	public String getAutonomousMode() {
		return autonomousChooser.getSelected();
	}

	public String getJoystickMode() {
		String selected = controllerChooser.getSelected();
		
		SmartDashboard.putString("Configuration : joystick mode",  selected);
		
		return selected;
	}
	
	public int getJoystickPort() {
		
		int port = 0;

		if (getJoystickMode().equals(THRUSTMASTER)) {
			port = (int) SmartDashboard.getNumber(THRUSTMASTER_PORT, THRUSTMASTER_PORT_DEFAULT);
		}
		else if (getJoystickMode().equals(XBOX360)) {
			port = (int) SmartDashboard.getNumber(XBOX360_PORT, XBOX360_PORT_DEFUALT);
		}
		
		return port;
	}
	
	public boolean isVisionEnabled() {
		String selected = visionChooser.getSelected();
		
		if (selected.equals(VISION_ENABLED)) {
			return true;
		} else {
			return false;
		}
	}
}
