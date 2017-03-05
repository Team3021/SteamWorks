package org.usfirst.frc.team3021.robot.controller;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;

public abstract class BaseController implements Controller {
	
	List<ButtonAction> buttonActions = new ArrayList<ButtonAction>();
	
	// Member Attributes
	Joystick controller;
	
	public BaseController() {
		
	}
	
	public BaseController(int port) {
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
	public boolean isXbox() {
		if (controller != null) {
			return controller.getIsXbox();
		}
		
		return false;
	}
	
	public boolean getRawButton(String action) {
		
		ButtonAction foundButtonAction = null;
		
		for (ButtonAction buttonAction : buttonActions) {
			if (buttonAction.getAction().equals(action)) {
				foundButtonAction = buttonAction;
				break;
			}
		}
		
		if (foundButtonAction == null) {
			System.err.println("Failed to find button for action");
			return false;
		}
		
		int number = foundButtonAction.getNumber();
		
		if (controller.getButtonCount() < number) {
			return false;
		}
		
		boolean buttonOn = controller.getRawButton(number);
		
		if (buttonOn) {
			System.out.println("button " + number + " for action " + action);
			return true;
		}
		
		return false;
	}

	@Override
	public void printButtonActions(String controller) {
		System.out.println("******************* " + controller + " *******************");

		for (ButtonAction buttonAction : buttonActions) {
			System.out.println(buttonAction);
		}

		System.out.println("");
	}
}

