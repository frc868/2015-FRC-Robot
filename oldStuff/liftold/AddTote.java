package com.techhounds.commands.liftold;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AddTote extends CommandGroup {

	public AddTote() {
		
//		addSequential(new SetLift(LiftSubsystem.DOWN));
//		addSequential(new WaitCommand(.1));
		addSequential(new SetLiftHeight(.8, true));
		addSequential(new SetLift(LiftSubsystem.OPEN));
		addSequential(new SetLift(LiftSubsystem.DOWN));
		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
		addSequential(new WaitCommand(.15));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.1));
		addSequential(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
	}
}
