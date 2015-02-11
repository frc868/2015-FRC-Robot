package com.techhounds.commands.auton;

import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoTote extends CommandGroup {

	public TwoTote() {
		
		//grab first tote
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.3));
		
		//lift first tote and drive to second tote
		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new WaitCommand(.3));
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true, 1));
    	
		//move lift down to second tote
    	addSequential(new SetLift(LiftSubsystem.OPEN));
    	addSequential(new SetLift(LiftSubsystem.DOWN));
    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    	
    	//grab second tote and lift a little
    	addSequential(new SetLift(LiftSubsystem.CLOSED));
    	addSequential(new WaitCommand(.3));
    	addSequential(new SetLiftHeight(.2));
		
	}
}
