package com.techhounds.commands.auton;

import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OneToteBin extends CommandGroup {
	
	public OneToteBin() {
		this(false);
	}
	
	public OneToteBin(boolean collectBin) {
		addSequential(new DriveToClosestTote());
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(1));
		addSequential(new SetLiftHeight(1, LiftSubsystem.UP));
	}
}
