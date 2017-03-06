package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Stanley;

public class AuxController extends BaseController {
	
	public AuxController() {
		buttonActions.add(new ButtonAction(1, "RIGHT_TOGGLE_BUTTON", "unassigned"));
		buttonActions.add(new ButtonAction(2, "MIDDLE_TOGGLE_BUTTON", "isTargetLocatorEnabled"));
		buttonActions.add(new ButtonAction(3, "LEFT_TOGGLE_BUTTON", "isScopeEnabled"));
	
		buttonActions.add(new ButtonAction(4, "SAFETY_TRIGGER", "isClimberSafteyOn"));
		
		buttonActions.add(new ButtonAction(5, "TOP_BLUE_BUTTON", "isClimbing"));
		buttonActions.add(new ButtonAction(6, "MIDDLE_BLUE_BUTTON", "isLaunching"));
		buttonActions.add(new ButtonAction(7, "BOTTOM_BLUE_BUTTON", "isCollecting"));
	
		buttonActions.add(new ButtonAction(8, "TOP_RED_BUTTON", "isSwitchingCamera"));
		buttonActions.add(new ButtonAction(9, "MIDDLE_RED_BUTTON", "unassigned"));
		buttonActions.add(new ButtonAction(10, "BOTTOM_RED_BUTTON", "isStoppingCommands"));
	}
	
	public AuxController(int port) {
		super(port);
	}
	
	@Override
	public boolean getRawButton(String action) {
		if (!Stanley.configuration.isAuxPanelEnabled()) {
			return false;
		}
		
		return super.getRawButton(action);
	}
}