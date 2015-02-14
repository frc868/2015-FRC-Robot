package com.techhounds.subsystems;

import com.techhounds.RobotMap;
import com.techhounds.gyro.GyroItg3200;
import com.techhounds.gyro.RotationTracker;
import com.techhounds.gyro.RotationTrackerInverted;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroSubsystem extends BasicSubsystem {

	private static GyroSubsystem instance;
	
	private GyroItg3200 gyro;
	private RotationTracker tilt, lean, rotation;
	
	private GyroSubsystem() {
		gyro = new GyroItg3200(RobotMap.Gyro.GYRO, false);
		
		tilt = getTiltTracker();
		lean = getLeanTracker();
		rotation = getRotationTracker();
	}
	
	public static GyroSubsystem getInstance() {
		if(instance == null)
			instance = new GyroSubsystem();
		return instance;
	}
	
	@Override
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Tilt", tilt.getAngle());
		SmartDashboard.putNumber("Lean", lean.getAngle());
		SmartDashboard.putNumber("Rotation", rotation.getAngle());
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public double getTilt() {
		return tilt.getAngle();
	}
	
	public double getLean() {
		return lean.getAngle();
	}
	
	public double getRotation() {
		return rotation.getAngle();
	}
	
	public RotationTracker getTiltTracker() {
		return gyro.getRotationZ();
	}
	
	public RotationTracker getLeanTracker() {
		return gyro.getRotationY();
	}
	
	public RotationTracker getRotationTracker() {
		return new RotationTrackerInverted(gyro.getRotationX());
	}
	
	public void resetGyro() {
		gyro.reset();
	}
}
