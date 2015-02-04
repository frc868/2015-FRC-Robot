package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class CompSubsystem extends Subsystem {
	
	private static CompSubsystem instance;
	
	private Compressor comp;
	
	private boolean compEnabled;
	
	private CompSubsystem() {
		super("CompSubsystem");
		
		if (compEnabled = RobotMap.Compressor.COMP != RobotMap.DOES_NOT_EXIST){
			comp = new Compressor(RobotMap.Compressor.COMP);
			comp.start();
		}
	}
	
	public static CompSubsystem getInstance() {
		if (instance == null) 
			instance = new CompSubsystem();
		return instance;
	}
	
	public void updateSmartDashboard() {
		
	}

    public void initDefaultCommand() {
       
    }
}

