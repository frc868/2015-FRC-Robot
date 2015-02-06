package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.lift.RunLift;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Alex Fleig, Matt Simons, Ayon Mitra, Clayton Detke
 */
public class LiftSubsystem extends BasicSubsystem {	
	
	private static LiftSubsystem instance;
	
	public static final double LIFT_POWER = 0.5;
	public static final double COUNT_TO_FEET = (24.0 / 12) / 497.0;
	public static final double IRFactor = 1;
	
	public static final int UP = 1, DOWN = 2, STOPPED = 3;
	public static final boolean CLOSED = true, OPEN = false;
	public static final boolean BRAKE = false, UNBRAKE = true;
	
	private MultiMotor motors;
	private Solenoid grabSol, brakeSol;
	private DigitalInput checkTop, checkBottom;
	private AnalogInput IRSensor;
	private Encoder enc;
	
	private double brakeHeight = 0;
	private boolean braked = false;
	private double power = 0;
	private int direction = STOPPED;
	private boolean motorsEnabled, grabSolEnabled, brakeSolEnabled, topEnabled, bottomEnabled, encEnabled, IREnabled;
	
	private LiftSubsystem() {
		super("LiftSubsystem");
		
		if (motorsEnabled = (RobotMap.Lift.MOTOR_1 != RobotMap.DOES_NOT_EXIST &&
				RobotMap.Lift.MOTOR_2 != RobotMap.DOES_NOT_EXIST))
			motors = new MultiMotor(
						new Victor[]{
							new Victor(RobotMap.Lift.MOTOR_1),
							new Victor(RobotMap.Lift.MOTOR_2)},
						new boolean[]{false, false}
					);
			
		if (topEnabled = RobotMap.Lift.DIGITAL_INPUT_TOP != RobotMap.DOES_NOT_EXIST)
			checkTop = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_TOP);
			
		if (bottomEnabled = RobotMap.Lift.DIGITAL_INPUT_BOTTOM != RobotMap.DOES_NOT_EXIST)
			checkBottom = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_BOTTOM);
		
		if (grabSolEnabled = RobotMap.Lift.GRAB_SOL != RobotMap.DOES_NOT_EXIST)
			grabSol = new Solenoid(RobotMap.Lift.GRAB_SOL);

		if (brakeSolEnabled = RobotMap.Lift.BRAKE_SOL != RobotMap.DOES_NOT_EXIST)
			brakeSol = new Solenoid(RobotMap.Lift.BRAKE_SOL);
		
		if (encEnabled = RobotMap.Lift.ENCODER_A != RobotMap.DOES_NOT_EXIST){
			enc = new Encoder(RobotMap.Lift.ENCODER_A, RobotMap.Lift.ENCODER_B);
			enc.setDistancePerPulse(COUNT_TO_FEET);
		}
		
		if (IREnabled = RobotMap.Lift.IRSensor != RobotMap.DOES_NOT_EXIST)
			IRSensor = new AnalogInput(RobotMap.Lift.IRSensor);
	}
	
	public static LiftSubsystem getInstance() {
		if (instance == null)
			instance = new LiftSubsystem();
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
		if ((isAtTop() && getDirection() == UP) || (isAtBottom() && getDirection() == DOWN))//should be redundant
			power = 0;
		
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
	
	public boolean getGrabPosition() {
		return grabSolEnabled ? grabSol.get() : OPEN;
	}
	
	public void setGrabPosition(boolean position) {
		if (grabSolEnabled)
			grabSol.set(position);
	}

	public boolean getBrakePosition(){
		return brakeSolEnabled ? brakeSol.get() : UNBRAKE;
	}
	
	public void setBrakePosition(boolean pos){
		if (brakeSolEnabled)
			brakeSol.set(pos);
	}
	
	public double getEncCount(){
		return encEnabled ? enc.get() : 0;
	}
	
	public double getEncHeight(){
		return encEnabled ? enc.getDistance() : 0;
	}
	
	public void resetEncHeight(){
		if (encEnabled)
			enc.reset();
	}
	
	public boolean getBraked(){
		return braked;
	}
	
	public void setBrake(boolean isBraked){
		braked = isBraked;
		if (braked){
			brakeHeight = getEncHeight();
			if (getDirection() == DOWN)
				brakeHeight -= .1; 
		}
	}
	
	public double getBrakeHeight(){
		return brakeHeight;
	}
	
	public void setBrakeHeight(double height){
		brakeHeight = height;
	}
	
	public double getIRDist(){
		return IREnabled ? IRSensor.getVoltage() * IRFactor : 0;
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Lift Enc Count", getEncCount());
		SmartDashboard.putNumber("Lift Enc Height", getEncHeight());
		SmartDashboard.putBoolean("Lift Top Switch", isAtTop());
		SmartDashboard.putBoolean("Lift Bottom Switch", isAtBottom());
		SmartDashboard.putBoolean("Lift isBraked", getBraked());
		SmartDashboard.putNumber("Lift Brake Height", getBrakeHeight());
		SmartDashboard.putNumber("Lift Power", getPower());
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new RunLift());		
	}
}
