package org.usfirst.frc.team3021.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ThrustMasterController implements Controller {
	private static final int TRIGGER = 1; // The middle trigger on the back of the stick
	private static final int RBUTTON = 4; // The right button on the front of the stick
	private static final int SHOULDER = 3; // The right shoulder button on the back of the stick
	private static final int MIDDLEBUTTON = 2; // The striped middle button on the front of the stick
	
	// Member Attributes
	Joystick JS;
	
	public ThrustMasterController(int port){
		JS = new Joystick(port);
	}
	
	@Override
	public double getMoveValue(){
		return JS.getY();
	}
	
	@Override
	public double getTurnValue(){
		return JS.getX();
	}
	
	@Override
	public boolean isSpinnerForward(){
		return JS.getRawButton(TRIGGER);
	}
	
	@Override
	public boolean isSwitchingCamera(){
		return JS.getRawButton(MIDDLEBUTTON);
	}
}

