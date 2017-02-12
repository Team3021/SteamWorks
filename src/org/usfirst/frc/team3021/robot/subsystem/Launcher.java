package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.SubSystem;
import org.usfirst.frc.team3021.robot.device.Agitator;
import org.usfirst.frc.team3021.robot.device.Indexer;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher extends SubSystem {
	
	private static final String VOLTAGE = Launcher.class.getSimpleName() + " : Voltage";
	private static final double DEFAULT_VOLTAGE = 0.55;
	
	private CANTalon launchWheel;
	private Indexer indexer;
	private Agitator agitator;
	
	public Launcher() {		
		launchWheel = new CANTalon(21);
		indexer = new Indexer(27);
		agitator = new Agitator(0);
		
		// Puts options on the dashboard to choose the voltage of the launch wheel, ball collector, and indexer.
		SmartDashboard.putNumber(VOLTAGE, DEFAULT_VOLTAGE);
	}
	
	public void teleopPeriodic() {
		double voltage = SmartDashboard.getNumber(VOLTAGE, DEFAULT_VOLTAGE);
		
		// Control for the launch wheel.
		if (controller.isLaunching()) {
			launchWheel.set(voltage);
			agitator.play();
			indexer.play();
		}
		else {
			indexer.pause();
			agitator.pause();
			launchWheel.set(0);
		}

		double actualVoltage = launchWheel.getBusVoltage() - DriverStation.getInstance().getBatteryVoltage();
		SmartDashboard.putNumber(Launcher.class.getSimpleName() + " : Voltage Reading", actualVoltage);
	}
}
