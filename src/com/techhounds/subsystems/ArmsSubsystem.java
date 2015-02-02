package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ArmsSubsystem extends Subsystem {
    
	private static ArmsSubsystem instance;
	
	public static final double FEED_IN = -0.5, FEED_OUT = 0.5, STOPPED = 0;
	public static final boolean OPEN = false, CLOSED = true;
	
	private double leftMotorMult = 1, rightMotorMult = .8;
	private boolean leftEnabled, rightEnabled, solEnabled;
	
	private SpeedController left, right;
	private Solenoid sol;
	
	private ArmsSubsystem() {
		super("ArmsSubsystem");
		
		if(leftEnabled = RobotMap.Arms.LEFT_ARM != RobotMap.DOES_NOT_EXIST)
			left = new Victor(RobotMap.Arms.LEFT_ARM);
		
		if(rightEnabled = RobotMap.Arms.RIGHT_ARM != RobotMap.DOES_NOT_EXIST)
			right = new Victor(RobotMap.Arms.RIGHT_ARM);
		
		if(solEnabled = RobotMap.Arms.ARM_SOL != RobotMap.DOES_NOT_EXIST)
			sol = new Solenoid(RobotMap.Arms.ARM_SOL);
	}
	
	public static ArmsSubsystem getInstance() {
		if (instance == null)
			instance = new ArmsSubsystem();
		return instance;
	}
		
	public double getLeftPower() {
		return leftEnabled ? left.get() : 0;
	}
	
	public double getRightPower() {
		return rightEnabled ? right.get() : 0;
	}
	
	public double getAvgPower() {
		return (getLeftPower() + getRightPower()) / 2;
	}
	
	public void setPower(double leftPower, double rightPower) {
		if (leftEnabled)
			left.set(Robot.checkRange(leftPower, -1, 1) * leftMotorMult);
		if (rightEnabled)
			right.set(Robot.checkRange(rightPower, -1, 1) * rightMotorMult);
	}
	
	public void setPower(double power) {
		setPower(power, power);
	}
	
	public boolean getPosition() {
		return solEnabled ? sol.get() : OPEN;
	}
	
	public void setPosition(boolean direction) {
		if (solEnabled)
			sol.set(direction);
	}
	
	public void stopArms() {
		setPower(0, 0);
	}
	
	public void updateSmartDashboard() {
		
	}

    public void initDefaultCommand() {
        
    }
}

