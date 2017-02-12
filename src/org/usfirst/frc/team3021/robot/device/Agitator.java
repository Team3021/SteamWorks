package org.usfirst.frc.team3021.robot.device;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Agitator extends RunnableDevice {
	
	private static final String TIME_BEFORE_FIRST_PERIODIC = Agitator.class.getSimpleName() + " : Time Before First Periodic";
	private static final double DEFAULT_TIME_BEFORE_FIRST_PERIODIC = 200;
	
	private static final String TIME_BETWEEN_PERIODIC = Agitator.class.getSimpleName() + " : Time Between Periodic";
	private static final double DEFAULT_TIME_BETWEEN_PERIODIC = 200;
	
	private static final String TIME_FOR_MOTOR = Agitator.class.getSimpleName() + " : Time For Motor";
	private static final double DEFAULT_TIME_FOR_MOTOR = 800;
	
	private Relay relay;

	public Agitator(int port) {
		super();
		
		relay = new Relay(port);
		
		SmartDashboard.putNumber(TIME_BETWEEN_PERIODIC, DEFAULT_TIME_BETWEEN_PERIODIC);
		SmartDashboard.putNumber(TIME_FOR_MOTOR, DEFAULT_TIME_FOR_MOTOR);
	}

	@Override
	protected void runPeriodic() {
		// delay the first periodic
		if (isFirstPeriodic()) {
			long pulseTime = (long) SmartDashboard.getNumber(TIME_BEFORE_FIRST_PERIODIC, DEFAULT_TIME_BEFORE_FIRST_PERIODIC);
			RunnableDevice.delay(pulseTime);
		}
		
		// start the motor
		relay.set(Relay.Value.kReverse);

		// run the motor for a  time
		long motorRunTime = (long) SmartDashboard.getNumber(TIME_FOR_MOTOR, DEFAULT_TIME_FOR_MOTOR);
		RunnableDevice.delay(motorRunTime);

		// stop the motor
		relay.set(Relay.Value.kOff);
		
		// wait before next periodic
		long timeBetweenPeriodics = (int) SmartDashboard.getNumber(TIME_BETWEEN_PERIODIC, DEFAULT_TIME_BETWEEN_PERIODIC);
		RunnableDevice.delay(timeBetweenPeriodics);
	}
}
