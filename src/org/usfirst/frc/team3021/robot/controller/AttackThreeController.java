package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class AttackThreeController implements Controller {
	
	private static final int STICK_TRIGGER_1 = 1;
	private static final int STICK_MIDDLE_BUTTON_2 = 2;
	private static final int STICK_CENTER_3 = 3;
	private static final int STICK_LEFT_BUTTON_4 = 4;
	private static final int STICK_RIGHT_BUTTON_5 = 5;
	
	private static final int BASE_FRONT_RIGHT_11 = 11;
	private static final int BASE_BACK_RIGHT_10 = 10;
	
	private static final int BASE_FRONT_LEFT_6 = 6;
	private static final int BASE_BACK_LEFT_7 = 7;
	
	private static final int BASE_BOTTOM_LEFT_8 = 8;
	private static final int BASE_BOTTOM_RIGHT_9 = 9;
	
	
	// Member Attributes
	Joystick controller;
	
	public AttackThreeController(int port){
		controller = new Joystick(port);
	}
	
	@Override
	public double getMoveValue(){
		return controller.getY();
	}
	
	@Override
	public double getTurnValue(){
		return controller.getX();
	}
	
	@Override
	public boolean isLaunching(){
		return getRawButton(STICK_TRIGGER_1);
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return getRawButton(BASE_BOTTOM_LEFT_8);
	}

	@Override
	public boolean isCollecting() {
		return getRawButton(STICK_CENTER_3);
	}

	@Override
	public boolean isResettingNavx() {
		return getRawButton(BASE_BOTTOM_RIGHT_9);
	}

	@Override
	public boolean isRotatingToNinety() {
		return getRawButton(STICK_RIGHT_BUTTON_5);
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		return getRawButton(STICK_LEFT_BUTTON_4);
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		return getRawButton(STICK_MIDDLE_BUTTON_2);
	}

	@Override
	public boolean isRotatingCustom() {
		return getRawButton(BASE_BACK_RIGHT_10);
	}

	@Override
	public boolean isRotatingCustomNegative() {
		return getRawButton(BASE_BACK_LEFT_7);
	}

	@Override
	public boolean isClimberSafteyOn() {
		return getRawButton(BASE_FRONT_LEFT_6);
	}

	@Override
	public boolean isClimbing() {
		return getRawButton(BASE_FRONT_RIGHT_11);
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

