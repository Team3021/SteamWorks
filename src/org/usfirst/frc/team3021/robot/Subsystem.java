package org.usfirst.frc.team3021.robot;

import org.usfirst.frc.team3021.robot.commands.Noop;

public abstract class Subsystem extends edu.wpi.first.wpilibj.command.Subsystem {
	
	protected Controller controller;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Noop());
	}

	public void teleopPeriodic() {
		
	}

}