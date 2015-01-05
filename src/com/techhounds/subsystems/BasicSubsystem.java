package com.techhounds.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * @author Atif Niyaz, Alex Fleig, MAAAAAAATEOOOOOOO, Shrek ILIL, John Yin Yang
 */
public abstract class BasicSubsystem extends Subsystem {
	
	public static ArrayList<BasicSubsystem> subsystems;
	
	public BasicSubsystem() { //this is a comment
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

	public abstract void updateSmartDashboard();
}
