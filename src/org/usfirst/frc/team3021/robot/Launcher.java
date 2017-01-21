package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;

public class Launcher {
	CANTalon launchWheel;
	Xbox360Controller joystick;
	
	public Launcher() {
		launchWheel = new CANTalon(21);
		joystick = new Xbox360Controller(0);
	}
	
	public void teleopPeriodic() {
		if(joystick.isSpinnerForward()) {
			launchWheel.set(0.55);
		}
		else {
			launchWheel.set(0);
		}
	}
}
