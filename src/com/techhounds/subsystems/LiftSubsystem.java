package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class LiftSubsystem extends BasicSubsystem{
	
	private static double liftPower;
	private static LiftSubsystem instance;
	private static Victor liftMotor;
	private static DigitalInput checkTop;
	private static DigitalInput checkBottom;
	
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
	
	public static boolean getTop() {
		return checkTop.get();
	}
	
	public static boolean getBottom() {
		return checkBottom.get();
	}
	
	public static double setPower(double power) {
		liftPower = Robot.checkRange(power, -1, 1);
		return liftPower;
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
