package com.techhounds.commands.auton;

import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class NextTote extends CommandGroup {
    
    public NextTote(boolean finalTote) {
    	
    	addParallel(new DriveToClosestTote(1));
    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new WaitForIR(6, 2, true, 1));
    	
    	addSequential(new SetLift(LiftSubsystem.OPEN));
    	addSequential(new SetLift(LiftSubsystem.DOWN));
    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    	
    	addSequential(new WaitCommand(.3));
    	addSequential(new SetLift(LiftSubsystem.CLOSED));
    	addSequential(new WaitCommand(.3));
    	if (finalTote)
    		addSequential(new SetLiftHeight(.2));
    	else
    		addSequential(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
    }
}
