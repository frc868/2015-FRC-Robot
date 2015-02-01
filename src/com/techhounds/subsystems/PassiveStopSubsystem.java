package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PassiveStopSubsystem extends BasicSubsystem {
    
	private static PassiveStopSubsystem instance;
	
	public static boolean STOP = true;
	public static boolean OPEN = false;
	
	private boolean solEnabled = false;
	
	private Solenoid sol;
	
	public static PassiveStopSubsystem getInstance(){
		if (instance == null)
			instance = new PassiveStopSubsystem();
		return instance;
	}
	
	private PassiveStopSubsystem(){
		if (solEnabled = RobotMap.Lift.PASSIVE_STOP_SOL != RobotMap.DOES_NOT_EXIST){
			sol = new Solenoid(RobotMap.Lift.PASSIVE_STOP_SOL);
		}
	}
	
	public void setPosition(boolean position){
		if (solEnabled)
			sol.set(position);
	}
	
	public boolean getPosition(){
		return solEnabled ? sol.get() : OPEN;
	}
	
	public void updateSmartDashboard() {
		
	}
	
    public void initDefaultCommand() {

    }
}

