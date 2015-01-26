package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Alex Fleig, Matt Simones, Ayon Mitra, Clayton Detke, Adam Foster
 */
public class BinSubsystem extends BasicSubsystem {
    
	public static BinSubsystem instance;
	
	private DigitalInput checkTop, checkBottom;
	
	static Talon motor;
	Solenoid sol;
	
	public static final int STOPPED = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	
	public static final boolean OPEN = true;
	public static final boolean CLOSED = false;
	
	private int direction;
	private double power;
	
	public BinSubsystem() {
		
		if(RobotMap.Bin.BIN_MOTOR != RobotMap.DOES_NOT_EXIST)
			motor = new Talon(RobotMap.Bin.BIN_MOTOR);
		
		if(RobotMap.Bin.BIN_SOL != RobotMap.DOES_NOT_EXIST)
			sol = new Solenoid(RobotMap.Bin.BIN_SOL);
		
		if(RobotMap.Bin.BIN_DOWN_CHECK != RobotMap.DOES_NOT_EXIST)
			checkBottom = new DigitalInput(RobotMap.Bin.BIN_DOWN_CHECK);
		
		if(RobotMap.Bin.BIN_TOP_CHECK != RobotMap.DOES_NOT_EXIST)
			checkTop = new DigitalInput(RobotMap.Bin.BIN_TOP_CHECK);
	}
	
	public static BinSubsystem getInstance() {
		if(instance == null)
			instance = new BinSubsystem();
		return instance;
	}
	
	public double getPower() {
		return Math.abs(motor.get());
	}
	
	public void setPower() {
		motor.set(power);
	}
	
	public boolean isAtTop(){
		return !checkTop.get();
	}
	
	public boolean isAtBottom(){
		return !checkBottom.get();
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setBin(int dir, double power) {
		power = Math.max(Math.min(power, 1), 0);
		if(dir == UP) {
			power *= -1;
		} else if (dir == STOPPED) {
			power = 0;
		}
		
		this.power = power;
		direction = dir;
	}
	
	public void setSolenoid(boolean position) {
		sol.set(position);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		
	}
	
}

