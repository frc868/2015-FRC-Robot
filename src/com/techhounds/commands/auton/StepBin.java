package com.techhounds.commands.auton;

import com.techhounds.commands.GoFishing;
import com.techhounds.subsystems.FishingPoleSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StepBin extends CommandGroup {
    
    public  StepBin() {
    	
    	addSequential(new GoFishing(FishingPoleSubsystem.OUT));
//    	addSequential(new WaitCommand(4));
//    	addSequential(new AutonDrive(5, 2));
    	
    }
}