package com.techhounds.commands.auton;

import com.techhounds.commands.SetFeeder;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.ManualTurn;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class TwoTote extends CommandGroup {

	public TwoTote(boolean turnAndMove, double waitTime) {

		/*
		 * Wait for other robots to push bins out of the way
		 */
		addSequential(new WaitCommand(waitTime));
		
		/*
		 * Drive to Tote.
		 * Wait till Tote is 6 inches away
		 * Set Feeder so it is closed and going in.
		 * Wait till Tote is 2 inch away
		 * Open the feeder and stop it and close the lift
		 */
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true));
		addParallel(new SetFeeder(FeederSubsystem.FEED_IN, FeederSubsystem.CLOSED));
		addSequential(new WaitForIR(2, 2, true));
		addParallel(new SetFeeder(FeederSubsystem.STOPPED, FeederSubsystem.OPEN));
		addParallel(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitForChildren());
		addSequential(new WaitCommand(.3));
	
		/*
		 * Lift the tote to one tote height
		 * While doing that drive for 1 second at 40% power
		 * Drive to Tote
		 * Wait till Tote is 6 inches away
		 * Set Feeder so that tote goes in and feeder is closed
		 * Wait till Tote is 2 inches away
		 */
		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new DriveTime(1, .4, false));
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true));
		addParallel(new SetFeeder(FeederSubsystem.FEED_IN, FeederSubsystem.CLOSED));
		addSequential(new WaitForIR(2, 2, true));
		
		/*
		 * Set Feeder so it stops and it will open
		 * While doing that the lift will open and go down.
		 * Then once it is down, close the Lift.
		 */
		addParallel(new SetFeeder(FeederSubsystem.STOPPED, FeederSubsystem.OPEN));
		addParallel(new SetLift(LiftSubsystem.OPEN));
		addParallel(new SetLift(LiftSubsystem.DOWN));
		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.3));
		
		/*
		 * Lift tote so its .2 feet above surface
		 * Rotate 90 degrees approximately
		 * Move into Auto Zone
		 * Set Lift down until it hits limit switch
		 * Open lift
		 */
		addSequential(new SetLiftHeight(.2));
		
		if (turnAndMove){
    		addSequential(new ManualTurn(.6, 1.2, false));
    		//addSequential(new RotateToAngle(90));
    		addSequential(new MoveToAutoZone(true));
    		addParallel(new SetLift(LiftSubsystem.DOWN));
    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    		addSequential(new SetLift(LiftSubsystem.OPEN));
    	}
		
//		//grab first tote
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true));
//		addSequential(new SetLift(LiftSubsystem.CLOSED));
//		addSequential(new WaitCommand(.3));
//		
//		//lift first tote and drive to second tote
//		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//		addSequential(new WaitCommand(.3));
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true, 1));
//    	
//		//move lift down to second tote
//    	addSequential(new SetLift(LiftSubsystem.OPEN));
//    	addSequential(new SetLift(LiftSubsystem.DOWN));
//    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
//    	
//    	//grab second tote and lift a little
//    	addSequential(new SetLift(LiftSubsystem.CLOSED));
//    	addSequential(new WaitCommand(.3));
//    	addSequential(new SetLiftHeight(.2));
		
	}
}
