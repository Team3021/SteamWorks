package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox360Controller implements Controller {
	
	// Controller Buttons
	private static final int A_BUTTON = 1;
	private static final int B_BUTTON = 2;
	private static final int X_BUTTON = 3;
	private static final int Y_BUTTON = 4;
	private static final int LEFT_SHOULDER = 5;
	private static final int RIGHT_SHOULDER = 6;
	private static final int BACK_BUTTON = 7;
	private static final int START_BUTTON = 8;
	private static final int LEFT_STICK_CLICK = 9;
	private static final int RIGHT_STICK_CLICK = 10;
	
	// Controller axes
	private static final int LEFT_STICK_X = 0;
	private static final int LEFT_STICK_Y = 1;
	private static final int LEFT_TRIGGER = 2;
	private static final int RIGHT_TRIGGER = 3;
	private static final int RIGHT_STICK_X = 4;
	private static final int RIGHT_STICK_Y = 5;

	// Member Attributes
	Joystick controller;

	public Xbox360Controller(int port){
		controller = new Joystick(port);
	}

	@Override
	public double getMoveValue(){
		return controller.getRawAxis(LEFT_STICK_Y);
	}

	@Override
	public double getTurnValue(){
		return controller.getRawAxis(LEFT_STICK_X);
	}

	@Override
	public boolean isLaunching(){
		return getRawButton(LEFT_SHOULDER);
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return getRawButton(BACK_BUTTON);
	}
	
	@Override
	public boolean isCollecting() {
		return getRawButton(RIGHT_SHOULDER);
	}

	@Override
	public boolean isResettingNavx() {
		return getRawButton(START_BUTTON);
	}

	@Override
	public boolean isRotatingToNinety() {
		return getRawButton(B_BUTTON);
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		return getRawButton(X_BUTTON);
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		return getRawButton(A_BUTTON);
	}

	@Override
	public boolean isRotatingCustom() {
		if (controller.getRawAxis(RIGHT_TRIGGER) > 0.9) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isRotatingCustomNegative() {
		if (controller.getRawAxis(LEFT_TRIGGER) > 0.9) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isClimberSafteyOn() {
		return getRawButton(BACK_BUTTON);
	}

	@Override
	public boolean isClimbing() {
		return getRawButton(Y_BUTTON);
	}
	
	private boolean getRawButton(int button) {
		if (controller.getButtonCount() < button) {
			return false;
		}
		
		return controller.getRawButton(button);
	}

	@Override
	public boolean isXbox() {
		return controller.getIsXbox();
	}
}
