package com.techhounds.commands.auton;

import com.techhounds.commands.Debug;
import com.techhounds.commands.Wink;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.ManualTurn;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class ThreeToteMoveTwoBin extends CommandGroup {
	
	public ThreeToteMoveTwoBin(boolean turnAndMove) {
		
		
		/*
		 * Drive to Tote.
		 * Wait till Tote is 6 inches away
		 * Set Feeder so it is closed and going in.
		 * Wait till Tote is 2 inch away
		 * Open the feeder and stop it and close the lift
		 */
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true));						
//		addParallel(new SetFeeder(FeederSubsystem.FEED_IN, FeederSubsystem.CLOSED));
//		addSequential(new WaitForIR(3, 2, true));
//		addParallel(new SetFeeder(FeederSubsystem.STOPPED, FeederSubsystem.OPEN));		
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
		addSequential(new SetLift(LiftSubsystem.DOWN));
		addSequential(new WaitCommand(.1));
		addSequential(new SetLift(LiftSubsystem.OPEN));
//		addSequential(new WaitForIR(3, 2, true));
		addSequential(new WaitCommand(.15));
		
		/*
		 * Set Feeder so it stops and it will open
		 * While doing that the lift will open and go down.
		 * Then once it is down, close the Lift.
		 */
//		addParallel(new SetFeeder(FeederSubsystem.STOPPED));
		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addParallel(new SetFeeder(FeederSubsystem.OPEN));
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
		addParallel(new SetFeeder(FeederSubsystem.STOPPED));

		addSequential(new RotateToAngle(55, .75));
		addSequential(new AutonDrive(4.5, .1, 1));
		addSequential(new RotateToAngle(-120, .75));
		
		addParallel(new SetFeeder(FeederSubsystem.FEED_IN));
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitCommand(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new DriveTime(0, 0, true));
		addParallel(new SetFeeder(FeederSubsystem.CLOSED));
	//	addSequential(new SetLift(LiftSubsystem.DOWN));
		addSequential(new WaitCommand(.1));
	//	addSequential(new SetLift(LiftSubsystem.OPEN));
	//	addSequential(new WaitCommand(.15));
		
		/*
		 * Lift tote so its .2 feet above surface
		 * Rotate 90 degrees approximately
		 * Move into Auto Zone
		 * Set Lift down until it hits limit switch
		 * Open lift
		 */
		//addSequential(new SetLiftHeight(LiftSubsystem.OFF_GROUND_HEIGHT));

		if (turnAndMove){
    		addSequential(new RotateToAngle(90, 1));
    		addParallel(new SetFeeder(FeederSubsystem.STOPPED));
    		addSequential(new AutonDrive(9, 2));
    		addSequential(new SetLift(LiftSubsystem.DOWN));
    		addSequential(new WaitCommand(.1));
    		addSequential(new SetLift(LiftSubsystem.OPEN));
    		addParallel(new SetFeeder(FeederSubsystem.OPEN));
    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    		addParallel(new AutonDrive(-3, .1, 1.5));
//    		addParallel(new DriveTime(2, -.5, true));
    	}
		addSequential(new Wink());
		
	}
}
