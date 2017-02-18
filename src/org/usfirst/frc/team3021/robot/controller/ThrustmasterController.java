package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class ThrustmasterController implements Controller {
	private static final int TRIGGER = 1; // The middle trigger on the back of the stick
	private static final int SHOULDER = 3; // The right shoulder button on the back of the stick
	private static final int FRONT_MIDDLE_BUTTON = 2;// The striped middle button on the front of the stick
	private static final int FRONT_LEFT_BUTTON = 4;
	private static final int FRONT_RIGHT_BUTTON = 5; // The right button on the front of the stick
	private static final int FRONT_BOTTOM_RIGHT = 9; // The button with the number 8 on it
	
	// Member Attributes
	Joystick JS;
	
	public ThrustmasterController(int port){
		JS = new Joystick(port);
	}
	
	@Override
	public double getMoveValue(){
		return JS.getY();
	}
	
	@Override
	public double getTurnValue(){
		return JS.getX();
	}
	
	@Override
	public boolean isLaunching(){
		return false;
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return JS.getRawButton(FRONT_BOTTOM_RIGHT);
	}

	@Override
	public boolean isCollecting() {
		return false;
	}

	@Override
	public boolean isResettingNavx() {
		// TODO Auto-generated method stub
		return JS.getRawButton(TRIGGER);
	}

	@Override
	public boolean isRotateToZero() {
		// TODO Auto-generated method stub
		return JS.getRawButton(SHOULDER);
	}

	@Override
	public boolean isRotatingToNinety() {
		// TODO Auto-generated method stub
		return JS.getRawButton(FRONT_RIGHT_BUTTON);
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		// TODO Auto-generated method stub
		return JS.getRawButton(FRONT_LEFT_BUTTON);
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		// TODO Auto-generated method stub
		return JS.getRawButton(FRONT_MIDDLE_BUTTON);
	}
}

