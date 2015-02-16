package com.techhounds;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;

public class MultiCANTalon implements SpeedController{

	private CANTalon[] motors;
	private double COUNTS_PER_FEET;
	private boolean[] inverted;
	private boolean encInverted;
	
	public MultiCANTalon(CANTalon[] motors, boolean[] inverted, FeedbackDevice enc, boolean encReversed, boolean forwardSwitch, boolean backSwitch, boolean forwardReversed, boolean backReversed){
		this.motors = motors;
		this.inverted = inverted;
		encInverted = encReversed;
		motors[0].enableBrakeMode(true);
		motors[0].reverseOutput(inverted[0]);
		motors[0].enableLimitSwitch(forwardSwitch, backSwitch);
		motors[0].ConfigFwdLimitSwitchNormallyOpen(!forwardReversed);
		motors[0].ConfigRevLimitSwitchNormallyOpen(!backReversed);
		motors[0].setFeedbackDevice(enc);
		motors[0].reverseSensor(encReversed);
		motors[0].changeControlMode(ControlMode.PercentVbus);
		for (int i = 1; i < motors.length; i++){
			motors[i].changeControlMode(CANTalon.ControlMode.Follower);
			motors[i].set(motors[0].getDeviceID());
			motors[i].reverseOutput(inverted[i]);
		}
	}
	
	public void pidWrite(double output) {
		set(output);
	}

	public double get() {
		return inverted[0] ? -motors[0].getOutputVoltage() / motors[0].getBusVoltage() :
			motors[0].getOutputVoltage() / motors[0].getBusVoltage();
	}

	public void set(double power, byte syncGroup) {
		set(power);
	}

	public void set(double power) {
		motors[0].changeControlMode(ControlMode.PercentVbus);
		power = Math.max(Math.min(power, 1), -1);
		power = inverted[0] ? -power : power;
		motors[0].set(power);
	}

	public void disable() {
		motors[0].disable();
	}
	
	public void setBrakeMode(boolean braked){
		for (int i = 0; i < motors.length; i++){
			motors[i].enableBrakeMode(braked);
		}
	}
	
	public void setPID(double P, double I, double D){
		motors[0].changeControlMode(ControlMode.Position);
		motors[0].setPID(P, I, D);
	}
	
	public void setPIDSetpoint(double setpoint){
		motors[0].changeControlMode(ControlMode.Position);
		motors[0].set(setpoint * COUNTS_PER_FEET);
	}
	
	public double getPIDSetpoint(){
		return motors[0].getSetpoint();
	}
	
	public boolean getForwardSwitch(){
		return motors[0].isFwdLimitSwitchClosed();
	}
	
	public boolean getBackwardSwitch(){
		return motors[0].isRevLimitSwitchClosed();
	}
	
	public void setCountsPerFeet(double val){
		COUNTS_PER_FEET = val;
	}
	
	public double getCount(){
		return encInverted ? -motors[0].getEncPosition() : motors[0].getEncPosition();
	}
	
	public double getDistance(){
		return getCount() / COUNTS_PER_FEET;
	}
	
	public double getSpeed(){
		return encInverted ? -motors[0].getEncVelocity() / COUNTS_PER_FEET : motors[0].getEncVelocity() / COUNTS_PER_FEET;
	}
	
	public void setEncDistance(double dist){
		if (encInverted)
			motors[0].setPosition(-dist * COUNTS_PER_FEET);
		else
			motors[0].setPosition(dist * COUNTS_PER_FEET);
	}
	
	public void resetEnc() {
		setEncDistance(0);
	}
}