package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class AuxController implements Controller {

	private static final int RIGHT_TOGGLE_BUTTON = 1;
	private static final int MIDDLE_TOGGLE_BUTTON = 2;
	private static final int LEFT_TOGGLE_BUTTON = 3;
	
	private static final int SAFETY_TRIGGER = 4;

	private static final int TOP_BLUE_BUTTON = 5;
	private static final int MIDDLE_BLUE_BUTTON = 6;
	private static final int BOTTOM_BLUE_BUTTON = 7;

	private static final int TOP_RED_BUTTON = 8;
	private static final int MIDDLE_RED_BUTTON = 9;
	private static final int BOTTOM_RED_BUTTON = 10;
	
	Joystick controller;
	
	public AuxController(int port) {
		controller = new Joystick(port);
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
		return getRawButton(6);
	}
	
	@Override
	public boolean isSwitchingCamera() {
		return false;
	}

	@Override
	public boolean isCollecting() {
		return getRawButton(7);
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
	public boolean isRotatingCustom() {
		return false;
	}

	@Override
	public boolean isRotatingCustomNegative() {
		return false;
	}

	@Override
	public boolean isClimberSafteyOn() {
		return getRawButton(SAFETY_TRIGGER);
	}

	@Override
	public boolean isClimbing() {
		return getRawButton(TOP_BLUE_BUTTON);
	}
	
	
	private boolean getRawButton(int button) {
		if (controller.getButtonCount() < button) {
			return false;
		}
		
		return controller.getRawButton(button);
	}

	@Override
	public boolean isXbox() {
		return false;
	}

}