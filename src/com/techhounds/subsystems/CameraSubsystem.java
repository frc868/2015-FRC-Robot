package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraSubsystem extends BasicSubsystem {

	private static CameraSubsystem instance;
	private boolean enabled;
	
	public static CameraSubsystem getInstance(){
		if (instance == null)
			instance = new CameraSubsystem();
		return instance;
	}
	
	private CameraSubsystem(){
		super("CameraSubsystem");
		
		enabled = RobotMap.CAMERA_ENABLED != RobotMap.DOES_NOT_EXIST;
	}
	
	public double getOffsetValue(){
		return enabled ? SmartDashboard.getNumber("Tote Offset Percentage", 0) : 0;
	}
	
	public double getDistValue(){
		return enabled ? SmartDashboard.getNumber("Tote Dist Factor", 0) : 0;
	}
	
    public void initDefaultCommand() {

    }

	public void updateSmartDashboard() {
	}
}

