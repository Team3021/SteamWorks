package org.usfirst.frc.team3021.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3021.robot.commands.device.StartAgitator;
import org.usfirst.frc.team3021.robot.commands.device.StartClimber;
import org.usfirst.frc.team3021.robot.commands.device.StartCollector;
import org.usfirst.frc.team3021.robot.commands.device.StartIndexer;
import org.usfirst.frc.team3021.robot.commands.device.StartLaunchWheel;
import org.usfirst.frc.team3021.robot.commands.device.StopAgitator;
import org.usfirst.frc.team3021.robot.commands.device.StopClimber;
import org.usfirst.frc.team3021.robot.commands.device.StopCollector;
import org.usfirst.frc.team3021.robot.commands.device.StopIndexer;
import org.usfirst.frc.team3021.robot.commands.device.StopLaunchWheel;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.MoveBackwardForTime;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForDistance;
import org.usfirst.frc.team3021.robot.commands.driving.MoveForwardForTime;
import org.usfirst.frc.team3021.robot.commands.driving.ResetEncoders;
import org.usfirst.frc.team3021.robot.commands.driving.ResetGyro;
import org.usfirst.frc.team3021.robot.commands.driving.StopMoving;
import org.usfirst.frc.team3021.robot.commands.driving.StopTurning;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngle180;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleLeft45;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleLeft90;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleRight45;
import org.usfirst.frc.team3021.robot.commands.driving.TurnToAngleRight90;
import org.usfirst.frc.team3021.robot.commands.test.ClimberTest;
import org.usfirst.frc.team3021.robot.commands.test.CollectorTest;
import org.usfirst.frc.team3021.robot.commands.test.DriveTest;
import org.usfirst.frc.team3021.robot.commands.test.LauncherTest;
import org.usfirst.frc.team3021.robot.commands.test.SubsystemTest;
import org.usfirst.frc.team3021.robot.commands.test.VisionTest;
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

	public Configuration() {
		
		addAutonmousChoices();
		
		addControllerChoices();
	}

	private void addAutonmousChoices() {
		autonomousChooser.addDefault("Autonmous : " + AUTONOMOUS_DEFALUT, AUTONOMOUS_DEFALUT);
		SmartDashboard.putData("Auto choices", autonomousChooser);
	}

	private void addControllerChoices() {
		controllerChooser.addDefault(ATTACK_THREE, ATTACK_THREE );
		controllerChooser.addObject(XBOX360, XBOX360);
		SmartDashboard.putData("Select Controller", controllerChooser);
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
		System.out.println("******************* Subsystems *******************");

		for (Subsystem subsystem : subsystems) {
			SmartDashboard.putData(subsystem);
			
			System.out.println("Subsystem : " + subsystem.getName());
		}
	}
	
	public void addCommandsToDashboard() {
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
		moveCommands.add(new MoveForwardForDistance());
		moveCommands.add(new MoveForwardForTime());
		moveCommands.add(new MoveBackwardForDistance());
		moveCommands.add(new MoveBackwardForTime());
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
	}

	private void addCommandsToSmartDashboard(String commandType, List<Command> commands) {
		System.out.println("******************* " + commandType + " commands *******************");

		for (Command command : commands) {
			SmartDashboard.putData(command);
			
			System.out.println("Command : " + command.getName());
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
