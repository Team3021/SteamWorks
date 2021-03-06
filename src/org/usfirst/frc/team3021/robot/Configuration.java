package org.usfirst.frc.team3021.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.auto.*;
import org.usfirst.frc.team3021.robot.commands.device.*;
import org.usfirst.frc.team3021.robot.commands.driving.*;
import org.usfirst.frc.team3021.robot.commands.test.*;

import org.usfirst.frc.team3021.robot.controller.AttackThreeController;
import org.usfirst.frc.team3021.robot.controller.AuxController;
import org.usfirst.frc.team3021.robot.controller.Controller;
import org.usfirst.frc.team3021.robot.controller.DefaultController;
import org.usfirst.frc.team3021.robot.controller.Xbox360Controller;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Configuration {
	
	private static final String NO_AUTONOMOUS = "No Command";
	
	private static final String NO_CONTROLLER = "No Controller";
	private static final String ATTACK_THREE = "Attack Three";
	private static final String XBOX360 = "Xbox360";

	private final String PREF_MAIN_CONTROLLER_PORT = "Controller.main.port";
	private final int MAIN_CONTROLLER_PORT_DEFAULT = 0;

	private  final String PREF_AUX_PANEL_STATUS = "Controller.aux.enabled";
	private final String PREF_AUX_PANEL_PORT = "Controller.aux.port";
	private final int AUX_PANEL_PORT_DEFAULT = 1;

	private  final String DASHBOARD_COMMANDS_ENABLED = "Config.dashboard.commands.enabled";
	private  final String DASHBAORD_SUBSYSTEMS_ENABLED = "Config.dashboard.subsystems.enabled";
	
	private SendableChooser<String> autonomousChooser = new SendableChooser<>();
	private SendableChooser<String> controllerChooser = new SendableChooser<>();
	
	private List<Subsystem> subsystems = new ArrayList<Subsystem>();

	private List<Command> testCommands = new ArrayList<Command>();
	private List<Command> moveCommands = new ArrayList<Command>();
	private List<Command> turnCommands = new ArrayList<Command>();
	private List<Command> deviceCommands = new ArrayList<Command>();
	private List<Command> autoCommands = new ArrayList<Command>();

	public void addAutonmousChoices() {
		autonomousChooser.addDefault("[Red] [Left] to [Left Gear]", "[Red] [Left] to [Left Gear]");
		
		autonomousChooser.addObject(NO_AUTONOMOUS, NO_AUTONOMOUS);
		
		for (Command command : autoCommands) {
			autonomousChooser.addObject(command.getName(), command.getName());
		}
		
		SmartDashboard.putData("Autonomous Mode", autonomousChooser);
	}

	public void addControllerChoices() {
		controllerChooser.addDefault(ATTACK_THREE, ATTACK_THREE);
		
		SmartDashboard.putData("Main Controller Mode", controllerChooser);
	}

	public void addSubsystemsToDashboard() {
		
	}

	private void addSubsystemsToSmartDashboard(List<Subsystem> subsystems) {
		boolean isDashboardSubsystemsEnabled = Preferences.getInstance().getBoolean(DASHBAORD_SUBSYSTEMS_ENABLED, false);
		
		if (!isDashboardSubsystemsEnabled) {
			return;
		}
		
		for (Subsystem subsystem : subsystems) {
			SmartDashboard.putData(subsystem);
		}
	}
	
	public void addCommandsToDashboard() {
		double speed = DriveCommand.getAutonomousMoveSpeed();
		double time = DriveCommand.getAutonomousMoveTime();
		double distance = DriveCommand.getAutonomousMoveDistance();
		
		SmartDashboard.putData(Scheduler.getInstance());

		// ****************************************************************************
		// **********************        AUTO COMMANDS           **********************
		// ****************************************************************************
		
		autoCommands.add(new RedStartLeftToLeftGear());
		autoCommands.add(new RedStartCenterToCenterGear());
		autoCommands.add(new RedStartRightToRightGear());
		autoCommands.add(new RedStartCenterToCenterGearToCrossLine());
		
		autoCommands.add(new BlueStartLeftToLeftGear());
		autoCommands.add(new BlueStartCenterToCenterGear());
		autoCommands.add(new BlueStartRightToRightGear());
		autoCommands.add(new BlueStartCenterToCenterGearToCrossLine());

		// Add commands to dashboard
		addCommandsToSmartDashboard("Autonomous", autoCommands);
	}

	private void addCommandsToSmartDashboard(String commandType, List<Command> commands) {
		boolean isDashboardCommandsEnabled = Preferences.getInstance().getBoolean(DASHBOARD_COMMANDS_ENABLED, false);
		
		if (!isDashboardCommandsEnabled) {
			return;
		}

		for (Command command : commands) {
			SmartDashboard.putData(command);
		}
	}
	
	public String getAutonomousMode() {
		return autonomousChooser.getSelected();
	}

	public Command getAutonomousCommand() {
		
		String name = getAutonomousMode();
		
		for (Command command : autoCommands) {
			if (command.getName().equals(name)) {
				return command;
			}
		}
		
		return null;
	}

	public String getMainControllerMode() {
		String selected = controllerChooser.getSelected();
		
		SmartDashboard.putString("Configuration : joystick mode",  selected);
		
		return selected;
	}
	
	public int getMainControllerPort() {
		return Preferences.getInstance().getInt(PREF_MAIN_CONTROLLER_PORT, MAIN_CONTROLLER_PORT_DEFAULT);
	}
	
	public int getAuxPanelPort() {
		return Preferences.getInstance().getInt(PREF_AUX_PANEL_PORT, AUX_PANEL_PORT_DEFAULT);
	}
	
	public boolean isAuxPanelEnabled() {
		return Preferences.getInstance().getBoolean(PREF_AUX_PANEL_STATUS, false);
	}

	public static void printButtonActions() {
		new AttackThreeController().printButtonActions("Attack Three");
		new Xbox360Controller().printButtonActions("Xbox360");
		new AuxController().printButtonActions("Aux Panel");
	}

	public void printPreferences() {
		Preferences prefs = Preferences.getInstance();
		
		@SuppressWarnings("rawtypes")
		Vector keys = prefs.getKeys();
		
		System.out.println("******************* Prefernces *******************");
		
		for (Object obj : keys) {
			String key = null;
			
			// cast from object to String
			if (obj instanceof String) {
				key = (String) obj;
			}
			
			if (key != null) {
				String value = prefs.getString(key, "");
			
				System.out.println(key + " : " + value);
			}
		}
	}

	public Controller initializeMainController() {
		int mainControllerPort = getMainControllerPort();

		Controller mainController = new AttackThreeController(mainControllerPort);
		
		String selectedController = getMainControllerMode();

		if (selectedController.equals(Configuration.ATTACK_THREE)) {
			System.out.println("*************** ATTACK THREE ***************");
			
			if (mainController.isXbox()) {
				System.out.println("*************** WARNING !!! ***************");
				System.out.println("Dahboard choice is not an XBOX controller, but this is an XBOX CONTROLLER on port " + getMainControllerPort());
			}
		}
		else if (selectedController.equals(Configuration.XBOX360)) {
			System.out.println("*************** XBOX ***************");
			mainController = new Xbox360Controller(mainControllerPort);
			
			if (!mainController.isXbox()) {
				System.out.println("*************** WARNING !!! ***************");
				System.out.println("Dahboard choice is XBOX controller, but this is NOT an XBOX CONTROLLER on port " + getMainControllerPort());
			}
		} else if (selectedController.equals(Configuration.NO_CONTROLLER)) {
			System.out.println("*************** NO CONTROLLER ***************");
			mainController = new DefaultController(mainControllerPort);
		}
		
		return mainController;
	}

	public Controller initializeAuxController() {
		System.out.println("*************** AUX ***************");
		
		int auxControllerPort = getAuxPanelPort();

		Controller auxController = new AuxController(auxControllerPort);
		
		return auxController;
	}
	
	public static void main(String[] args) {
		Configuration.printButtonActions();
	}
}
