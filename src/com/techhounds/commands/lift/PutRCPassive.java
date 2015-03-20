package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;
import com.techhounds.subsystems.PassiveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PutRCPassive extends CommandGroup {

	public PutRCPassive() {
		addSequential(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
		addSequential(new SetLiftHeight((LiftSubsystem.ONE_TOTE_HEIGHT + LiftSubsystem.TWO_TOTE_HEIGHT) / 2));
		addSequential(new WaitCommand(.2));
		addSequential(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));
		
	}
}
