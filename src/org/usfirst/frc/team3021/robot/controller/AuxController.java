package org.usfirst.frc.team3021.robot.controller;

import edu.wpi.first.wpilibj.Joystick;

public class AuxController extends BaseController {
	
	Joystick controller;
	
	public AuxController() {
		buttonActions.add(new ButtonAction(1, "RIGHT_TOGGLE_BUTTON", "unassigned"));
		buttonActions.add(new ButtonAction(2, "MIDDLE_TOGGLE_BUTTON", "unassigned"));
		buttonActions.add(new ButtonAction(3, "LEFT_TOGGLE_BUTTON", "unassigned"));
	
		buttonActions.add(new ButtonAction(4, "SAFETY_TRIGGER", "isClimberSafteyOn"));
		
		buttonActions.add(new ButtonAction(5, "TOP_BLUE_BUTTON", "isClimbing"));
		buttonActions.add(new ButtonAction(6, "MIDDLE_BLUE_BUTTON", "isLaunching"));
		buttonActions.add(new ButtonAction(7, "BOTTOM_BLUE_BUTTON", "isCollecting"));
	
		buttonActions.add(new ButtonAction(8, "TOP_RED_BUTTON", "unassigned"));
		buttonActions.add(new ButtonAction(9, "MIDDLE_RED_BUTTON", "unassigned"));
		buttonActions.add(new ButtonAction(10, "BOTTOM_RED_BUTTON", "unassigned"));
	}
	
	public AuxController(int port) {
		this();
		
		controller = new Joystick(port);
	}
	
	@Override
	public boolean isLaunching() {
		return getRawButton("isLaunching");
	}
	
	@Override
	public boolean isSwitchingCamera() {
		return getRawButton("isSwitchingCamera");
	}

	@Override
	public boolean isCollecting() {
		return getRawButton("isCollecting");
	}

	@Override
	public boolean isResettingNavx() {
		return getRawButton("isResettingNavx");
	}

	@Override
	public boolean isRotatingToNinety() {
		return getRawButton("isRotatingToNinety");
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		return getRawButton("isRotatingToNegativeNinety");
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		return getRawButton("isRotatingToOneHundredEighty");
	}

	@Override
	public boolean isRotatingRight45() {
		return getRawButton("isRotatingRight45");
	}

	@Override
	public boolean isRotatingLeft45() {
		return getRawButton("isRotatingLeft45");
	}

	@Override
	public boolean isClimberSafteyOn() {
		return getRawButton("isClimberSafteyOn");
	}

	@Override
	public boolean isClimbing() {
		return getRawButton("isClimbing");
	}
}