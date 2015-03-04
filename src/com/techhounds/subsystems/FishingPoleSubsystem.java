package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *	-------------- This Subsystem is FINAL Robot ONLY! ------------------------
 */
public class FishingPoleSubsystem extends BasicSubsystem {
    
	private static FishingPoleSubsystem instance;
	
	public static boolean OUT = true, IN = false;
	
	private boolean solEnabled;
	
	private Solenoid sol;
	
	public static FishingPoleSubsystem getInstance(){
		if (instance == null)
			instance = new FishingPoleSubsystem();
		return instance;
	}

	private FishingPoleSubsystem(){
		if (!Robot.isFinal()){
			solEnabled = false;
			return;
		}
		
		if (solEnabled = RobotMap.FishingPole.SOL != RobotMap.DOES_NOT_EXIST)
			sol = new Solenoid(RobotMap.FishingPole.SOL);
	}
	
	public void setPos(boolean pos){
		if (solEnabled)
			sol.set(pos);
	}
	
	public boolean getPos(){
		return solEnabled ? sol.get() : IN;
	}
	
    public void initDefaultCommand() {
    	
    }

	public void updateSmartDashboard() {
		
	}
}

