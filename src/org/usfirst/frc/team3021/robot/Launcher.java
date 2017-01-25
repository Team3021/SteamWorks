package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;

public class Launcher {
	CANTalon launchWheel;
	ThrustMasterController joystick;
	
	public Launcher() {
		launchWheel = new CANTalon(21);
		joystick = new ThrustMasterController(0);
	}
	
	public void teleopPeriodic() {
		if(joystick.isHighGear()) {
			launchWheel.set(0.55);
		}
		else {
			launchWheel.set(0);
		}
	}
}
