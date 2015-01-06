package com.techhounds.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * @author Atif Niyaz
 */
public abstract class BasicSubsystem extends Subsystem {
	
	public static ArrayList<BasicSubsystem> subsystems;
	
	public BasicSubsystem() { 
		super();
		addToArray();
	}
	
	public BasicSubsystem(String name) {
		super(name);
		addToArray();
	}
	
	private void addToArray() {
		
		if(subsystems == null)
			subsystems = new ArrayList<>();
		
		subsystems.add(this);
	}

	/** 
	 * This method is called repeatedly updating the SmartDashboard 
	 */
	public abstract void updateSmartDashboard();
}
