package com.techhounds.commands.auton;

import com.techhounds.commands.Wink;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.feeder.SetFeederMult;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TwoToteMoveBin extends CommandGroup {

	public TwoToteMoveBin(boolean turnAndMove) {
		addSequential(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.1));
		
		/*
		 * Lift the tote to one tote height
		 * While doing that drive for 1 second at 40% power
		 * Drive to Tote
		 * Wait till Tote is 6 inches away
		 * Set Feeder so that tote goes in and feeder is closed
		 * Wait till Tote is 2 inches away
		 */
		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));

		addSequential(new RotateToAngle(55, .75));
		addSequential(new AutonDrive(4.5, .1, 1));
		addSequential(new RotateToAngle(-120, .75));
		
		addParallel(new SetFeeder(FeederSubsystem.FEED_IN));
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitCommand(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new DriveTime(0, 0, true));
		addParallel(new SetFeeder(FeederSubsystem.CLOSED));
		
		
		if (turnAndMove){
    		addSequential(new RotateToAngle(90, 1));
    		addParallel(new SetFeeder(FeederSubsystem.STOPPED));
    		addSequential(new AutonDrive(9, 2));
    	}
		addSequential(new Wink());
	}
}
