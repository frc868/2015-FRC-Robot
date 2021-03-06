package com.techhounds.commands.auton;

import com.techhounds.commands.Wink;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.feeder.SetFeederMult;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ThreeTote extends CommandGroup {
	
	public ThreeTote(boolean turnAndMove, double waitTime) {
		
		addSequential(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN));
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
//		addSequential(new DriveTime(1.5, .3, false));
		if (waitTime >= .1)
			addSequential(new WaitCommand(waitTime - .1));
		addParallel(new SetFeeder(FeederSubsystem.FEED_IN));
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitCommand(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new DriveTime(0, 0, true));
		addParallel(new SetFeeder(FeederSubsystem.CLOSED));
		addSequential(new SetLift(LiftSubsystem.Action.DOWN));
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
		addSequential(new WaitForLiftSwitch(LiftSubsystem.Action.DOWN));
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
		addSequential(new WaitCommand(1));
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
		 * Set Feeder so it stops and it will open
		 * While doing that the lift will open and go down.
		 * Then once it is down, close the Lift.
		 */
//		addParallel(new SetFeeder(FeederSubsystem.STOPPED));
	//	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
	//	addSequential(new SetLift(LiftSubsystem.CLOSED));
	//	addParallel(new SetFeeder(FeederSubsystem.OPEN));
	//	addSequential(new WaitCommand(.1));
		
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
    		addSequential(new SetLift(LiftSubsystem.Action.DOWN));
    		addSequential(new WaitCommand(.1));
    		addSequential(new SetLift(LiftSubsystem.OPEN));
    		addSequential(new WaitForLiftSwitch(LiftSubsystem.Action.DOWN));
    		addParallel(new SetFeeder(FeederSubsystem.OPEN));
    		addParallel(new AutonDrive(-3, .1, 1.5));
    	}
		addSequential(new Wink());
			
//		
//		addSequential(new Debug("1"));
//		addSequential(new WaitCommand(waitTime)); 
//		addSequential(new Debug("2"));
//		
//		//grab first tote
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new Debug("3"));
//		addSequential(new WaitForIR(6, 2, true));
//		addSequential(new Debug("4"));
//		addSequential(new SetLift(LiftSubsystem.CLOSED));
//		addSequential(new Debug("5"));
//		addSequential(new WaitCommand(.3));
//		addSequential(new Debug("6"));
//		
//		//lift first tote and drive to second tote
//		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//		addSequential(new Debug("7"));
////		addSequential(new WaitCommand(.3));
//    	addSequential(new DriveTime(1, .4, false));
//		addSequential(new Debug("8"));
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new Debug("9"));
//		addSequential(new WaitForIR(6, 2, true, 2));
//		addSequential(new Debug("10"));
//		
////    	addParallel(new DriveToClosestTote(1));
////    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
////		addParallel(new WaitForIR(6, 2, true, 1));
////		addSequential(new WaitForChildren());
//    	
//		//move lift down to second tote
//    	addSequential(new SetLift(LiftSubsystem.OPEN));
//		addSequential(new Debug("11"));
//    	addSequential(new SetLift(LiftSubsystem.DOWN));
//		addSequential(new Debug("12"));
//    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
//		addSequential(new Debug("13"));
//    	
//    	//grab second tote
//    	addSequential(new SetLift(LiftSubsystem.CLOSED));
//		addSequential(new Debug("14"));
//    	addSequential(new WaitCommand(.3));
//		addSequential(new Debug("15"));
//    	
//    	//lift second tote and drive to third tote
//    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//		addSequential(new Debug("16"));
////		addSequential(new WaitCommand(.3));
//    	addSequential(new DriveTime(.75, .4, false));
//		addSequential(new Debug("17"));
//    	addParallel(new DriveToClosestTote(1));
//		addSequential(new Debug("18"));
//		addSequential(new WaitForIR(6, 2, true, 2));
//		addSequential(new Debug("19"));
//    	
//		//move lift down to third tote
//    	addSequential(new SetLift(LiftSubsystem.OPEN));
//		addSequential(new Debug("20"));
//    	addSequential(new SetLift(LiftSubsystem.DOWN));
//		addSequential(new Debug("21"));
//    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
//		addSequential(new Debug("22"));
//    	
//    	//lift up third tote a little
//    	addSequential(new SetLift(LiftSubsystem.CLOSED));
//		addSequential(new Debug("23"));
//    	addSequential(new WaitCommand(.3));
//		addSequential(new Debug("24"));
//    	addSequential(new SetLiftHeight(.2));
//		addSequential(new Debug("25"));
    	
//    	if (turnAndMove){
//    		addSequential(new ManualTurn(.6, 1.2, false));
//    		addSequential(new MoveToAutoZone(true));
//    		addSequential(new SetLift(LiftSubsystem.DOWN));
//    		addSequential(new SetLift(LiftSubsystem.OPEN));
////    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
////    		addSequential(new DriveTime(.5, -.5));
//    	}
//		}
	}
}
