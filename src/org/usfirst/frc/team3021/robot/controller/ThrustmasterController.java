package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class ThrustmasterController implements Controller {
	private static final int TRIGGER = 1; // The middle trigger on the back of the stick
	private static final int FRONT_RIGHT_BUTTON = 4; // The right button on the front of the stick
	private static final int SHOULDER = 3; // The right shoulder button on the back of the stick
	private static final int FRONT_MIDDLE_BUTTON = 2; // The striped middle button on the front of the stick
	
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
		return JS.getRawButton(SHOULDER);
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return JS.getRawButton(FRONT_RIGHT_BUTTON);
	}

	@Override
	public boolean isCollecting() {
		return JS.getRawButton(FRONT_MIDDLE_BUTTON);
	}
}

