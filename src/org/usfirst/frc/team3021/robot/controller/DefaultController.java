package org.usfirst.frc.team3021.robot.controller;

public class DefaultController extends BaseController {
	
	public DefaultController(int port) {
		// override to do nothing and not construct a Joystick
	}

	@Override
	public boolean isLaunching() {
		return false;
	}

	@Override
	public boolean isSwitchingCamera() {
		return false;
	}

	@Override
	public boolean isCollecting() {
		return false;
	}

	@Override
	public boolean isResettingNavx() {
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
	public boolean isRotatingRight45() {
		return false;
	}

	@Override
	public boolean isRotatingLeft45() {
		return false;
	}

	@Override
	public boolean isClimberSafteyOn() {
		return false;
	}

	@Override
	public boolean isClimbing() {
		return false;
	}

	@Override
	public boolean getRawButton(String action) {
		return false;
	}
}