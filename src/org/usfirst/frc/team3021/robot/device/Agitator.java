package org.usfirst.frc.team3021.robot.device;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;

public class Agitator extends RunnableDevice {
	
	private static final String PREF_TIME_BEFORE_FIRST_PERIODIC = "Agitator.period.first.time.millis";
	private static final long DEFAULT_TIME_BEFORE_FIRST_PERIODIC = 200;
	
	private static final String PREF_TIME_BETWEEN_PERIODIC = "Agitator.periodic.time.millis";
	private static final long DEFAULT_TIME_BETWEEN_PERIODIC = 200;
	
	private static final String PREF_TIME_FOR_MOTOR = "Agitator.motor.time.millis";
	private static final long DEFAULT_TIME_FOR_MOTOR = 800;
	
	private Relay relay;

	public Agitator(int port) {
		super();
		
		relay = new Relay(port);
	}

	@Override
	protected void runPeriodic() {
		// delay the first periodic
		if (isFirstPeriodic()) {
			long pulseTime = Preferences.getInstance().getLong(PREF_TIME_BEFORE_FIRST_PERIODIC, DEFAULT_TIME_BEFORE_FIRST_PERIODIC);
			RunnableDevice.delay(pulseTime);
		}
		
		// start the motor
		relay.set(Relay.Value.kReverse);

		// run the motor for a  time
		long motorRunTime = Preferences.getInstance().getLong(PREF_TIME_FOR_MOTOR, DEFAULT_TIME_FOR_MOTOR);
		RunnableDevice.delay(motorRunTime);

		// stop the motor
		relay.set(Relay.Value.kOff);
		
		// wait before next periodic
		long timeBetweenPeriodics = Preferences.getInstance().getLong(PREF_TIME_BETWEEN_PERIODIC, DEFAULT_TIME_BETWEEN_PERIODIC);
		RunnableDevice.delay(timeBetweenPeriodics);
	}
}
