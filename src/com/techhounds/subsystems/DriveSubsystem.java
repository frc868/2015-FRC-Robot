package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.OI;
import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;
	private RobotDrive robotDrive;
	private MultiMotor leftMotors;
	private MultiMotor rightMotors;

	private DriveSubsystem() {
		leftMotors = new MultiMotor(
				new SpeedController[]{
						new Victor(RobotMap.LEFT_DRIVE_MOTOR_1),
						new Victor(RobotMap.LEFT_DRIVE_MOTOR_2),
						new Victor(RobotMap.LEFT_DRIVE_MOTOR_3),
					},
				new boolean[]{false, false, false});
		
		rightMotors = new MultiMotor(
				new SpeedController[]{
						new Victor(RobotMap.RIGHT_DRIVE_MOTOR_1),
						new Victor(RobotMap.RIGHT_DRIVE_MOTOR_2),
						new Victor(RobotMap.RIGHT_DRIVE_MOTOR_3),
					},
				new boolean[]{false, false, false});
		
		robotDrive = new RobotDrive(leftMotors, rightMotors);
	}
	
	public static DriveSubsystem getInstance() {
		if(instance == null)
			instance = new DriveSubsystem();
		return instance;
	}
	
	public double getRightPower() {
		return rightMotors.get();
	}
	public double getLeftPower() {
		return leftMotors.get();
	}
	
	public double getAveragePower() {
		return (getLeftPower() + getRightPower())/2;
	}
	public void setRightPower(double newVal) {
		rightMotors.set(Robot.checkRange(newVal, -1, 1));
	}
	
	public void setLeftPower(double newVal) {
		rightMotors.set(Robot.checkRange(newVal, -1, 1));
	}
	
	public void setPower(double newVal) {
		setRightPower(newVal);
		setLeftPower(newVal);
	}
	
	public void stopMotors() {
		leftMotors.set(0);
		rightMotors.set(0);
	}
	
	public void driveWithGamepad() {
		double magnitude = OI.getDriverLeftYAxis();
		double steering = OI.getDriverRightXAxis();
		
		robotDrive.arcadeDrive(magnitude, steering);
	}
	
	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}