package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraSubsystem extends BasicSubsystem {

	private static CameraSubsystem instance;
	
	public static CameraSubsystem getInstance(){
		if (instance == null)
			instance = new CameraSubsystem();
		return instance;
	}
	
	private CameraSubsystem(){
		super("CameraSubsystem");
	}
	
	public double getOffsetValue(){
		return SmartDashboard.getNumber("Tote Offset Percentage", 0);
	}
	
	public double getDistValue(){
		return SmartDashboard.getNumber("Tote Dist Factor", 0);
	}
	
    public void initDefaultCommand() {

    }

	public void updateSmartDashboard() {
		
	}
}

