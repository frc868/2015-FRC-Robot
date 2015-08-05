package com.techhounds.subsystems;

import com.techhounds.MultiCANTalon;
import com.techhounds.MultiMotor;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.lift.RunLift;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Alex Fleig, Matt Simons, Ayon Mitra, Clayton Detke
 */
public class LiftSubsystem extends BasicSubsystem {	
	
	private static LiftSubsystem instance;
	
	public static final double THREE_TOTE_HEIGHT = 4.2;
	public static final double TWO_TOTE_HEIGHT = 2.8;
	public static final double ONE_TOTE_HEIGHT = 1.4;
	public static final double OFF_GROUND_HEIGHT = 0.2;
	public static final double MAX_HEIGHT = 20;
	
	public static final double LIFT_POWER = 1.0;
	public static final double COUNTS_TO_FEET = (32.25 / 12) / 5752.0;
	public static final double COUNTS_TO_FEET_PRACT = (24.0 / 12) / 497.0;
	public static final double UP_BRAKE_MULT = 10;
	public static final double DOWN_BRAKE_MULT = 4; 
			
	public enum Action {
		UP, DOWN, STOPPED
	}
	
	public static final boolean CLOSED = true, OPEN = false;
	public static final boolean BRAKE = false, UNBRAKE = true;
	
	public static boolean OP_STICK_CONTROL = true;
	public static boolean DRIVER_CONTROL = false;
	public static final boolean LIFT_BRAKING = false;
	
	private MultiCANTalon motors;
	private MultiMotor motorsPract;
	private Solenoid grabSol;
	private DigitalInput checkTop, checkBottom, checkPassive;
	private AnalogInput IRSensor;
	private Encoder enc;
	
	private double IRBadCount = 0;
	private double lastIRVal = 0;
	private double brakeMult = 10;
	private double brakeHeight = 0;
	private boolean braked = false;
	private double power = 0;
	private Action direction = Action.STOPPED;
	
	private boolean motorsEnabled, grabSolEnabled, topEnabled, bottomEnabled, encEnabled, IREnabled, passCheckEnabled;
	
	private LiftSubsystem() {
		super("LiftSubsystem");
		
		if (Robot.isFinal()){
			if (motorsEnabled = (RobotMap.Lift.MOTOR_1 != RobotMap.DOES_NOT_EXIST &&
					RobotMap.Lift.MOTOR_2 != RobotMap.DOES_NOT_EXIST)){
				motors = new MultiCANTalon(
						new CANTalon[]{
								new CANTalon(RobotMap.Lift.MOTOR_1),
								new CANTalon(RobotMap.Lift.MOTOR_2)},
						new boolean[]{false, false},
						FeedbackDevice.QuadEncoder,
						true, true, true, false, false);
				motors.setCountsPerFeet(1 / COUNTS_TO_FEET);
				motors.resetEnc();
				encEnabled = true;
				topEnabled = true;
				bottomEnabled = true;
			}
			
			
			
			if (grabSolEnabled = RobotMap.Lift.GRAB_SOL != RobotMap.DOES_NOT_EXIST)
				grabSol = new Solenoid(RobotMap.Lift.GRAB_SOL);
	
			if (IREnabled = RobotMap.Lift.IR_SENSOR != RobotMap.DOES_NOT_EXIST)
				IRSensor = new AnalogInput(RobotMap.Lift.IR_SENSOR);
			
			if (passCheckEnabled = RobotMap.Lift.PASSIVE_LIMIT != RobotMap.DOES_NOT_EXIST)
				checkPassive = new DigitalInput(RobotMap.Lift.PASSIVE_LIMIT);
		} else{
			if (motorsEnabled = (RobotMap.Lift.MOTOR_1_PRACT != RobotMap.DOES_NOT_EXIST &&
					RobotMap.Lift.MOTOR_2_PRACT != RobotMap.DOES_NOT_EXIST))
				motorsPract = new MultiMotor(
							new Victor[]{
								new Victor(RobotMap.Lift.MOTOR_1_PRACT),
								new Victor(RobotMap.Lift.MOTOR_2_PRACT)},
							new boolean[]{false, false}
						);
				
			if (topEnabled = RobotMap.Lift.DIGITAL_INPUT_TOP_PRACT != RobotMap.DOES_NOT_EXIST)
				checkTop = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_TOP_PRACT);
				
			if (bottomEnabled = RobotMap.Lift.DIGITAL_INPUT_BOTTOM_PRACT != RobotMap.DOES_NOT_EXIST)
				checkBottom = new DigitalInput(RobotMap.Lift.DIGITAL_INPUT_BOTTOM_PRACT);
			
			if (grabSolEnabled = RobotMap.Lift.GRAB_SOL_PRACT != RobotMap.DOES_NOT_EXIST)
				grabSol = new Solenoid(RobotMap.Lift.GRAB_SOL_PRACT);
			
			if (encEnabled = RobotMap.Lift.ENCODER_A_PRACT != RobotMap.DOES_NOT_EXIST){
				enc = new Encoder(RobotMap.Lift.ENCODER_A_PRACT, RobotMap.Lift.ENCODER_B_PRACT);
				enc.setDistancePerPulse(COUNTS_TO_FEET_PRACT);
			}
			
			if (IREnabled = RobotMap.Lift.IR_SENSOR_PRACT != RobotMap.DOES_NOT_EXIST)
				IRSensor = new AnalogInput(RobotMap.Lift.IR_SENSOR_PRACT);

			if (passCheckEnabled = RobotMap.Lift.PASSIVE_LIMIT_PRACT != RobotMap.DOES_NOT_EXIST)
				checkPassive = new DigitalInput(RobotMap.Lift.PASSIVE_LIMIT_PRACT);
		}
	}
	
	public static LiftSubsystem getInstance() {
		if (instance == null)
			instance = new LiftSubsystem();
		return instance;
	}
	
	public boolean isAtTop() {
		return topEnabled ? getPassiveSwitch() || (Robot.isFinal() ? motors.getBackwardSwitch() : !checkTop.get()) : true;
	}
	
	public boolean isAtBottom() {
		return bottomEnabled ? (Robot.isFinal() ? motors.getForwardSwitch() : !checkBottom.get()) : true;
	}
	
	public boolean getPassiveSwitch(){
		return passCheckEnabled ? (!checkPassive.get() && getGrabPosition() == CLOSED) : false;
	}
	
	public double getPower() {
		return motorsEnabled ? (Robot.isFinal() ? Math.abs(motors.get()) : Math.abs(motorsPract.get())) : 0;
	}
	
	public Action getDirection(){
		return direction;
	}
	
	public void setPower() {
		if ((isAtTop() && getDirection() == Action.UP) || (isAtBottom() && getDirection() == Action.DOWN))//should be redundant
			power = 0;
		
		if (!motorsEnabled)
			return;
		if (Robot.isFinal())
			motors.set(power);
		else
			motorsPract.set(power);
	}
	
	public void setLift(Action dir, double power) {
		
		power = Math.max(Math.min(power, 1), 0);
		
		if (dir == Action.UP){
			power *= -1;
		}else if (dir == Action.STOPPED){
			power = 0;
		}
		
		this.direction = dir;
		this.power = power;
	}
	
	public void stopLift() {
		setLift(Action.STOPPED, 0);
	}
	
	public boolean getGrabPosition() {
		return grabSolEnabled ? grabSol.get() : OPEN;
	}
	
	public void setGrabPosition(boolean position) {
		if (grabSolEnabled)
			grabSol.set(position);
	}
	
	public double getEncCount(){
		return encEnabled ? (Robot.isFinal() ? motors.getCount() : enc.get()) : 0;
	}
	
	public double getEncHeight(){
		return encEnabled ? (Robot.isFinal() ? motors.getDistance() : enc.getDistance()) : 0;
	}
	
	public void resetEncHeight(){
		if (!encEnabled)
			return;
		if (Robot.isFinal())
			motors.resetEnc();
		else
			enc.reset();
	}
	
	public boolean getBraked(){
		return braked;
	}
	
	public void setBrake(boolean isBraked){
		braked = isBraked;
		if (braked){
			brakeHeight = getEncHeight();
			if (getDirection() == Action.DOWN){
				brakeHeight -= 1;
				setBrakeMult(DOWN_BRAKE_MULT);
			}else if (getDirection() == Action.UP)
				setBrakeMult(UP_BRAKE_MULT);
		}
	}
	
	public double getBrakeHeight(){
		return brakeHeight;
	}
	
	public void setBrakeHeight(double height){
		brakeHeight = height;
	}

	public double getBrakeMult(){
		return brakeMult;
	}
	
	public void setBrakeMult(double mult){
		brakeMult = mult;
	}
	
	public double getIRDist(){
		return IREnabled ? distance(IRSensor.getVoltage()) : 0;
	}
	
	public double getIRAvgDist(){
		if (IREnabled){
			double val = distance(IRSensor.getVoltage());
			if (val / lastIRVal > 1.25 || val / lastIRVal < .75){
				val = lastIRVal;
				IRBadCount++;
			}else
				IRBadCount = 0;
			
			if (IRBadCount > 3){
				IRBadCount = 0;
				return lastIRVal = distance(IRSensor.getVoltage());
			}else
				return val;
		}
		return 0;
	}
	
	private double distance(double volts){
		return 1.3958 * Math.pow(volts, 4) +
				-13.74 * Math.pow(volts, 3) +
				49.609 * Math.pow(volts, 2) +
				-80.118 * volts +
				53.37;
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Lift Enc Height", Robot.instance.isEnabled() ? Math.max(getEncHeight(), 0) : -1);// <---- IMPORTANT! Needed to send to opBoard
		SmartDashboard.putNumber("Lift Brake Height", getBrakeHeight());
		SmartDashboard.putNumber("Lift IR Dist", getIRDist());

		SmartDashboard.putBoolean("Lift is Bottom", isAtBottom());
		SmartDashboard.putBoolean("Lift is Top", isAtTop());
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new com.techhounds.commands.lift.RunLift());		
	}
}
