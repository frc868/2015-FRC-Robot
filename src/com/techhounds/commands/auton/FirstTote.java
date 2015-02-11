package com.techhounds.commands.auton;

import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FirstTote extends CommandGroup {
	
	public FirstTote() {
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.3));
		addSequential(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
	}
}
