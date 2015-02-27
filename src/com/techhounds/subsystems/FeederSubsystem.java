package com.techhounds.subsystems;

import com.techhounds.MultiCANTalon;
import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class FeederSubsystem extends Subsystem {
    
	/*
	 *-----------------This Subsystem is Final Robot ONLY!---------------------------
	 */
	
	private static FeederSubsystem instance;
	
	public static final double FEED_IN = -0.75, FEED_OUT = 0.75, STOPPED = 0;
	public static final boolean OPEN = false, CLOSED = true;
	public static final double MIN_RIGHT_DIST = 1, MIN_LEFT_DIST = 1;
	
	private double leftMotorMult = 1, rightMotorMult = 1;
	private boolean solEnabled, motorsEnabled, leftEnabled, rightEnabled;
	
	private MultiCANTalon motors;
	private AnalogInput left, right;
	private Solenoid sol;
	
	private FeederSubsystem() {
		super("ArmsSubsystem");
		
		if (!Robot.isFinal())
			return;
					
		if (motorsEnabled = (RobotMap.Feeder.LEFT_MOTOR != RobotMap.DOES_NOT_EXIST && 
				RobotMap.Feeder.RIGHT_MOTOR != RobotMap.DOES_NOT_EXIST)){
			motors = new MultiCANTalon(
					new CANTalon[]{
						new CANTalon(RobotMap.Feeder.LEFT_MOTOR),
						new CANTalon(RobotMap.Feeder.RIGHT_MOTOR)},
					new boolean[]{true, true},
					FeedbackDevice.QuadEncoder,
					false, false, false, false, false);
		}
				
		if(solEnabled = RobotMap.Feeder.SOL != RobotMap.DOES_NOT_EXIST)
			sol = new Solenoid(RobotMap.Feeder.SOL);
		
		if(leftEnabled = RobotMap.Feeder.LEFT_SENSOR != RobotMap.DOES_NOT_EXIST)
			left = new AnalogInput(RobotMap.Feeder.LEFT_SENSOR);
		
		if(rightEnabled = RobotMap.Feeder.RIGHT_SENSOR != RobotMap.DOES_NOT_EXIST)
			right = new AnalogInput(RobotMap.Feeder.RIGHT_SENSOR);
	}
	
	public static FeederSubsystem getInstance() {
		if (instance == null)
			instance = new FeederSubsystem();
		return instance;
	}
		
	public double getPower() {
		return motorsEnabled ? motors.get() : 0;
	}
	
	public void setPower(double power) {
		if (motorsEnabled)
			motors.set(power);
	}
	
	public boolean getPosition() {
		return solEnabled ? sol.get() : OPEN;
	}
	
	public void setPosition(boolean direction) {
		if (solEnabled)
			sol.set(direction);
	}
	
	public double getLeftSensor() {
		return leftEnabled ? left.getVoltage() : MIN_LEFT_DIST;
	}
	
	public double getRightSensor() {
		return rightEnabled ? right.getVoltage() : MIN_RIGHT_DIST;
	}
	
	public double getLeftDistance() {
		return getLeftSensor();
	}
	
	public double getRightDistance(){
		return getRightSensor();
	}
	
	public boolean getRightSensorInRange(){
		return getRightDistance() < MIN_RIGHT_DIST;
	}
	
	public boolean getLeftSensorInRange(){
		return getLeftDistance() < MIN_LEFT_DIST;
	}
	
	public void stopArms() {
		setPower(0);
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Left Feeder Sensor", getLeftSensor());
		SmartDashboard.putNumber("Right Feeder Sensor", getRightSensor());
//		SmartDashboard.putBoolean("Feeder Solenoid In", !getPosition());
//		SmartDashboard.putString("Feeder Direction", getPower() > 0 ? "OUT" : getPower() == 0 ? "STOPPED" : "IN");
	}

    public void initDefaultCommand() {
        
    }
}

