package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.commands.LauncherCommand;
import org.usfirst.frc.team3021.robot.device.Agitator;
import org.usfirst.frc.team3021.robot.device.Indexer;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Preferences;

public class Launcher extends Subsystem {
	
	private static final String PREF_VOLTAGE = "Launcher.motor.voltage";
	private static final double DEFAULT_VOLTAGE = 0.45;
	
	private CANTalon launchWheel;
	private Indexer indexer;
	private Agitator agitator;
	
	public Launcher() {		
		launchWheel = new CANTalon(21);
		indexer = new Indexer(27);
		agitator = new Agitator(0);
	}
	
	public void startIndexer() {
		indexer.play();
	}
	
	public void stopIndexer() {
		indexer.pause();
	}
	
	public void startAgitator() {
		agitator.play();
	}
	
	public void stopAgitator() {
		agitator.pause();
	}
	
	public void startLaunchWheel() {
		launchWheel.set(getVoltage());
	}
	
	public void stopLaunchWheel() {
		launchWheel.set(0);
	}
	
	public void teleopPeriodic() {
		
		// Control for the launch wheel.
		if (mainController.isLaunching() || auxController.isLaunching()) {
			startLaunchWheel();
			startIndexer();
			startAgitator();
		}
		else {
			stopIndexer();
			stopAgitator();
			stopLaunchWheel();
		}
	}
	
	private double getVoltage() {
		double voltage = Preferences.getInstance().getDouble(PREF_VOLTAGE, DEFAULT_VOLTAGE);
		
		return voltage;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LauncherCommand());
	}

}
