package com.techhounds.commands.auton;

import com.techhounds.commands.Debug;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.ManualTurn;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class ThreeTote extends CommandGroup {
	
	public ThreeTote(boolean turnAndMove, double waitTime) {
//		
//		addSequential(new FirstTote());
//		addSequential(new NextTote(false));
//		addSequential(new NextTote(true));
		
		addSequential(new Debug("1"));
		addSequential(new WaitCommand(waitTime));
		addSequential(new Debug("2"));
		
		//grab first tote
		addParallel(new DriveToClosestTote(1));
		addSequential(new Debug("3"));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new Debug("4"));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new Debug("5"));
		addSequential(new WaitCommand(.3));
		addSequential(new Debug("6"));
		
		//lift first tote and drive to second tote
		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new Debug("7"));
//		addSequential(new WaitCommand(.3));
    	addSequential(new DriveTime(1, .4, false));
		addSequential(new Debug("8"));
		addParallel(new DriveToClosestTote(1));
		addSequential(new Debug("9"));
		addSequential(new WaitForIR(6, 2, true, 2));
		addSequential(new Debug("10"));
		
//    	addParallel(new DriveToClosestTote(1));
//    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//		addParallel(new WaitForIR(6, 2, true, 1));
//		addSequential(new WaitForChildren());
    	
		//move lift down to second tote
    	addSequential(new SetLift(LiftSubsystem.OPEN));
		addSequential(new Debug("11"));
    	addSequential(new SetLift(LiftSubsystem.DOWN));
		addSequential(new Debug("12"));
    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
		addSequential(new Debug("13"));
    	
    	//grab second tote
    	addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new Debug("14"));
    	addSequential(new WaitCommand(.3));
		addSequential(new Debug("15"));
    	
    	//lift second tote and drive to third tote
    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new Debug("16"));
//		addSequential(new WaitCommand(.3));
    	addSequential(new DriveTime(.75, .4, false));
		addSequential(new Debug("17"));
    	addParallel(new DriveToClosestTote(1));
		addSequential(new Debug("18"));
		addSequential(new WaitForIR(6, 2, true, 2));
		addSequential(new Debug("19"));
    	
		//move lift down to third tote
    	addSequential(new SetLift(LiftSubsystem.OPEN));
		addSequential(new Debug("20"));
    	addSequential(new SetLift(LiftSubsystem.DOWN));
		addSequential(new Debug("21"));
    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
		addSequential(new Debug("22"));
    	
    	//lift up third tote a little
    	addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new Debug("23"));
    	addSequential(new WaitCommand(.3));
		addSequential(new Debug("24"));
    	addSequential(new SetLiftHeight(.2));
		addSequential(new Debug("25"));
    	
    	if (turnAndMove){
    		addSequential(new ManualTurn(.6, 1.2, false));
    		addSequential(new MoveToAutoZone(true));
    		addSequential(new SetLift(LiftSubsystem.DOWN));
    		addSequential(new SetLift(LiftSubsystem.OPEN));
//    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
//    		addSequential(new DriveTime(.5, -.5));
    	}
//		}
	}
}
