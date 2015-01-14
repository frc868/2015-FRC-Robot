package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class CompSubsystem extends Subsystem {
	
	private static CompSubsystem instance;
	
	private Compressor comp;
	
	public static CompSubsystem getInstance() {
		if (instance == null) {
			
		}
	}

    public void initDefaultCommand() {
       
    }
}

