package com.techhounds.subsystems;

import com.techhounds.RobotMap;
import com.techhounds.commands.bin.RunBin;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
//add second solonoid
/**
 * @author Alex Fleig, Matt Simones, Ayon Mitra, Clayton Detke, Adam Foster
 */
public class BinSubsystem extends BasicSubsystem {
    
	/*
	 *-----------------This Subsystem is Defunt---------------------------
	 */
	
	public static BinSubsystem instance;
	
	public static final double COUNT_TO_FEET = 1 / 500.0;
	public static final double MIN_TILT_DOWN_HEIGHT = 0;
	
	private boolean motorEnabled, grabEnabled, tiltEnabled, topEnabled, bottomEnabled, encEnabled;
	
	private Victor motor;
	private Solenoid grabSol, tiltSol;
	private DigitalInput checkTop, checkBottom;
	private Encoder enc;
	
	public static final int STOPPED = 0, DOWN = 1, UP = 2;
	public static final double LIFT_POWER = .5;
	public static final boolean OPEN = false, CLOSED = true, 
			TILT_UP = true, TILT_DOWN = false;
	
	private int direction;
	private double power;
	
	public BinSubsystem() {
		super("BinSubsystem");
		
		if(motorEnabled = RobotMap.Bin.MOTOR != RobotMap.DOES_NOT_EXIST)
			motor = new Victor(RobotMap.Bin.MOTOR);
		
		if(grabEnabled = RobotMap.Bin.GRABSOL != RobotMap.DOES_NOT_EXIST)
			grabSol = new Solenoid(RobotMap.Bin.GRABSOL);
		
		if(tiltEnabled = RobotMap.Bin.TILTSOL != RobotMap.DOES_NOT_EXIST)
			tiltSol = new Solenoid(RobotMap.Bin.TILTSOL);
		
		if(bottomEnabled = RobotMap.Bin.DOWN_CHECK != RobotMap.DOES_NOT_EXIST)
			checkBottom = new DigitalInput(RobotMap.Bin.DOWN_CHECK);
		
		if(topEnabled = RobotMap.Bin.TOP_CHECK != RobotMap.DOES_NOT_EXIST)
			checkTop = new DigitalInput(RobotMap.Bin.TOP_CHECK);
		
		if (encEnabled = RobotMap.Bin.ENCODER_A != RobotMap.DOES_NOT_EXIST){
			enc = new Encoder(RobotMap.Bin.ENCODER_A, RobotMap.Bin.ENCODER_B);
			enc.setDistancePerPulse(COUNT_TO_FEET);
		}
//		SmartDashboard.putData(this);
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
		if ((isAtTop() && getDirection() == UP) || (isAtBottom() && getDirection() == DOWN))//should be redundant
			power = 0;
		
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
		return topEnabled ? !checkTop.get() : true;
	}
	
	public boolean isAtBottom(){
		return bottomEnabled ? !checkBottom.get() : true;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setBin(int dir, double power) {
		power = Math.max(Math.min(power, 1), 0);
		if(dir == DOWN) {
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

	public double getEncCount(){
		return encEnabled ? enc.get() : 0;
	}
	
	public double getEncHeight(){
		return encEnabled ? enc.getDistance() : 0;
	}
	
	public void resetEncHeight(){
		if(encEnabled)
			enc.reset();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RunBin());
    }

	public void updateSmartDashboard() {
//		SmartDashboard.putNumber("Bin Enc Count", getEncCount());
//		SmartDashboard.putNumber("Bin Enc Height", getEncHeight());
//		SmartDashboard.putBoolean("Bin Top Switch", isAtTop());
//		SmartDashboard.putBoolean("Bin Bottom Switch", isAtBottom());
//		SmartDashboard.putNumber("Bin Direction", getDirection());
//		SmartDashboard.putNumber("Bin Power", getPower());
	}
}

