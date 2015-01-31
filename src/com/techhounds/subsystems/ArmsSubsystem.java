package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ArmsSubsystem extends Subsystem {
    
	private static ArmsSubsystem instance;
	
	public static final double FEED_IN = -0.5;
	public static final double FEED_OUT = 0.5;
	public static final double STOPPED = 0;
	public static final boolean OPEN = false;
	public static final boolean CLOSED = true;
	
	private boolean leftEnabled = false;
	private boolean rightEnabled = false;
	private boolean solEnabled = false;
	
	private SpeedController left;
	private SpeedController right;
	
	private Solenoid sol;
	
	private double leftMotorMult = 1;
	private double rightMotorMult = .8;
	
	private ArmsSubsystem() {
		super("ArmsSubsystem");
		
		if(RobotMap.Arms.LEFT_ARM != RobotMap.DOES_NOT_EXIST){
			left = new Victor(RobotMap.Arms.LEFT_ARM);
			leftEnabled = true;
		}
		if(RobotMap.Arms.RIGHT_ARM != RobotMap.DOES_NOT_EXIST){
			right = new Victor(RobotMap.Arms.RIGHT_ARM);
			rightEnabled = true;
		}
		if(RobotMap.Arms.ARM_SOL != RobotMap.DOES_NOT_EXIST){
			sol = new Solenoid(RobotMap.Arms.ARM_SOL);
			solEnabled = true;
		}
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
	
	public void setPower(double left, double right) {
		if (leftEnabled)
			this.left.set(Robot.checkRange(left, -1, 1) * leftMotorMult);
		if (rightEnabled)
			this.right.set(Robot.checkRange(right, -1, 1) * rightMotorMult);
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

