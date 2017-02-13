package org.usfirst.frc.team3021.robot;

public abstract class SubSystem {
	
	protected Controller controller;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void autonomousPeriodic() {
		
	}

	public void teleopPeriodic() {
		
	}

	public void testPeriodic() {
		
	}

}