package org.usfirst.frc.team3021.robot;

public abstract class Subsystem extends edu.wpi.first.wpilibj.command.Subsystem {
	
	protected Controller controller;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void teleopPeriodic() {
		
	}

}