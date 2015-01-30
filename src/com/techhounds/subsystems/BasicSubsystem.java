package com.techhounds.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * @author Atif Niyaz
 */
public abstract class BasicSubsystem extends Subsystem {
	
	public static ArrayList<BasicSubsystem> subsystems = new ArrayList<>();
	
	public BasicSubsystem() { 
		super();
		subsystems.add(this);
	}
	
	public BasicSubsystem(String name) {
		super(name);
		subsystems.add(this);
	}
	
	public abstract void updateSmartDashboard();
}
