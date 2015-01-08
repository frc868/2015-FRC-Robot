package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Alex Fleig, Matt Simmons, Ayon Mitra, Clayton Detke
 */
public class LiftSubsystem extends BasicSubsystem {	
	
	private static LiftSubsystem instance;
	
	public static final double LIFT_POWER = 0.5;
	
	private Victor liftMotor;
	
	private DigitalInput checkTop;
	private DigitalInput checkBottom;
	
	private LiftSubsystem() {
		liftMotor = new Victor(RobotMap.LIFT_MOTOR);
		checkTop = new DigitalInput(RobotMap.DIGITAL_INPUT_TOP);
		checkBottom = new DigitalInput(RobotMap.DIGITAL_INPUT_BOTTOM);
	}
	
	public static LiftSubsystem getInstance() {
		if (instance == null) {
			instance = new LiftSubsystem();
		}
		return instance;
	}
	
	public boolean getTop() {
		return checkTop.get();
	}
	
	public boolean getBottom() {
		return checkBottom.get();
	}
	
	public double getPower() {
		return liftMotor.get();
	}
	
	private void setPower(double power) {
		liftMotor.set(Robot.checkRange(power, 0, 1));
	}
	
	/**
	 * Sets Lift Direction
	 * @param dir Pass either UP, DOWN, or STOP from LiftSubsystem.Direction
	 * @param power Power to set, regardless of direction, has to be from 0 to 1
	 */
	public void setLiftDirection(Direction dir, double power) {
		
		Math.abs(power);
		
		if(dir == Direction.UP)
			setPower(power);
		else if(dir == Direction.DOWN)
			setPower(-power);
		else
			stopLift();
	}
	
	public void stopLift() {
		setPower(0);
	}

	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static enum Direction {
		UP, DOWN, STOP
	}

}
