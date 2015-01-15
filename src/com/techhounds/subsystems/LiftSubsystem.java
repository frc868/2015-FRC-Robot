package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Alex Fleig, Matt Simmons, Ayon Mitra, Clayton Detke
 */
public class LiftSubsystem extends BasicSubsystem {	
	
	private static LiftSubsystem instance;
	
	public static final double LIFT_POWER = 0.5;
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int STOPPED = 3;
	
	public static final boolean IN = true;
	public static final boolean OUT = false;
	
	private int direction = STOPPED;
	public double power = 0;
	
	private Victor liftMotor;
	
	private Solenoid sol;
	
	private DigitalInput checkTop;
	private DigitalInput checkBottom;
	
	private LiftSubsystem() {
		liftMotor = new Victor(RobotMap.Lift.LIFT_MOTOR);
		checkTop = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_TOP);
		checkBottom = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_BOTTOM);
	}
	
	public static LiftSubsystem getInstance() {
		if (instance == null) {
			instance = new LiftSubsystem();
		}
		return instance;
	}
	
	public boolean isAtTop() {
		return checkTop.get();
	}
	
	public boolean isAtBottom() {
		return checkBottom.get();
	}
	
	public double getPower() {
		return Math.abs(liftMotor.get());
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setPower() {
		liftMotor.set(power);
	}
	
	public void setLift(int dir, double power) {
		
		power = Math.max(Math.min(power, 1), -1);
		
		if (dir == UP){
			power *= -1;
		}else if (dir == STOPPED){
			power = 0;
		}
		
		this.direction = dir;
		this.power = power;
	}
	
	public void stopLift() {
		setLift(STOPPED, 0);
	}
	
	public boolean getPosition() {
		return sol.get();
	}
	
	public void setPosition(boolean position) {
		sol.set(position);
	}

	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
