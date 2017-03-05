package org.usfirst.frc.team3021.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3021.robot.commands.DriveCommand;
import org.usfirst.frc.team3021.robot.commands.auto.StartCenterToCenterGearDelivery;
import org.usfirst.frc.team3021.robot.commands.auto.StartLeftToLeftGearDelivery;
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
	
	public static final String AUTONOMOUS_DEFALUT = "Default";
	
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

	public Configuration() {
		
		addAutonmousChoices();
		
		addControllerChoices();
	}

	private void addAutonmousChoices() {
		autonomousChooser.addDefault("Autonmous : " + AUTONOMOUS_DEFALUT, AUTONOMOUS_DEFALUT);
		SmartDashboard.putData("Autonomous Mode", autonomousChooser);
	}

	private void addControllerChoices() {
		controllerChooser.addDefault(ATTACK_THREE, ATTACK_THREE );
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
		
		// Add test commands to dashboard
		testCommands.add(new SubsystemTest());
		testCommands.add(new DriveTest());
		testCommands.add(new CollectorTest());
		testCommands.add(new ClimberTest());
		testCommands.add(new LauncherTest());
		testCommands.add(new VisionTest());
		
		addCommandsToSmartDashboard("Test", testCommands);
		
		// Add move commands to dashboard
		moveCommands.add(new ResetEncoders());
		moveCommands.add(new MoveForwardForDistance(speed, distance));
		moveCommands.add(new MoveForwardForTime(speed, time));
		moveCommands.add(new MoveBackwardForDistance(speed, distance));
		moveCommands.add(new MoveBackwardForTime(speed, time));
		moveCommands.add(new StopMoving());
		
		addCommandsToSmartDashboard("Move", moveCommands);
		
		// add turning commands
		turnCommands.add(new ResetGyro());
		turnCommands.add(new TurnToAngleRight45());
		turnCommands.add(new TurnToAngleRight90());
		turnCommands.add(new TurnToAngleLeft45());
		turnCommands.add(new TurnToAngleLeft90());
		turnCommands.add(new TurnToAngle180());
		turnCommands.add(new StopTurning());
		
		addCommandsToSmartDashboard("Turn", turnCommands);
		
		// Add device commands to dashboard
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
		
		addCommandsToSmartDashboard("Device", deviceCommands);
		
		// Add autonomous commands to dashboard
		autoCommands.add(new StartLeftToLeftGearDelivery());
		autoCommands.add(new StartCenterToCenterGearDelivery());
		
		addCommandsToSmartDashboard("Autonomous", autoCommands);
	}

	private void addCommandsToSmartDashboard(String commandType, List<Command> commands) {

		for (Command command : commands) {
			SmartDashboard.putData(command);
			
		}
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

	public static void main(String[] args) {
		Configuration.printButtonActions();
	}
}
