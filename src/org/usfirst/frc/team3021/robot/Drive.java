package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;
import java.lang.Math; // Used for a custom exponential function for the moveValue. 
import edu.wpi.first.wpilibj.RobotDrive;

public class Drive {
	// Member Attributes
	private CANTalon RightRear;
	private CANTalon RightFront;
	private CANTalon LeftRear;
	private CANTalon LeftFront;
	private RobotDrive SpeedBase;
	private Controller controller;

	public Drive(){
		LeftFront = new CANTalon(25);
		LeftRear = new CANTalon(24);
		RightFront = new CANTalon(22);
		RightRear = new CANTalon(23);
		
		SpeedBase=new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);

	}
	
	public int getDirection(double n){
		// Returns either 1, -1, or 0 depending on whether the argument is 
		// positive, negative, or neutral respectively.
		// Returns 0 when given -0 as an argument.
		if (n > 0) {
			return 1;
		}
		else if (n < -0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void teleopPeriodic(){
		// Determines whether the output of expSpeed should be positive or negative.
		int verticalDirection = getDirection(controller.getMoveValue());
		int horizontalDirection = getDirection(controller.getTurnValue());
		
		// These equations are modified versions of the standard exponential form, ab^x. 
		// The equations here are of the form d(ab^|x| - a) where d is the respective direction value (note above). 
		// If you wish to alter these equations, adjust b, then solve for a at (1, 1).
		// Greater values of b make the controls less sensitive; smaller values are more sensitive.
		double expSpeed = verticalDirection * (0.2 * Math.pow(6.0, Math.abs(controller.getMoveValue())) - 0.2);
		double expTurn = horizontalDirection * (0.25 * Math.pow(5.0, Math.abs(controller.getTurnValue())) - 0.25);
		
		SpeedBase.arcadeDrive(expSpeed, expTurn, false);
	}
}
