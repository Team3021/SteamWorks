package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;

public class Launcher {
	CANTalon launchWheel;
	CANTalon intake;
	CANTalon indexer;
	Controller controller;
	
	public Launcher() {		
		launchWheel = new CANTalon(21);
		intake = new CANTalon(26);
		indexer = new CANTalon(27);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void teleopPeriodic() {
		// Control for the launch wheel.
		if(controller.isSpinnerForward()) {
			launchWheel.set(0.55);
			indexer.set(0.3);
		}
		else {
			launchWheel.set(0);
			indexer.set(0);
		}

		// Control for the feeder
		if(controller.isFeederForward()) {
			intake.set(-0.6);
		}
		else {
			intake.set(0);
		}
		
		System.out.println(launchWheel.getBusVoltage());
		
	}
}
