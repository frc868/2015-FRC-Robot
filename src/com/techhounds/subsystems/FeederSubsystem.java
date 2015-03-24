package com.techhounds.subsystems;

import com.techhounds.MultiCANTalon;
import com.techhounds.MultiMotor;
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


public class FeederSubsystem extends BasicSubsystem {
    
	/*
	 *
	 */
	
	private static FeederSubsystem instance;
	
	public static final double FEED_IN = -0.75, FEED_OUT = 0.75, STOPPED = 0;
	public static final boolean OPEN = false, CLOSED = true;
	public static final double MIN_RIGHT_VOLTS = 1.7, MIN_LEFT_VOLTS = 1.7;
	
	private double leftMotorMult = 1, rightMotorMult = 1;
	private boolean solEnabled, motorsEnabled, leftEnabled, rightEnabled;
	
	private MultiMotor motorsPract;
	private MultiCANTalon motors;
	private AnalogInput left, right;
	private Solenoid sol;
	
	private FeederSubsystem() {
		super("ArmsSubsystem");
					
		if (Robot.isFinal()){
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
		}else{
			if (motorsEnabled = (RobotMap.Feeder.LEFT_MOTOR_PRACT != RobotMap.DOES_NOT_EXIST && 
					RobotMap.Feeder.RIGHT_MOTOR_PRACT != RobotMap.DOES_NOT_EXIST)){
				motorsPract = new MultiMotor(
						new SpeedController[]{
							new Victor(RobotMap.Feeder.LEFT_MOTOR_PRACT),
							new Victor(RobotMap.Feeder.RIGHT_MOTOR_PRACT)},
						new boolean[]{true, true});
			}
					
			if(solEnabled = RobotMap.Feeder.SOL_PRACT != RobotMap.DOES_NOT_EXIST)
				sol = new Solenoid(RobotMap.Feeder.SOL_PRACT);
			
			if(leftEnabled = RobotMap.Feeder.LEFT_SENSOR_PRACT != RobotMap.DOES_NOT_EXIST)
				left = new AnalogInput(RobotMap.Feeder.LEFT_SENSOR_PRACT);
			
			if(rightEnabled = RobotMap.Feeder.RIGHT_SENSOR_PRACT != RobotMap.DOES_NOT_EXIST)
				right = new AnalogInput(RobotMap.Feeder.RIGHT_SENSOR_PRACT);
		}
	}
	
	public static FeederSubsystem getInstance() {
		if (instance == null)
			instance = new FeederSubsystem();
		return instance;
	}
		
	public double getPower() {
		return motorsEnabled ? (Robot.isFinal() ? motors.get() : motorsPract.get()) : 0;
	}
	
	public void setPower(double power) {
		if (!motorsEnabled)
			return;
		if (Robot.isFinal())
			motors.set(power);
		else
			motorsPract.set(power);
	}
	
	public boolean getPosition() {
		return solEnabled ? sol.get() : OPEN;
	}
	
	public void setPosition(boolean direction) {
		if (solEnabled)
			sol.set(direction);
	}
	
	public double getLeftSensor() {
		return leftEnabled ? left.getVoltage() : MIN_LEFT_VOLTS;
	}
	
	public double getRightSensor() {
		return rightEnabled ? right.getVoltage() : MIN_RIGHT_VOLTS;
	}
	
	public double getLeftDistance() {
		double vol = getLeftSensor();

		if (Robot.isFinal())
			return -0.0046 * Math.pow(vol, 4) + 0.669 * Math.pow(vol, 3) - 0.2921 * Math.pow(vol, 2) + 0.1078 * vol + 2;
		else
			return -0.0046 * Math.pow(vol, 4) + 0.669 * Math.pow(vol, 3) - 0.2921 * Math.pow(vol, 2) + 0.1078 * vol + 2;
	}
	
	public double getRightDistance(){
		double vol = getRightSensor();

		if (Robot.isFinal())
			return -Math.pow(5, -15) * Math.pow(vol, 4) - 0.0037 * Math.pow(vol, 3) + 
				0.0778 * Math.pow(vol, 2) - 0.555 * vol + 1.9968;
		else
			return -Math.pow(5, -15) * Math.pow(vol, 4) - 0.0037 * Math.pow(vol, 3) + 
					0.0778 * Math.pow(vol, 2) - 0.555 * vol + 1.9968;
	}
	
	public boolean getRightSensorInRange(){
//		return getRightDistance() < MIN_RIGHT_DIST;
		if (Robot.isFinal())
			return getRightSensor() > MIN_RIGHT_VOLTS;
		else
			return getRightSensor() > MIN_RIGHT_VOLTS;
	}
	
	public boolean getLeftSensorInRange(){
//		return getLeftDistance() < MIN_LEFT_DIST;
		if(Robot.isFinal())
			return getLeftSensor() > MIN_LEFT_VOLTS;
		else
			return getLeftSensor() > MIN_LEFT_VOLTS;
	}
	
	public void stopArms() {
		setPower(0);
	}
	
	public void updateSmartDashboard() {
//		SmartDashboard.putNumber("Left Feeder Sensor", getLeftSensor());
//		SmartDashboard.putNumber("Right Feeder Sensor", getRightSensor());
//		SmartDashboard.putNumber("Left Feeder Sensor Dist", getLeftDistance());
//		SmartDashboard.putNumber("Right Feeder Sensor Dist", getRightDistance());
//		SmartDashboard.putBoolean("Collector Out", getPosition());
//		SmartDashboard.putString("Feeder Direction", getPower() > 0 ? "OUT" : getPower() == 0 ? "STOPPED" : "IN");
	}

    public void initDefaultCommand() {
        
    }
}