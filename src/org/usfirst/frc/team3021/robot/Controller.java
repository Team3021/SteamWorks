package org.usfirst.frc.team3021.robot;

public interface Controller {

	double getMoveValue();

	double getTurnValue();

	boolean isSpinnerForward();

	boolean isSwitchingCamera();
	
	boolean isFeederForward();
}