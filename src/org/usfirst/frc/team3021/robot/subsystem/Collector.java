package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends Subsystem {
	
	private static final String VOLTAGE = "Collector : Voltage";
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
			startMotor();
		}
		else {
			stopMotor();
		}

		displayActualVoltage();
	}

	public void startMotor() {
		talon.set(getVoltage());
	}

	public void stopMotor() {
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
		SmartDashboard.putNumber("Collector : Voltage Reading", actualVoltage);
	}
}