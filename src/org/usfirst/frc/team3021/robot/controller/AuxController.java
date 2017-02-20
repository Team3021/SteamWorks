package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class AuxController implements Controller {
	
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