package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;

public class Launcher {
	CANTalon launchWheel;
	Controller controller;
	
	public Launcher(Controller controller) {
		launchWheel = new CANTalon(21);
		controller = new ThrustMasterController(0);
	}
	
	public void teleopPeriodic() {
		if(controller.isSpinnerForward()) {
			launchWheel.set(0.55);
		}
		else {
			launchWheel.set(0);
		}
	}
}
