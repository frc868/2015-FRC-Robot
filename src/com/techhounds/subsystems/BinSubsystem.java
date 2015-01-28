package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
//add second solonoid
/**
 * @author Alex Fleig, Matt Simones, Ayon Mitra, Clayton Detke, Adam Foster
 */
public class BinSubsystem extends BasicSubsystem {
    
	public static BinSubsystem instance;
	
	private DigitalInput checkTop, checkBottom;
	
	Victor motor;
	Solenoid grabSol; //grab
	Solenoid tiltSol;//tilt
	
	public static final int STOPPED = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	
	public static final boolean OPEN = true;
	public static final boolean CLOSED = false;
	
	public static final boolean TILTUP = true;
	public static final boolean TILTDOWN = false;
	
	public static final double LIFT_POWER = 1;
	
	private int direction;
	private double power;
	
	public BinSubsystem() {
		
		if(RobotMap.Bin.BIN_MOTOR != RobotMap.DOES_NOT_EXIST)
			motor = new Victor(RobotMap.Bin.BIN_MOTOR);
		
		if(RobotMap.Bin.BIN_GRABSOL != RobotMap.DOES_NOT_EXIST)
			grabSol = new Solenoid(RobotMap.Bin.BIN_GRABSOL);
		
		if(RobotMap.Bin.BIN_TILTSOL != RobotMap.DOES_NOT_EXIST)
			tiltSol = new Solenoid(RobotMap.Bin.BIN_TILTSOL);
		
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
	
	public boolean getGrabSol() {
		return grabSol.get();
	}
	
	public boolean getTiltSol() {
		return tiltSol.get();
	}
	
	public boolean isAtTop(){
		return checkTop.get();
	}
	
	public boolean isAtBottom(){
		return checkBottom.get();
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
	
	public void setGrabSol(boolean position) {
		grabSol.set(position);
	}
	
	public void setTiltSol(boolean position) {
		tiltSol.set(position);
	}
	
	public void stopLift(){
		setBin(STOPPED, 0);
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

