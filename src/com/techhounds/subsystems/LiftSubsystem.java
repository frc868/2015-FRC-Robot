package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.lift.RunLift;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Alex Fleig, Matt Simons, Ayon Mitra, Clayton Detke
 */
public class LiftSubsystem extends BasicSubsystem {	
	
	private static LiftSubsystem instance;
	
	public static final double LIFT_POWER = 0.5;
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int STOPPED = 3;
	
	public static final boolean CLOSED = false;
	public static final boolean OPEN = true;
	
	private int direction = STOPPED;
	public double power = 0;
	
	private MultiMotor motors;
	private Solenoid sol;
	private DigitalInput checkTop, checkBottom;
	
	private boolean motorsEnabled = false;
	private boolean solEnabled = false;
	private boolean topEnabled = false;
	private boolean bottomEnabled = false;
	
	private LiftSubsystem() {
		super("LiftSubsystem");
		
		if (RobotMap.Lift.LIFT_MOTOR_1 != RobotMap.DOES_NOT_EXIST &&
				RobotMap.Lift.LIFT_MOTOR_2 != RobotMap.DOES_NOT_EXIST){
			motors = new MultiMotor(
						new Victor[]{
								new Victor(RobotMap.Lift.LIFT_MOTOR_1),
								new Victor(RobotMap.Lift.LIFT_MOTOR_2)},
						new boolean[]{false, false}
					);
			motorsEnabled = true;
		}
		if (RobotMap.Lift.DIGITAL_INPUT_TOP != RobotMap.DOES_NOT_EXIST){
			checkTop = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_TOP);
			topEnabled = true;
		}
		if (RobotMap.Lift.DIGITAL_INPUT_BOTTOM != RobotMap.DOES_NOT_EXIST){
			checkBottom = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_BOTTOM);
			bottomEnabled = true;
		}
		if (RobotMap.Lift.LIFT_SOL != RobotMap.DOES_NOT_EXIST){
			sol = new Solenoid(RobotMap.Lift.LIFT_SOL);
			solEnabled = true;
		}
	}
	
	public static LiftSubsystem getInstance() {
		if (instance == null) {
			instance = new LiftSubsystem();
		}
		return instance;
	}
	
	public boolean isAtTop() {
		return topEnabled ? !checkTop.get() : true;
	}
	
	public boolean isAtBottom() {
		return bottomEnabled ? !checkBottom.get() : true;
	}
	
	public double getPower() {
		return motorsEnabled ? Math.abs(motors.get()) : 0;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setPower() {
		if (motorsEnabled)
			motors.set(power);
	}
	
	public void setLift(int dir, double power) {
		
		power = Math.max(Math.min(power, 1), 0);
		
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
		return solEnabled ? sol.get() : OPEN;
	}
	
	public void setPosition(boolean position) {
		if (solEnabled)
			sol.set(position);
	}

	public void updateSmartDashboard() {
		
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new RunLift());		
	}
}
