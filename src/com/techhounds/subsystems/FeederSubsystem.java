package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class FeederSubsystem extends Subsystem {
    
	/*
	 *-----------------This Subsystem is Final Robot ONLY!---------------------------
	 */
	
	private static FeederSubsystem instance;
	
	public static final double FEED_IN = -0.5, FEED_OUT = 0.5, STOPPED = 0;
	public static final boolean OPEN = false, CLOSED = true;
	
	private double leftMotorMult = 1, rightMotorMult = 1;
	private boolean leftEnabled, rightEnabled, solEnabled;
	
	private CANTalon left, right;
	private Solenoid sol;
	
	private FeederSubsystem() {
		super("ArmsSubsystem");
		
		if (!Robot.isFinal())
			return;
					
		if(leftEnabled = RobotMap.Feeder.LEFT_MOTOR != RobotMap.DOES_NOT_EXIST)
			left = new CANTalon(RobotMap.Feeder.LEFT_MOTOR);
		
		if(rightEnabled = RobotMap.Feeder.RIGHT_MOTOR != RobotMap.DOES_NOT_EXIST)
			right = new CANTalon(RobotMap.Feeder.RIGHT_MOTOR);
		
		if(solEnabled = RobotMap.Feeder.SOL != RobotMap.DOES_NOT_EXIST)
			sol = new Solenoid(RobotMap.Feeder.SOL);
	}
	
	public static FeederSubsystem getInstance() {
		if (instance == null)
			instance = new FeederSubsystem();
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

