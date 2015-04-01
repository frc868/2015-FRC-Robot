package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class CompSubsystem extends Subsystem {
	
	private static CompSubsystem instance;
	
	private Compressor comp;
	
	@SuppressWarnings("unused")
	private boolean compEnabled;
	
	private CompSubsystem() {
		super("CompSubsystem");
		
		if(Robot.isFinal()){
			if (compEnabled = RobotMap.Compressor.COMP != RobotMap.DOES_NOT_EXIST){
				comp = new Compressor(RobotMap.Compressor.COMP);
				comp.start();
			}
		}else{
			if (compEnabled = RobotMap.Compressor.COMP_PRACT != RobotMap.DOES_NOT_EXIST){
				comp = new Compressor(RobotMap.Compressor.COMP_PRACT);
				comp.start();
			}
		}
	}
	
	public static CompSubsystem getInstance() {
		if (instance == null) 
			instance = new CompSubsystem();
		return instance;
	}
	
	public void updateSmartDashboard() {
//		SmartDashboard.putBoolean("Compressor Running", comp.enabled());
	}

    public void initDefaultCommand() {
       
    }
}

