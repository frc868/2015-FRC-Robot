package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;
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
		return (leftMotors.get() + rightMotors.get())/2;
	}
	public void setRightPower(double newVal) {
		rightMotors.set(Robot.checkRange(newVal, -1, 1));
	}
	
	public void setLeftPower(double newVal) {
		leftMotors.set(Robot.checkRange(newVal, -1, 1));
	}
	
	public void stopMotors() {
		leftMotors.set(0);
		rightMotors.set(0);
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