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
import org.usfirst.frc.team3021.robot.controller.Xbox360Controller;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Configuration {
	
	public static final String AUTONOMOUS_DEFALUT = "No Command";
	
	public static final String DEFAULT = "No Controller";
	public static final String ATTACK_THREE = "Attack Three";
	public static final String XBOX360 = "Xbox360";

	private final String PREF_MAIN_CONTROLLER_PORT = "Controller.main.port";
	private final int MAIN_CONTROLLER_PORT_DEFAULT = 0;

	public  final String PREF_AUX_PANEL_STATUS = "Controller.aux.enabled";
	private final String PREF_AUX_PANEL_PORT = "Controller.aux.port";
	private final int AUX_PANEL_PORT_DEFAULT = 1;
	
	private SendableChooser<String> autonomousChooser = new SendableChooser<>();
	private SendableChooser<String> controllerChooser = new SendableChooser<>();
	
	private List<Subsystem> subsystems = new ArrayList<Subsystem>();

	private List<Command> testCommands = new ArrayList<Command>();
	private List<Command> moveCommands = new ArrayList<Command>();
	private List<Command> turnCommands = new ArrayList<Command>();
	private List<Command> deviceCommands = new ArrayList<Command>();
	private List<Command> autoCommands = new ArrayList<Command>();

	public void addAutonmousChoices() {
		autonomousChooser.addDefault(AUTONOMOUS_DEFALUT, AUTONOMOUS_DEFALUT);
		
		for (Command command : autoCommands) {
			autonomousChooser.addObject(command.getName(), command.getName());
		}
		
		SmartDashboard.putData("Autonomous Mode", autonomousChooser);
	}

	public void addControllerChoices() {
		controllerChooser.addDefault(DEFAULT, DEFAULT);
		controllerChooser.addObject(ATTACK_THREE, ATTACK_THREE);
		controllerChooser.addObject(XBOX360, XBOX360);
		SmartDashboard.putData("Main Controller Mode", controllerChooser);
	}

	public void addSubsystemsToDashboard() {
		subsystems.add(Stanley.robotDrive);
		subsystems.add(Stanley.collector);
		subsystems.add(Stanley.climber);
		subsystems.add(Stanley.launcher);
		subsystems.add(Stanley.vision);
		
		addSubsystemsToSmartDashboard(subsystems);
	}

	private void addSubsystemsToSmartDashboard(List<Subsystem> subsystems) {
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
		
		autoCommands.add(new StartLeftToLeftGearDelivery());
		autoCommands.add(new StartCenterToCenterGearDelivery());
		autoCommands.add(new StartRightToRightGearDelivery());

		// ****************************************************************************
		// **********************        MOVE COMMANDS           **********************
		// ****************************************************************************
		
		moveCommands.add(new ResetEncoders());
		moveCommands.add(new MoveForwardForDistance(speed, distance));
		moveCommands.add(new MoveForwardForTime(speed, time));
		moveCommands.add(new MoveBackwardForDistance(speed, distance));
		moveCommands.add(new MoveBackwardForTime(speed, time));
		moveCommands.add(new StopMoving());
		
		// ****************************************************************************
		// **********************        TURN COMMANDS           **********************
		// ****************************************************************************
		
		turnCommands.add(new ResetGyro());
		turnCommands.add(new TurnRightToAngle45());
		turnCommands.add(new TurnRightToAngle90());
		turnCommands.add(new TurnLeftToAngle45());
		turnCommands.add(new TurnLeftToAngle90());
		turnCommands.add(new TurnToAngle180());
		turnCommands.add(new StopTurning());
		turnCommands.add(new TurnToTarget());
		
		// ****************************************************************************
		// **********************       DEVICE COMMANDS          **********************
		// ****************************************************************************
		
		deviceCommands.add(new StartAgitator());
		deviceCommands.add(new StartIndexer());
		deviceCommands.add(new StartLaunchWheel());
		deviceCommands.add(new StartCollector());
		deviceCommands.add(new StartClimber());
		
		deviceCommands.add(new StopAgitator());
		deviceCommands.add(new StopIndexer());
		deviceCommands.add(new StopLaunchWheel());
		deviceCommands.add(new StopCollector());
		deviceCommands.add(new StopClimber());

		// ****************************************************************************
		// **********************        TEST COMMANDS           **********************
		// ****************************************************************************
		
		testCommands.add(new SubsystemTest());
		testCommands.add(new CollectorTest());
		testCommands.add(new ClimberTest());
		testCommands.add(new LauncherTest());
		testCommands.add(new VisionTest());
		testCommands.add(new MoveForwardForDistanceTest());
		testCommands.add(new MoveBackwardForDistanceTest());
		testCommands.add(new MoveForwardForTimeTest());
		testCommands.add(new MoveBackwardForTimeTest());

		// Add commands to dashboard
		addCommandsToSmartDashboard("Autonomous", autoCommands);
		addCommandsToSmartDashboard("Move", moveCommands);
		addCommandsToSmartDashboard("Turn", turnCommands);
		addCommandsToSmartDashboard("Device", deviceCommands);
		addCommandsToSmartDashboard("Test", testCommands);
	}

	private void addCommandsToSmartDashboard(String commandType, List<Command> commands) {

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
	
	public static void main(String[] args) {
		Configuration.printButtonActions();
	}
}
