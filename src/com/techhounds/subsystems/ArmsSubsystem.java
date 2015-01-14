package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ArmsSubsystem extends Subsystem {
    
	private static ArmsSubsystem instance;
	
	public static final double FEED_IN = 0.5;
	public static final double FEED_OUT = -0.5;
	
	private Victor left;
	private Victor right;
	
	private Solenoid sol;
	
	private ArmsSubsystem() {
		left = new Victor(RobotMap.LEFT_ARM);
		right = new Victor(RobotMap.RIGHT_ARM);
		sol = new Solenoid(RobotMap.DIRECTION);
	}
	
	public static ArmsSubsystem getInstance() {
		if (instance == null) {
			instance = new ArmsSubsystem();
		}
		
		return instance;
	}
		
	public double getPower() {
		return left.get();
	}
	
	public void setPower(double power) {
		left.set(Robot.checkRange(power, -1, 1));
		right.set(Robot.checkRange(power, -1, 1));
	}
	
	public boolean getPosition() {
		return sol.get();
	}
	
	public void stopArms() {
		left.set(0);
		right.set(0);
	}

    public void initDefaultCommand() {
        
    }
}

