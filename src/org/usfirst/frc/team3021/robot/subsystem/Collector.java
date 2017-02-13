package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.SubSystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends SubSystem {
	
	private static final String VOLTAGE = Collector.class.getSimpleName() + " : Voltage";
	private static final double DEFAULT_VOLTAGE = 0.55;
	
	private CANTalon talon;
	
	public Collector() {		
		talon = new CANTalon(26);
		
		SmartDashboard.putNumber(VOLTAGE, DEFAULT_VOLTAGE);
	}
	
	@Override
	public void teleopPeriodic() {
		// Control the motor
		if (controller.isCollecting()) {
			talon.set(getVoltage());
		}
		else {
			talon.set(0);
		}

		displayActualVoltage();
	}

	@Override
	public void testPeriodic() {
		// turn on the motor
		talon.set(getVoltage());
		
		// run the motor for a time
		Timer.delay(30);
		
		// turn off the motor
		talon.set(0);
	}
	
	private double getVoltage() {
		double voltage = SmartDashboard.getNumber(VOLTAGE, DEFAULT_VOLTAGE);
		
		// reverse the polarity
		voltage = voltage * -1;
		
		return voltage;
	}

	private void displayActualVoltage() {
		double actualVoltage = talon.getBusVoltage() - DriverStation.getInstance().getBatteryVoltage();
		SmartDashboard.putNumber(Collector.class.getSimpleName() + " : Voltage Reading", actualVoltage);
	}
}
