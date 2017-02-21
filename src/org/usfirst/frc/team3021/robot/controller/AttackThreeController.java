package org.usfirst.frc.team3021.robot.controller;

import edu.wpi.first.wpilibj.Joystick;

public class AttackThreeController extends BaseController {
	
	public AttackThreeController() {
		buttonActions.add(new ButtonAction(1, "STICK_TRIGGER", "isLaunching"));
		buttonActions.add(new ButtonAction(3, "STICK_CENTER", "isCollecting"));
	
		buttonActions.add(new ButtonAction(4, "STICK_LEFT_BUTTON", "isRotatingToNegativeNinety"));
		buttonActions.add(new ButtonAction(2, "STICK_MIDDLE_BUTTON", "isRotatingToOneHundredEighty"));
		buttonActions.add(new ButtonAction(5, "STICK_RIGHT_BUTTON", "isRotatingToNinety"));
	
		buttonActions.add(new ButtonAction(10, "BASE_BACK_RIGHT", "isRotatingRight45"));
		buttonActions.add(new ButtonAction(7, "BASE_BACK_LEFT", "isRotatingLeft45"));
	
		buttonActions.add(new ButtonAction(11, "BASE_FRONT_RIGHT", "isClimbing"));
		buttonActions.add(new ButtonAction(6, "BASE_FRONT_LEFT", "isClimberSafteyOn"));
	
		buttonActions.add(new ButtonAction(8, "BASE_BOTTOM_LEFT", "isSwitchingCamera"));
		buttonActions.add(new ButtonAction(9, "BASE_BOTTOM_RIGHT", "isResettingNavx"));
	}
	
	public AttackThreeController(int port) {
		this();
		
		controller = new Joystick(port);
	}
	
	@Override
	public double getMoveValue() {
		return controller.getY();
	}
	
	@Override
	public double getTurnValue() {
		return controller.getX();
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

