package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.device.Agitate;
import org.usfirst.frc.team3021.robot.commands.device.Climb;
import org.usfirst.frc.team3021.robot.commands.device.Collect;
import org.usfirst.frc.team3021.robot.commands.device.Index;
import org.usfirst.frc.team3021.robot.commands.device.SpinWheel;
import org.usfirst.frc.team3021.robot.commands.driving.DriveForTime;
import org.usfirst.frc.team3021.robot.commands.driving.DriveToDistance;
import org.usfirst.frc.team3021.robot.commands.driving.ResetEncoders;
import org.usfirst.frc.team3021.robot.commands.driving.ResetGyro;
import org.usfirst.frc.team3021.robot.commands.driving.StopDriving;
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
import edu.wpi.first.wpilibj.command.Scheduler;
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
		SmartDashboard.putData(Stanley.robotDrive);
		SmartDashboard.putData(Stanley.collector);
		SmartDashboard.putData(Stanley.climber);
		SmartDashboard.putData(Stanley.launcher);
		SmartDashboard.putData(Stanley.vision);
	}
	
	public void addCommandsToDashboard() {
		SmartDashboard.putData(Scheduler.getInstance());
		
		// Add test commands to dashboard
		SmartDashboard.putData(new SubsystemTest());
		SmartDashboard.putData(new DriveTest());
		SmartDashboard.putData(new CollectorTest());
		SmartDashboard.putData(new ClimberTest());
		SmartDashboard.putData(new LauncherTest());
		SmartDashboard.putData(new VisionTest());
		
		// Add drive commands to dashboard
		SmartDashboard.putData(new StopDriving());
		SmartDashboard.putData(new DriveToDistance(0.3, 3));
		SmartDashboard.putData(new DriveForTime(0.3, 3));
		SmartDashboard.putData(new TurnToAngleRight45());
		SmartDashboard.putData(new TurnToAngleRight90());
		SmartDashboard.putData(new TurnToAngleLeft45());
		SmartDashboard.putData(new TurnToAngleLeft90());
		SmartDashboard.putData(new TurnToAngle180());
		SmartDashboard.putData(new ResetGyro());
		SmartDashboard.putData(new ResetEncoders());
		
		// Add device commands to dashboard
		SmartDashboard.putData(new Agitate(5.0));
		SmartDashboard.putData(new Index(5.0));
		SmartDashboard.putData(new SpinWheel(5.0));
		SmartDashboard.putData(new Collect(5.0));
		SmartDashboard.putData(new Climb(5.0));
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
