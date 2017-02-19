package org.usfirst.frc.team3021.robot.commands.driving;

import org.usfirst.frc.team3021.robot.controller.GyroController;

public class TurnToAngle extends TurnToCentralAngle {
	
	public TurnToAngle(double angle) {
		super();
		desiredAngle = GyroController.convertToCentralAngle(angle);
	}
}
