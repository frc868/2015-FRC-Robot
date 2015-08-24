package com.techhounds.commands.auton;

import com.techhounds.commands.feeder.SetFeederNormal;
import com.techhounds.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class KeepToteIn extends CommandGroup {

	public KeepToteIn() {
		
		addSequential(new SetFeederNormal(FeederSubsystem.CLOSED));
		addSequential(new SetFeederNormal(FeederSubsystem.FEED_IN));
		addSequential(new WaitCommand(1));
		addSequential(new SetFeederNormal(FeederSubsystem.STOPPED));
	}

}
