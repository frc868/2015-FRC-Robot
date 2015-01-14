package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.OI;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.DriveWithGamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Alex Fleig, Matt Simmons, Ayon Mitra, Clayton Detke
 */
public class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;
	private RobotDrive robotDrive;
	private MultiMotor leftMotors;
	private MultiMotor rightMotors;
	private static boolean inverted = false;

	private DriveSubsystem() {
		leftMotors = new MultiMotor(
				new SpeedController[]{
						new Victor(RobotMap.Drive.LEFT_DRIVE_MOTOR_1),
						new Victor(RobotMap.Drive.LEFT_DRIVE_MOTOR_2),
						new Victor(RobotMap.Drive.LEFT_DRIVE_MOTOR_3),
					},
				new boolean[]{false, false, false});
		
		rightMotors = new MultiMotor(
				new SpeedController[]{
						new Victor(RobotMap.Drive.RIGHT_DRIVE_MOTOR_1),
						new Victor(RobotMap.Drive.RIGHT_DRIVE_MOTOR_2),
						new Victor(RobotMap.Drive.RIGHT_DRIVE_MOTOR_3),
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
		
		if (inverted) magnitude = -magnitude;
		robotDrive.arcadeDrive(magnitude, steering);
	}
	
	public void invert() {
		inverted = !inverted;
	}
	
	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithGamepad());
	}
}