package org.usfirst.frc.team3021.robot;

public interface Controller {

	double getMoveValue();

	double getTurnValue();

	boolean isLaunching();

	boolean isSwitchingCamera();
	
	boolean isCollecting();

	boolean isResettingNavx();

	boolean isRotatingToNinety();
	
	boolean isRotatingToNegativeNinety();
	
	boolean isRotatingToOneHundredEighty();

	boolean isRotatingCustom();

	boolean isRotatingCustomNegative();

	boolean isXbox();

	boolean isClimberSafteyOn();

	boolean isClimbing();
}