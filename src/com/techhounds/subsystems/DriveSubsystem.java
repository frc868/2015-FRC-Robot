package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.OI;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.DriveWithGamepad;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Alex Fleig, Matt Simons, Ayon Mitra, Clayton Detke
 */
public class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;
	
	private boolean overrideOperatorButton = false;
	private boolean twoPersonDrive = true;
	private boolean isForward = true;
	private boolean isHalfSpeed = false;

	private MultiMotor leftMotors;
	private MultiMotor rightMotors;
	
	private Counter leftEnc;
	private Counter rightEnc;
	
	private final double COUNTS_TO_FEET = 0;

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
		
		if(RobotMap.Drive.LEFT_ENC != RobotMap.DOES_NOT_EXIST){
			leftEnc = new Counter(RobotMap.Drive.LEFT_ENC);
			leftEnc.setDistancePerPulse(COUNTS_TO_FEET);
		}
		
		if(RobotMap.Drive.RIGHT_ENC != RobotMap.DOES_NOT_EXIST){
			rightEnc = new Counter(RobotMap.Drive.RIGHT_ENC);
			rightEnc.setDistancePerPulse(COUNTS_TO_FEET);
		}
	}
	
	public static DriveSubsystem getInstance() {
		if(instance == null)
			instance = new DriveSubsystem();
		return instance;
	}
	
	public void driveWithGamepad() {
		double powerMag;
		double steerMag;
		boolean posPower;
		boolean posSteer;
		
		double onePower = OI.getInstance().getDriverLeftYAxis();
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
	
	public double getLeftDistance() {
		return leftEnc.get();
	}
	
	public double getRightDistance() {
		return rightEnc.get();
	}
	
	public double getAvgDistance() {
		return (getLeftDistance() + getRightDistance())/2;
	}
	
	public double getLeftVelocity() {
		return leftEnc.getRate();
	}
	
	public double getRightVelocity() {
		return rightEnc.getRate();
	}
	
	public double getAvgVelocity() {
		return (getLeftVelocity() + getRightVelocity())/2;
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
	
	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithGamepad());
	}
}