package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.commands.ClimberCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	
	private static final String PREF_VOLTAGE = "Climber.motor.voltage";
	private static final double DEFAULT_VOLTAGE = 1.0;
	
	private CANTalon talon;
	
	public Climber() {		
		talon = new CANTalon(30);
		
		talon.setCurrentLimit(100);
	}
	
	@Override
	public void teleopPeriodic() {
		// Control the motor
		if ((mainController.isClimberSafteyOn() && mainController.isClimbing())
				|| (auxController.isClimberSafteyOn() && auxController.isClimbing())) {
			startMotor();
		}
		else {
			stopMotor();
		}
		
		SmartDashboard.putNumber("Climber.talon.voltage", talon.getBusVoltage());
	}

	public void startMotor() {
		talon.set(getVoltage());
	}

	public void stopMotor() {
		talon.set(0);
	}
	
	private double getVoltage() {
		double voltage = Preferences.getInstance().getDouble(PREF_VOLTAGE, DEFAULT_VOLTAGE);
		
		// reverse the polarity
		voltage = voltage * -1;
		
		return voltage;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberCommand());
	}	
}
