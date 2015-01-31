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
	
	private boolean motorEnabled = false;
	private boolean grabEnabled = false;
	private boolean tiltEnabled = false;
	private boolean topEnabled = false;
	private boolean bottomEnabled = false;
	
	private Victor motor;
	private Solenoid grabSol, tiltSol;
	private DigitalInput checkTop, checkBottom;
	
	public static final int STOPPED = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	
	public static final boolean OPEN = true;
	public static final boolean CLOSED = false;
	
	public static final boolean TILT_UP = true;
	public static final boolean TILT_DOWN = false;
	
	public static final double LIFT_POWER = 1;
	
	private int direction;
	private double power;
	
	public BinSubsystem() {
		super("BinSubsystem");
		
		if(RobotMap.Bin.BIN_MOTOR != RobotMap.DOES_NOT_EXIST){
			motor = new Victor(RobotMap.Bin.BIN_MOTOR);
			motorEnabled = true;
		}
		
		if(RobotMap.Bin.BIN_GRABSOL != RobotMap.DOES_NOT_EXIST){
			grabSol = new Solenoid(RobotMap.Bin.BIN_GRABSOL);
			grabEnabled = true;
		}
		
		if(RobotMap.Bin.BIN_TILTSOL != RobotMap.DOES_NOT_EXIST){
			tiltSol = new Solenoid(RobotMap.Bin.BIN_TILTSOL);
			tiltEnabled = true;
		}
		
		if(RobotMap.Bin.BIN_DOWN_CHECK != RobotMap.DOES_NOT_EXIST){
			checkBottom = new DigitalInput(RobotMap.Bin.BIN_DOWN_CHECK);
			bottomEnabled = true;
		}
		
		if(RobotMap.Bin.BIN_TOP_CHECK != RobotMap.DOES_NOT_EXIST){
			checkTop = new DigitalInput(RobotMap.Bin.BIN_TOP_CHECK);
			topEnabled = true;
		}
	}
	
	public static BinSubsystem getInstance() {
		if(instance == null)
			instance = new BinSubsystem();
		return instance;
	}
	
	public double getPower() {
		return motorEnabled ? Math.abs(motor.get()) : 0;
	}
	
	public void setPower() {
		if (motorEnabled)
			motor.set(power);
	}
	
	public boolean getGrabSol() {
		return grabEnabled ? grabSol.get() : OPEN;
	}
	
	public boolean getTiltSol() {
		return tiltEnabled ? tiltSol.get() : TILT_DOWN;
	}
	
	public boolean isAtTop(){
		return topEnabled ? checkTop.get() : true;
	}
	
	public boolean isAtBottom(){
		return bottomEnabled ? checkBottom.get() : true;
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
		if (grabEnabled)
			grabSol.set(position);
	}
	
	public void setTiltSol(boolean position) {
		if (tiltEnabled)
			tiltSol.set(position);
	}
	
	public void stopLift(){
		setBin(STOPPED, 0);
	}

    public void initDefaultCommand() {

    }

	public void updateSmartDashboard() {
		
	}
	
}

