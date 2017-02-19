package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class AuxController implements Controller {
	
	Joystick joystick;
	
	public AuxController(int port) {
		joystick = new Joystick(port);
	}
	
	@Override
	public double getMoveValue() {
		return 0;
	}
	
	@Override
	public double getTurnValue() {
		return 0;
	}
	
	@Override
	public boolean isLaunching() {
		return joystick.getRawButton(6);
	}
	
	@Override
	public boolean isSwitchingCamera() {
		return false;
	}

	@Override
	public boolean isCollecting() {
		return joystick.getRawButton(7);
	}

	@Override
	public boolean isResettingNavx() {
		return false;
	}

	@Override
	public boolean isRotateToZero() {
		return false;
	}

	@Override
	public boolean isRotatingToNinety() {
		return false;
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		return false;
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		return false;
	}

	@Override
	public boolean isRotatingCustom() {
		return false;
	}

	@Override
	public boolean isRotatingCustomNegative() {
		return false;
	}
}