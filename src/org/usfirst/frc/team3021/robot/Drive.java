package org.usfirst.frc.team3021.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

public class Drive {
	// Member Attributes
	private CANTalon RightRear;
	private CANTalon RightFront;
	private CANTalon LeftRear;
	private CANTalon LeftFront;
	private RobotDrive SpeedBase;
	private ThrustMasterController JS;

	public Drive(){
		JS = new ThrustMasterController(0);

		LeftFront = new CANTalon(25);
		LeftRear = new CANTalon(24);
		RightFront = new CANTalon(22);
		RightRear = new CANTalon(23);
		SpeedBase=new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);

		SpeedBase.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		SpeedBase.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		SpeedBase.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		SpeedBase.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
		
	}
	
	public void teleopPeriodic(){
		SpeedBase.arcadeDrive(JS.getMoveValue(), JS.getTurnValue(), true);
	}
}
