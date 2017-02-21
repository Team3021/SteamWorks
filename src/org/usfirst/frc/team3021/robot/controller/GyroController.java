package org.usfirst.frc.team3021.robot.controller;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroController implements PIDOutput {

	// Member Attributes
	private AHRS navx;
	private PIDController pidController;
    double rotateToAngleRate;
    
	private double compassAdjustment;
    
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
    
    public static final double kToleranceDegrees = 2.0f; // Tolerance--Precision of turning with the Navx
	
	public GyroController() {
        try {
        	// The Navx--connected by USB
            navx = new AHRS(Port.kUSB); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

        pidController = new PIDController(kP, kI, kD, kF, navx, this); 
        
        pidController.setInputRange(-180.0f, 180.0f);
        pidController.setOutputRange(-0.5, 0.5);
        pidController.setAbsoluteTolerance(kToleranceDegrees);
        pidController.setContinuous(true);

        pidController.enable();
        
        resetGyro();
	}

	public void printAngle() {
		SmartDashboard.putNumber("GyroController : gyro compass angle",  navx.getCompassHeading());
		SmartDashboard.putNumber("GyroController : gyro pitch angle",  navx.getPitch());
		SmartDashboard.putNumber("GyroController : compass adjustment",  compassAdjustment);
		SmartDashboard.putNumber("GyroController : angle", getAngle());
	}

	public void printCentralAngle() {
		SmartDashboard.putNumber("GyroController : central angle", convertToCentralAngle(getAngle()));
	}
	
	public void resetGyro() {
		System.out.println("Resetting gyro");
		
		navx.reset();
		
		compassAdjustment =  navx.getCompassHeading();
	}

	public static double getAdustedAngle(double angle, double offset) {
		double adjustedCompassHeading = angle - offset;
		
		if (adjustedCompassHeading < 0) {
			adjustedCompassHeading = adjustedCompassHeading + 360;
		}
		
		return adjustedCompassHeading;
	}
	
	// This returns a value from 0 to 360
	public double getAngle() {
		return getAdustedAngle(navx.getCompassHeading(), compassAdjustment);
	}

	// Input is the range of 0 to 360
	public void setAngle(double angle) {
		angle = convertToCentralAngle(angle);
		
		setCentralAngle(angle);
	}
	
	// This returns a value from -180 to 180
	public double getCentralAngle() {
		return convertToCentralAngle(getAngle());
	}

	// Input is the range of -180 to 180 to control the PID Controller
	public void setCentralAngle(double setpoint) {
		pidController.setSetpoint(setpoint);
	}

	public double getTurnValue() {
		SmartDashboard.putNumber("GyroController : currentRotationRate",  rotateToAngleRate);

		return rotateToAngleRate;
	}
	
    @Override
    /* This function is invoked periodically by the PID Controller, */
    /* based upon navX MXP yaw angle input and PID Coefficients.    */
    public void pidWrite(double rotateToAngleRate) {
        this.rotateToAngleRate = rotateToAngleRate;
    }

	// Input is the range of 0 to 360
    // Output is the range of -180 to 180
	public static double convertToCentralAngle(double angle) {
		double centralAngle = 0;
		
		// convert from 360 degree system to central range system of -180 to 180
		if (angle == 0) {
			centralAngle = 0;
		}
		else if (0 < angle && angle < 180) {
			centralAngle = angle;
		}
		else if (180 < angle && angle <= 360) {
			centralAngle = angle - 360;
		}
		else if (angle == 180) {
			centralAngle = 180;
		}
		
		return centralAngle;
	}
	
	public static void main(String[] args) {
		System.out.println(GyroController.convertToCentralAngle(180));
		System.out.println(GyroController.convertToCentralAngle(90));
		System.out.println(GyroController.convertToCentralAngle(0));
		System.out.println(GyroController.convertToCentralAngle(360));
		System.out.println(GyroController.convertToCentralAngle(270));
		System.out.println(GyroController.convertToCentralAngle(181));
		System.out.println("");
		System.out.println(getAdustedAngle(0, 90));
		System.out.println(getAdustedAngle(90, 90));
		System.out.println(getAdustedAngle(180, 90));
		System.out.println(getAdustedAngle(270, 90));
		System.out.println(getAdustedAngle(360, 90));
	}
}
