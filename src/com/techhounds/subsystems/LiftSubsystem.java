package com.techhounds.subsystems;

import com.techhounds.OI;
import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class LiftSubsystem extends BasicSubsystem{	
	
	private static LiftSubsystem instance;
	
	public static final double LIFT_UP_POWER = 0.5;
	public static final double LIFT_DOWN_POWER = -0.5;
	
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
	
	public void setPower(double power) {
		liftMotor.set(Robot.checkRange(power, -1, 1));
	}
	
	public void stopLift() {
		liftMotor.set(0);
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
