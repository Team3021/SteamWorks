package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.device.*;
import org.usfirst.frc.team3021.robot.commands.driving.*;
import org.usfirst.frc.team3021.robot.commands.test.*;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Configuration {
	
	public static final String AUTONOMOUS_DEFALUT = "Default";
	
	public static final String THRUSTMASTER = "ThrustMaster";
	public static final String XBOX360 = "Xbox360";

	private final String MAIN_CONTROLLER_PORT = "Main Controller : Port";
	private final int MAIN_CONTROLLER_PORT_DEFAULT = 0;

	public static final String AUX_PANEL_STATUS = "AuxPanel";
	private final String AUX_PANEL_PORT = "AuxPanel : Port";
	private final int AUX_PANEL_PORT_DEFAULT = 1;
	
	private final String VISION_SUBSYSTEM_STATUS = "Vision : Enabled";
	
	private SendableChooser<String> autonomousChooser = new SendableChooser<>();
	private SendableChooser<String> controllerChooser = new SendableChooser<>();

	public Configuration() {
		
		addAutonmousChoices();
		
		addControllerChoices();
	}

	private void addAutonmousChoices() {
		autonomousChooser.addDefault("Autonmous : " + AUTONOMOUS_DEFALUT, AUTONOMOUS_DEFALUT);
		SmartDashboard.putData("Auto choices", autonomousChooser);
	}

	private void addControllerChoices() {
		controllerChooser.addDefault(THRUSTMASTER, THRUSTMASTER );
		controllerChooser.addObject(XBOX360, XBOX360);
		SmartDashboard.putData("Select Controller", controllerChooser);
	}

	public void addSubsystemsToDashboard() {
		SmartDashboard.putData(Stanley.robotDrive);
		SmartDashboard.putData(Stanley.collector);
		SmartDashboard.putData(Stanley.launcher);
		SmartDashboard.putData(Stanley.vision);
	}
	
	public void addCommandsToDashboard() {
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
		SmartDashboard.putData(new DriveForTime(0.3, 3));
		SmartDashboard.putData(new TurnToAngle(90.0));
		SmartDashboard.putData(new TurnToCentralAngle(90.0));
		SmartDashboard.putData(new ResetGyro());
		SmartDashboard.putData(new ResetEncoders());
		
		// Add device commands to dashboard
		SmartDashboard.putData(new Agitate(5.0));
		SmartDashboard.putData(new Index(5.0));
		SmartDashboard.putData(new SpinWheel(5.0));
		SmartDashboard.putData(new Collect(5.0));
	}
	
	public String getAutonomousMode() {
		return autonomousChooser.getSelected();
	}

	public String getMainControllerMode() {
		String selected = controllerChooser.getSelected();
		
		SmartDashboard.putString("Configuration : joystick mode",  selected);
		
		return selected;
	}
	
	public int getMainControllerPort() {
		return Preferences.getInstance().getInt(MAIN_CONTROLLER_PORT, MAIN_CONTROLLER_PORT_DEFAULT);
	}
	
	public int getAuxPanelPort() {
		return Preferences.getInstance().getInt(AUX_PANEL_PORT, AUX_PANEL_PORT_DEFAULT);
	}
	
	public boolean isAuxPanelEnabled() {
		return Preferences.getInstance().getBoolean(AUX_PANEL_STATUS, false);
	}
	
	public boolean isVisionEnabled() {
		return Preferences.getInstance().getBoolean(VISION_SUBSYSTEM_STATUS, false);
	}
}
