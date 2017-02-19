package org.usfirst.frc.team3021.robot.controller;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroController implements PIDOutput {

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
	
	public GyroController() {
        try {
        	// The Navx--connected by USB
            navx = new AHRS(Port.kUSB); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

        pidController = new PIDController(kP, kI, kD, kF, navx, this); 
        
        pidController.setInputRange(-180.0f,  180.0f);
        pidController.setOutputRange(-0.5, 0.5);
        pidController.setAbsoluteTolerance(kToleranceDegrees);
        pidController.setContinuous(true);

        pidController.enable();
	}

	public void resetGyro() {
		System.out.println("Resetting gyro");
		
		navx.reset();
	}
	
	public double getAngle() {
		
		SmartDashboard.putNumber("GyroController : current angle",  navx.getAngle());

		return navx.getAngle();
	}

	public void setAngle(double setpoint) {
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
}
