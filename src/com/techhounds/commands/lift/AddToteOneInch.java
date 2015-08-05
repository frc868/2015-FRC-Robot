package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AddToteOneInch extends CommandGroup {

	public AddToteOneInch() {
		
		addSequential(new SetLift(LiftSubsystem.Action.DOWN));
		addSequential(new WaitCommand(.1));
		addSequential(new SetLift(LiftSubsystem.OPEN));
		addSequential(new WaitForLiftSwitch(LiftSubsystem.Action.DOWN));
		addSequential(new WaitCommand(.15));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new SetLiftHeight(.2));
	}
}
