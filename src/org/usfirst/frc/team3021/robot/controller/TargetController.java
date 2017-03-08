package org.usfirst.frc.team3021.robot.controller;

import org.usfirst.frc.team3021.robot.Stanley;
import org.usfirst.frc.team3021.robot.vision.TargetPIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TargetController implements PIDOutput {
	
	private TargetPIDSource pidSource;
	
	private PIDController pidController;
	
    double rotateRate;
	
    static final double kP = 0.02; // Proportion
    static final double kI = 0.00; // Integration 
    static final double kD = 0.00; // Differential
    static final double kF = 0.00; // Feedback
    
    public static final double kTolerance = 2.0f; // Tolerance

	public TargetController() {
		
		pidSource = new TargetPIDSource();
		
		pidSource.setPIDSourceType(PIDSourceType.kDisplacement);
		pidSource.setTargetScope(Stanley.vision.getTargetScope());
		pidSource.setTargetLocator(Stanley.vision.getTargetLocator());

        pidController = new PIDController(kP, kI, kD, kF, pidSource, this); 
        
        pidController.setInputRange(-160.0f, 160.0f);
        pidController.setOutputRange(-0.4, 0.4);
        pidController.setAbsoluteTolerance(kTolerance);
        pidController.setContinuous(true);

        pidController.enable();
	}

	// Input is the range of -160 to 160 to control the PID Controller
	public void setDesiredAngle(double setpoint) {
		pidController.setSetpoint(setpoint);
	}

	public double getTurnValue() {
		SmartDashboard.putNumber("TargetController : currentRotationRate",  rotateRate);

		return rotateRate;
	}

	@Override
	public void pidWrite(double output) {
		this.rotateRate = output;
	}
}
