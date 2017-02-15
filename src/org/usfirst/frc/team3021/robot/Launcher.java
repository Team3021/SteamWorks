package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher {
	private CANTalon launchWheel;
	private CANTalon intake;
	private CANTalon indexer;
	private Controller controller;
	private Relay agitator;
	
	private double launcherVoltage;
	private double intakeVoltage;
	private double indexerVoltage;

	private int ticksBetweenShots;  // The time between each shot
	private int indexerTickDuration;  // The amount of ticks that the indexer remains on for
	private int firstShotTicks;     // The amount of ticks until the first shot is fired
	private int sinceLastShotCount;      // Counts up until the next shot
	private int indexTickCount;          // Counts up until the indexer is turned off
	private boolean isFiring;       // Whether or not the indexer is on
	private boolean firstShotFired; // Whether or not the first shot has been fired
	
	public Launcher() {		
		launchWheel = new CANTalon(21);
		intake = new CANTalon(26);
		indexer = new CANTalon(27);
		agitator = new Relay(0);
		// Puts options on the dashboard to choose the voltage of the launch wheel, ball collector, and indexer.
		SmartDashboard.putNumber("Launcher Voltage", 0.55);
		SmartDashboard.putNumber("Ball Collector Voltage", 0.6);
		SmartDashboard.putNumber("Indexer Voltage", 0.2);
		SmartDashboard.putNumber("Cycles Between Launches", 90);
		SmartDashboard.putNumber("Cycles per Indexer Pulse", 1);
		SmartDashboard.putNumber("Cycles Before First Shot", 100);
		
		sinceLastShotCount = 0;
		indexTickCount = 0;
		isFiring = false;
		firstShotFired = false;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void teleopPeriodic() {
		launcherVoltage = SmartDashboard.getNumber("Launcher Voltage", 0.55);
		intakeVoltage = SmartDashboard.getNumber("Ball Collector Voltage", 0.6) * -1;
		indexerVoltage = SmartDashboard.getNumber("Indexer Voltage", 0.2);
		
		ticksBetweenShots = (int) SmartDashboard.getNumber("Cycles Between Launches", 90.0);
		indexerTickDuration = (int) SmartDashboard.getNumber("Cycles Per Indexer Pulse", 1);
		firstShotTicks = (int) SmartDashboard.getNumber("Cycles Before First Shot", 100);
		
		// Control for the launch wheel.
		// There are two states: when the indexer is on (firing) and when the indexer is off (not firing).
		// If a shot is not being fired (e.g. start of loop), sinceLastShot counts up until it reaches ticksBetweenShots.
		// Next, the indexer turns on and pulseTime counts up until it reaches indexerTickDuration.
		// Afterward, the cycle then repeats.
		if(controller.isSpinnerForward()) {
			
			launchWheel.set(launcherVoltage);
			agitator.set(Relay.Value.kReverse);
			
			if (firstShotFired == false && sinceLastShotCount >= firstShotTicks && isFiring == false) {
				// When a shot is not already being fired, the time to fire has been reached, and the first shot has not been fired.
				// A different delay is used for the first shot so that the motor has enough time to reach optimal speed.
				indexer.set(indexerVoltage);
				Timer.delay(0.010);
				indexer.set(0);
				isFiring = true;
				firstShotFired = true;
				sinceLastShotCount = 0;
			}
			else if (firstShotFired == true && sinceLastShotCount >= ticksBetweenShots && isFiring == false) {
				// If a shot is not already being fired, the time to fire has been reached, and the first shot has been fired.
				indexer.set(indexerVoltage);
				Timer.delay(0.010);
				indexer.set(0);
				isFiring = true;
				sinceLastShotCount = 0;
			}
			else if (isFiring == true && indexTickCount >= indexerTickDuration) {
				// If a shot is being fired and the indexer's on interval has ended.
				indexer.set(0);
				
				isFiring = false;
				indexTickCount = 0;
			}
			else  if (isFiring == false){
				// If a shot is not being fired, counts up to the next shot.
				sinceLastShotCount += 1;
			}
			else if (isFiring == true) {
				// If a shot is being fired, counts up until the indexer turns off.
				indexTickCount += 1;
			}
		}
		else {
			launchWheel.set(0);
			indexer.set(0);
			agitator.set(Relay.Value.kOff);
		}

		// Control for the feeder
		if(controller.isFeederForward()) {
			intake.set(intakeVoltage);
		}
		else {
			intake.set(0);
		}
		
		// Not to be confused with the variable storing the selection from the dashboard
		double launcherBusVoltage = launchWheel.getBusVoltage() - DriverStation.getInstance().getBatteryVoltage();
		SmartDashboard.putNumber("Voltage usage by Launch Wheel", launcherBusVoltage);
	}
}
