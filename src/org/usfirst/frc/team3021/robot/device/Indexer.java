package org.usfirst.frc.team3021.robot.device;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Preferences;

public class Indexer extends RunnableDevice {
	
	private static final String PREF_TIME_BEFORE_FIRST_PERIODIC = "Indexer.period.first.time.mills";
	private static final long DEFAULT_TIME_BEFORE_FIRST_PERIODIC = 1000;
	
	private static final String PREF_TIME_BETWEEN_PERIODIC = "Indexer.period.time.millis";
	private static final long DEFAULT_TIME_BETWEEN_PERIODIC = 100;
	
	private static final String PREF_TIME_FOR_MOTOR = "Indexer.period.motor.time.millis";
	private static final long DEFAULT_TIME_FOR_MOTOR = 30;
	
	private static final String PREF_VOLTAGE = "Indexer.motor.voltage";
	private static final double DEFAULT_VOLTAGE = 0.3;
	
	private CANTalon talon;
	
	public Indexer(int port) {
		super();
		
		talon = new CANTalon(port);
	}

	@Override
	protected void runPeriodic() {
		// delay the first periodic
		if (isFirstPeriodic()) {
			long pulseTime = Preferences.getInstance().getLong(PREF_TIME_BEFORE_FIRST_PERIODIC, DEFAULT_TIME_BEFORE_FIRST_PERIODIC);
			RunnableDevice.delay(pulseTime);
		}
		
		// start the motor
		double voltage = Preferences.getInstance().getDouble(PREF_VOLTAGE, DEFAULT_VOLTAGE);
		talon.set(voltage);

		// run the motor for a time
		long motorRunTime = Preferences.getInstance().getLong(PREF_TIME_FOR_MOTOR, DEFAULT_TIME_FOR_MOTOR);
		RunnableDevice.delay(motorRunTime);
		
		// stop the motor
		talon.set(0);
		
		// wait before next periodic
		long timeBetweenPeriodic = Preferences.getInstance().getLong(PREF_TIME_BETWEEN_PERIODIC, DEFAULT_TIME_BETWEEN_PERIODIC);
		RunnableDevice.delay(timeBetweenPeriodic);
	}
}
