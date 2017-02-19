package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Controller;

import edu.wpi.first.wpilibj.Joystick;

public class AuxController implements Controller{
	
	Joystick Buttons;
	
	public AuxController(int port){
		Buttons = new Joystick(port);
	}
	
	@Override
	public double getMoveValue(){
		return 0;
	}
	
	@Override
	public double getTurnValue(){
		return 0;
	}
	
	@Override
	public boolean isLaunching(){
		return Buttons.getRawButton(6);
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return false;
	}

	@Override
	public boolean isCollecting() {
		return Buttons.getRawButton(7);
	}

	@Override
	public boolean isResettingNavx() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRotateToZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRotatingToNinety() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRotatingToNegativeNinety() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRotatingToOneHundredEighty() {
		// TODO Auto-generated method stub
		return false;
	}
}