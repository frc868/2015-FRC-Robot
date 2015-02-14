package com.techhounds.commands.auton;

import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.ManualTurn;
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
public class OneTote extends CommandGroup {
    
    public  OneTote() {
    	
    	//grab first tote
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.3));
		
		//lift first tote and turn and move
		addParallel(new SetLiftHeight(LiftSubsystem.OFF_GROUND_HEIGHT));
		addSequential(new ManualTurn(.6, .9, false));
		addSequential(new MoveToAutoZone(1.75, .25));
    }
}
