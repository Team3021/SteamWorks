package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class ThrustmasterController implements Controller {
	
	private static final int TRIGGER = 1; // The middle trigger on the back of the stick
	private static final int SHOULDER = 3; // The right shoulder button on the back of the stick
	
	private static final int FRONT_MIDDLE_BUTTON = 2;// The striped middle button on the front of the stick
	private static final int FRONT_LEFT_BUTTON = 4;

	private static final int FRONT_RIGHT_BUTTON = 5; // The right button on the front of the stick
	private static final int FRONT_BOTTOM_RIGHT = 9;
	
	// Member Attributes
	Joystick controller;
	
	public ThrustmasterController(int port){
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
		return getRawButton(SHOULDER);
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return getRawButton(FRONT_RIGHT_BUTTON);
	}

	@Override
	public boolean isCollecting() {
		return getRawButton(FRONT_MIDDLE_BUTTON);
	}

	@Override
	public boolean isResettingNavx() {
		return getRawButton(TRIGGER);
	}

	@Override
	public boolean isRotatingToNinety() {
		return getRawButton(FRONT_RIGHT_BUTTON);
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		return getRawButton(FRONT_LEFT_BUTTON);
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		return getRawButton(FRONT_MIDDLE_BUTTON);
	}

	@Override
	public boolean isRotatingCustom() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRotatingCustomNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClimberSafteyOn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClimbing() {
		return getRawButton(FRONT_BOTTOM_RIGHT);
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

