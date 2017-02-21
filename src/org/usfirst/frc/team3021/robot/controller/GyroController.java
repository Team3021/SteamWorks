package org.usfirst.frc.team3021.robot.controller;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroController implements PIDOutput {
	
	private final String PREF_GYRO_USB_ENABLED = "Gyro.usb.enabled";
	private final String PREF_GYRO_MXP_ENABLED = "Gyro.mxp.enabled";

	// Member Attributes
	private AHRS navx;
	private PIDController pidController;
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
    
    public static final double kToleranceDegrees = 1.25f; // Tolerance--Precision of turning with the Navx
	
	public GyroController() {
		if (!isGyroEnabled()) {
			System.out.println("WARNING !!! NO GYRO PORT ENABLED");
			return;  
		}
		else {
			System.out.println("gyro port enabled");
		}
		
        try {
        	
        	if (isUSBEnabled()) {
        		// The Navx--connected by USB port
        		navx = new AHRS(Port.kUSB); 
        	} else {
        		// The Navx--connected by MXP port
        		navx = new AHRS(Port.kMXP); 
        	}
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
	
	private boolean isUSBEnabled() {
		return Preferences.getInstance().getBoolean(PREF_GYRO_USB_ENABLED, false);
	}
	
	private boolean isMXPEnabled() {
		return Preferences.getInstance().getBoolean(PREF_GYRO_MXP_ENABLED, true);
	}
	
	private boolean isGyroEnabled() {
		return isUSBEnabled() || isMXPEnabled();
	}
	
	public void resetGyro() {
		System.out.println("Resetting gyro");
		
		navx.reset();
	}

	// Input is the range of -180 to 180 to control the PID Controller
	public void setDesiredAngle(double setpoint) {
		pidController.setSetpoint(setpoint);
	}

	public double getTurnValue() {
		SmartDashboard.putNumber("GyroController : currentRotationRate",  rotateToAngleRate);

		return rotateToAngleRate;
	}

	public double getGyroOffset() {
		double gyroOffset = (getTurnValue() * .01111111111);
		
		// IF THE ABOSOLUTE VAL OF THE GYRO OFFSET IS LARGER THAN 1
		if (Math.abs(gyroOffset) > 1) {
			// SET THE GYRO OFFSET TO EITHER 1 OR -1
			gyroOffset =  (-1 * (Math.abs(gyroOffset) / gyroOffset));
		} else {
			gyroOffset =  -gyroOffset;
		}
		
		return gyroOffset;
	}

	public boolean isRotating() {
		return navx.isRotating();
	}

	public boolean isOnTarget() {
		return pidController.onTarget();
	}
	
    @Override
    /* This function is invoked periodically by the PID Controller, */
    /* based upon navX MXP yaw angle input and PID Coefficients.    */
    public void pidWrite(double rotateToAngleRate) {
        this.rotateToAngleRate = rotateToAngleRate;
    }
}