package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Alex Fleig, Matt Simones, Ayon Mitra, Clayton Detke, Adam Foster
 */
public class BinSubsystem extends Subsystem {
    
	public static BinSubsystem instance;
	
	private DigitalInput checkTop, checkBottom;
	
	static Talon motor;
	Solenoid sol;
	
	public static final int STOPPED = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	
	public static final boolean OPEN = true;
	public static final boolean CLOSED = false;
	
	public static int direction;
	public static double power;
	
	public BinSubsystem() {
		motor = new Talon(RobotMap.Bin.BIN_MOTOR);
		sol = new Solenoid(RobotMap.Bin.BIN_SOL);
		checkTop = new DigitalInput(RobotMap.Bin.BIN_TOP_CHECK);
		checkBottom = new DigitalInput(RobotMap.Bin.BIN_DOWN_CHECK);
	}
	
	public static BinSubsystem getInstance() {
		if(instance == null) instance = new BinSubsystem();
		return instance;
	}
	
	public double getPower() {
		return motor.get();
	}
	
	public void setPower(double power) {
		motor.set(Math.max(Math.min(power, 1), 0));
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setBin(int dir, double power) {
		power = Math.max(Math.min(power, 1), 0);
		if(dir == UP) {
			power *= -1;
		} else if (dir == STOPPED) {
			power = 0;
		}
		
		direction = dir;
	}
	
	public void setSolenoid(boolean toggle) {
		sol.set(toggle);
	}
	
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
}

