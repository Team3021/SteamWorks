package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.SubSystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends SubSystem {
	
	private static final String VOLTAGE = Collector.class.getSimpleName() + " : Voltage";
	private static final double DEFAULT_VOLTAGE = 0.55;
	
	private CANTalon talon;
	
	private double voltage;
	
	public Collector() {		
		talon = new CANTalon(26);
		
		SmartDashboard.putNumber(VOLTAGE, DEFAULT_VOLTAGE);
	}
	
	@Override
	public void teleopPeriodic() {
		voltage = SmartDashboard.getNumber(VOLTAGE, DEFAULT_VOLTAGE);
		
		// reverse the polarity
		voltage = voltage * -1;
		
		// Control the motor
		if (controller.isCollecting()) {
			talon.set(voltage);
		}
		else {
			talon.set(0);
		}

		double actualVoltage = talon.getBusVoltage() - DriverStation.getInstance().getBatteryVoltage();
		SmartDashboard.putNumber(Collector.class.getSimpleName() + " : Voltage Reading", actualVoltage);
	}
}
