package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.SubSystem;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Drive extends SubSystem  implements PIDOutput {
	// Member Attributes
	
	// main drive
	private RobotDrive robotDrive;
	
	// motor controllers
	private CANTalon RightRear;
	private CANTalon RightFront;
	private CANTalon LeftRear;
	private CANTalon LeftFront;
	
	// direction controllers using navx MXP
	private AHRS navx;
	private PIDController turnController;
    double rotateToAngleRate;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
    // Motor tuning values
    static final double kP = 0.02; // Proportion
    static final double kI = 0.00; // Integration 
    static final double kD = 0.00; // Differential
    static final double kF = 0.00; // Feedback
    
    static final double kToleranceDegrees = 2.0f; // Tolerance--Precision of turning with the Navx
	
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
        
        try {
        	// The Navx--connected by USB
            navx = new AHRS(Port.kUSB); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

        
        turnController = new PIDController(kP, kI, kD, kF, navx, this); 
        
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-0.5, 0.5);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);

	}
	
	private int getDirection(double n) {
		// Returns either 1, -1, or 0 depending on whether the argument is 
		// positive, negative, or neutral respectively.
		// Returns 0 when given -0 as an argument.
		
		int result = 0;
		
		if (n > 0) {
			result = 1;
		}
		else if (n < -0) {
			result = -1;
		}
		else {
			result = 0;
		}
		
		return result;
	}

	private double getMoveValue() {
		// Determines whether the output of expSpeed should be positive or negative.
		int verticalDirection = getDirection(controller.getMoveValue());
		
		// These equations are modified versions of the standard exponential form, ab^x. 
		// The equations here are of the form d(ab^|x| - a) where d is the respective direction value (note above). 
		// If you wish to alter these equations, adjust b, then solve for a at (1, 1).
		// Greater values of b make the controls less sensitive; smaller values are more sensitive.
		double expSpeed = -1 * verticalDirection * (0.2 * Math.pow(6.0, Math.abs(controller.getMoveValue())) - 0.2);
		
		return expSpeed;
	}

	private double getTurnValue() {
		
        boolean rotateToAngle = false;
        
        if (controller.isResettingNavx()) {
        	System.out.println("Reseting nav direction");
            navx.reset();
        }
        
        if (controller.isRotateToZero()) {
            turnController.setSetpoint(0.0f);
            rotateToAngle = true;
        }
        else if (controller.isRotatingToNinety()) {
            turnController.setSetpoint(90.0f);
            rotateToAngle = true;
        }
        else if (controller.isRotatingToNegativeNinety()) {
            turnController.setSetpoint(-90.0f);
            rotateToAngle = true;
        }
        else if (controller.isRotatingToOneHundredEighty()) {
            turnController.setSetpoint(180.0f);
            rotateToAngle = true;
        }
        
        double turnValue = 0.0; // Voltage given to the drive motors
        
        if (rotateToAngle) {
        	
        	// Starts calculations for turning with the Navx
            turnController.enable();
            
            turnValue = rotateToAngleRate;
            
            // increase the turn value to ensure the motors have enough voltage to move
//            turnValue = (turnValue * 0.4) + 0.1;
            
            System.out.println("currentRotationRate " + turnValue);
            System.out.println("ahrs.getAngle() " + navx.getAngle());
        } 
        else {
        	turnController.disable();
        	
        	turnValue = controller.getTurnValue();
        	
        	int turnDirection = getDirection(turnValue);
    		
    		// These equations are modified versions of the standard exponential form, ab^x. 
    		// The equations here are of the form d(ab^|x| - a) where d is the respective direction value (note above). 
    		// If you wish to alter these equations, adjust b, then solve for a at (1, 1).
    		// Greater values of b make the controls less sensitive; smaller values are more sensitive.
    		turnValue = turnDirection * (0.25 * Math.pow(5.0, Math.abs(turnValue)) - 0.25);
        }
        
		

        return turnValue;
	}
	
    @Override
    /* This function is invoked periodically by the PID Controller, */
    /* based upon navX MXP yaw angle input and PID Coefficients.    */
    public void pidWrite(double output) {
        rotateToAngleRate = output;
    }
	
	public void teleopPeriodic() {
		double speedValue = getMoveValue();
		double turnValue = getTurnValue();
		
		robotDrive.arcadeDrive(speedValue, turnValue, false);
	}
}
