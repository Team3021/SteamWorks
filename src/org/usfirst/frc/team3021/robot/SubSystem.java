package org.usfirst.frc.team3021.robot;

public abstract class SubSystem {
	
	protected Controller controller;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public abstract void teleopPeriodic();

}