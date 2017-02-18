package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;

public class Drive extends Subsystem {
	// Member Attributes
	private CANTalon RightRear;
	private CANTalon RightFront;
	private CANTalon LeftRear;
	private CANTalon LeftFront;
	
	private RobotDrive robotDrive;
	
	public Drive() {
		LeftFront = new CANTalon(25);
		LeftRear = new CANTalon(24);
		RightFront = new CANTalon(22);
		RightRear = new CANTalon(23);
		
		robotDrive = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);

		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
	}
	
	public int getDirection(double n) {
		int result = 0;
		
		// Returns either 1, -1, or 0 depending on whether the argument is 
		// positive, negative, or neutral respectively.
		// Returns 0 when given -0 as an argument.
		if (n > 0) {
			result = 1;
		}
		else if (n < -0) {
			result = -1;
		}
		else {
			result = 0;
		}
		
		// Reverse the result to make the robot drive in the opposite direction
		result = result * -1;
		
		return result;
	}
	
	public void teleopPeriodic() {
		// Determines whether the output of expSpeed should be positive or negative.
		int verticalDirection = getDirection(controller.getMoveValue());
		int horizontalDirection = getDirection(controller.getTurnValue());
		
		// These equations are modified versions of the standard exponential form, ab^x. 
		// The equations here are of the form d(ab^|x| - a) where d is the respective direction value (note above). 
		// If you wish to alter these equations, adjust b, then solve for a at (1, 1).
		// Greater values of b make the controls less sensitive; smaller values are more sensitive.
		double expSpeed = verticalDirection * (0.2 * Math.pow(6.0, Math.abs(controller.getMoveValue())) - 0.2);
		double expTurn = horizontalDirection * (0.25 * Math.pow(5.0, Math.abs(controller.getTurnValue())) - 0.25);
		
		robotDrive.arcadeDrive(expSpeed, expTurn, false);
	}
}
