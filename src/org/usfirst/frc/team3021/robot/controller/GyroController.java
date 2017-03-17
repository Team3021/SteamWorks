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
	
	private final String PREF_GYRO_ANGLE_ADJUSTMENT = "Gyro.angle.adjustment";

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
    
    public static final double kToleranceDegrees = 2.0f; // Tolerance--Precision of turning with the Navx
	
    private double lastCalculatedOffset = 0;
    
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
        		System.out.println("Using NavX on USB port");
        		navx = new AHRS(Port.kUSB); 
        	} else {
        		// The Navx--connected by MXP port
        		System.out.println("Using NavX on MXP port");
        		navx = new AHRS(Port.kMXP); 
        	}
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

        pidController = new PIDController(kP, kI, kD, kF, navx, this); 
        
        pidController.setInputRange(-180.0f, 180.0f);
        pidController.setOutputRange(-0.3, 0.3);
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

	public boolean isMoving() {
		return navx.isMoving();
	}

	// Input is the range of -180 to 180 to control the PID Controller
	public void setDesiredAngle(double setpoint) {
		pidController.setSetpoint(setpoint);
	}

	public double getGyroRotation() {
		
		double rotation = navx.getYaw();
		
		SmartDashboard.putNumber("GyroController : Navx : Rotation", rotation);

		return rotation;
	}

	public double getTurnValue() {
		double angleAdjustment = Preferences.getInstance().getDouble(PREF_GYRO_ANGLE_ADJUSTMENT, 0.0);
		
		double adjustedAngle = rotateToAngleRate + angleAdjustment;
		
		SmartDashboard.putNumber("GyroController : currentRotationRate",  adjustedAngle);

		return adjustedAngle;
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
		
		if (gyroOffset != lastCalculatedOffset) {
			System.out.println("GyroController calculated offset: " + gyroOffset);
			
			lastCalculatedOffset = gyroOffset;
		}
		
		return gyroOffset;
	}
	
	public void resetGyro() {
		System.out.println("Resetting gyro");
		
		navx.reset();
	}
	
	public void zeroGyro() {
		System.out.println("Zero the gyro");
		
		navx.zeroYaw();
	}
	
    @Override
    /* This function is invoked periodically by the PID Controller, */
    /* based upon navX MXP yaw angle input and PID Coefficients.    */
    public void pidWrite(double rotateToAngleRate) {
        this.rotateToAngleRate = rotateToAngleRate;
    }
}