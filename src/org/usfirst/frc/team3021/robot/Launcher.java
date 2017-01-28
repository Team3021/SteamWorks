package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;

public class Launcher {
	CANTalon launchWheel;
	Controller controller;
	
	public Launcher() {		
		launchWheel = new CANTalon(21);
		
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
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
