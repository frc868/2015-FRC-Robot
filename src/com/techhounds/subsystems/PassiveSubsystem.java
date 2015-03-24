package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PassiveSubsystem extends BasicSubsystem {
    
	private static PassiveSubsystem instance;
	
	public static boolean STOPPED = true, FREE = false;
	public static boolean OPEN = true, CLOSED = false;
	
	private boolean stopSolEnabled, armSolEnabled;
	
	private Solenoid stopSol, armSol;
	
	public static PassiveSubsystem getInstance(){
		if (instance == null)
			instance = new PassiveSubsystem();
		return instance;
	}
	
	private PassiveSubsystem(){
	
		if (Robot.isFinal()){
			if (stopSolEnabled = RobotMap.Lift.PASSIVE_STOP_SOL != RobotMap.DOES_NOT_EXIST){
				stopSol = new Solenoid(RobotMap.Lift.PASSIVE_STOP_SOL);
			}
			if (armSolEnabled = RobotMap.Lift.PASSIVE_ARM_SOL != RobotMap.DOES_NOT_EXIST){
				armSol = new Solenoid(RobotMap.Lift.PASSIVE_ARM_SOL);
			}
		}else{
			if (stopSolEnabled = RobotMap.Lift.PASSIVE_STOP_SOL_PRACT != RobotMap.DOES_NOT_EXIST){
				stopSol = new Solenoid(RobotMap.Lift.PASSIVE_STOP_SOL_PRACT);
			}
			if (armSolEnabled = RobotMap.Lift.PASSIVE_ARM_SOL_PRACT != RobotMap.DOES_NOT_EXIST){
				armSol = new Solenoid(RobotMap.Lift.PASSIVE_ARM_SOL_PRACT);
			}
		}
	}
	
	public void setStopPosition(boolean position){
		if (stopSolEnabled)
			stopSol.set(position);
	}
	
	public boolean getStopPosition(){
		return stopSolEnabled ? stopSol.get() : OPEN;
	}
	
	public void setArmPosition(boolean position){
		if (armSolEnabled)
			armSol.set(position);
	}
	
	public boolean getArmPosition(){
		return armSolEnabled ? armSol.get() : OPEN;
	}
	
	public void updateSmartDashboard() {
//		SmartDashboard.putBoolean("Passive Arms Out", getArmPosition());
		SmartDashboard.putBoolean("Passive Brake Engaged", getStopPosition());
	}
	
    public void initDefaultCommand() {

    }
}

