package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
import org.usfirst.frc.team3021.robot.commands.ClimberCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	
	private static final String VOLTAGE = "Climber : Voltage";
	private static final double DEFAULT_VOLTAGE = 1.0;
	
	private CANTalon talon;
	
	public Climber() {		
		talon = new CANTalon(30);
	}
	
	@Override
	public void teleopPeriodic() {
		// Control the motor
		if (mainController.isClimberSafteyOn() && mainController.isClimbing()) {
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
		double voltage = Preferences.getInstance().getDouble(VOLTAGE, DEFAULT_VOLTAGE);
		
		// reverse the polarity
		voltage = voltage * -1;
		
		return voltage;
	}

	private void displayActualVoltage() {
		double actualVoltage = talon.getBusVoltage() - DriverStation.getInstance().getBatteryVoltage();
		SmartDashboard.putNumber("Climber : Voltage Reading", actualVoltage);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberCommand());
	}	
}
