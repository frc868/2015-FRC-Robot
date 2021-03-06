package com.techhounds.commands.auton;

import com.techhounds.commands.Wink;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.SetPassiveStop;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;
import com.techhounds.subsystems.PassiveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BinAndTote extends CommandGroup {
    
    public  BinAndTote() {

		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new SetLiftHeight((LiftSubsystem.ONE_TOTE_HEIGHT + LiftSubsystem.TWO_TOTE_HEIGHT) / 2));
//		addParallel(new DriveTime(1, .4, false));
		addSequential(new AutonDrive(2.5, .1, 2));
//		addSequential(new WaitForIR(6, 2, true, .5));
//		addSequential(new DriveTime(0, 0, true));
		addParallel(new SetFeeder(FeederSubsystem.FEED_IN, FeederSubsystem.CLOSED));
		addSequential(new SetLift(LiftSubsystem.Action.DOWN));
		addSequential(new WaitCommand(.5));
		addSequential(new SetLift(LiftSubsystem.OPEN));
		addSequential(new WaitForLiftSwitch(LiftSubsystem.Action.DOWN));
		addParallel(new SetFeeder(FeederSubsystem.STOPPED, FeederSubsystem.OPEN));
    	
    	//grab first tote
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.2));
		
		//lift first tote and turn and move
		addSequential(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
		addSequential(new SetLiftHeight((LiftSubsystem.ONE_TOTE_HEIGHT + LiftSubsystem.TWO_TOTE_HEIGHT) / 2));
		addSequential(new WaitCommand(.2));
		addSequential(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));
		addSequential(new RotateToAngle(-90, 1));
		addSequential(new AutonDrive(9.5, 2));
//		addSequential(new ManualTurn(.6, 1, true));
//		addSequential(new MoveToAutoZone(1.75, .25));
//		addSequential(new SetLift(LiftSubsystem.DOWN));
//		addSequential(new SetLift(LiftSubsystem.OPEN));
		addSequential(new Wink());
    }
}
