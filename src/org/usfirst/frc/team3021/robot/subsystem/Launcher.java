package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
import org.usfirst.frc.team3021.robot.commands.LauncherCommand;
import org.usfirst.frc.team3021.robot.device.Agitator;
import org.usfirst.frc.team3021.robot.device.Indexer;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher extends Subsystem {
	
	private static final String VOLTAGE = "Launcher : Voltage";
	private static final double DEFAULT_VOLTAGE = 0.55;
	
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
	
	public void startWheel() {
		launchWheel.set(getVoltage());
	}
	
	public void stopWheel() {
		launchWheel.set(0);
	}
	
	public void teleopPeriodic() {
		
		// Control for the launch wheel.
		if (controller.isLaunching()) {
			startWheel();
			startIndexer();
			startAgitator();
		}
		else {
			stopIndexer();
			stopAgitator();
			stopWheel();
		}

		displayActualVoltage();
	}
	
	private double getVoltage() {
		double voltage = Preferences.getInstance().getDouble(VOLTAGE, DEFAULT_VOLTAGE);
		
		return voltage;
	}

	private void displayActualVoltage() {
		double actualVoltage = launchWheel.getBusVoltage() - DriverStation.getInstance().getBatteryVoltage();
		SmartDashboard.putNumber("Launcher : Voltage Reading", actualVoltage);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LauncherCommand());
	}

}
