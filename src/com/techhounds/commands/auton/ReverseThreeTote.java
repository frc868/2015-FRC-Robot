package com.techhounds.commands.auton;

import com.techhounds.commands.Wink;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.ManualTurn;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.WaitForLiftSwitch;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ReverseThreeTote extends CommandGroup {
    
    public  ReverseThreeTote(boolean turnAndMove, boolean startAngleLeft, boolean liftBin) {

    	if (liftBin){
    		addSequential(new SetLift(LiftSubsystem.CLOSED));
    		addSequential(new SetLiftHeight((LiftSubsystem.ONE_TOTE_HEIGHT + LiftSubsystem.TWO_TOTE_HEIGHT) / 2));
    		addParallel(new DriveTime(1, .4, false));
    		addSequential(new WaitForIR(6, 2, true, .5));
    		addSequential(new DriveTime(0, 0, true));
    		addSequential(new SetLift(LiftSubsystem.DOWN));
    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    	}else{
	    	//knock bin out of way
	    	addSequential(new ManualTurn(.6, .55, !startAngleLeft));
    	}
    	
    	addSequential(new ThreeTote(false, 0));
    	
    	if (turnAndMove){
//    		addSequential(new ManualTurn(.6, 1.2, false));						//should change to gyroPID
    		addSequential(new RotateToAngle(-90, 1));
//    		addSequential(new MoveToAutoZone(true));							//should change to drivePID
    		addSequential(new AutonDrive(9, 2));
    		addSequential(new SetLift(LiftSubsystem.DOWN));								//''
    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    		addSequential(new SetLift(LiftSubsystem.OPEN));
    		addSequential(new DriveTime(.1, -.4, true));
    		addSequential(new Wink());
    	}
    	
//    	
//    	//grab first tote
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true));
//		addSequential(new SetLift(LiftSubsystem.CLOSED));
//		addSequential(new WaitCommand(.3));
//		
//		//lift first tote and drive to second tote
//		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//		addSequential(new WaitCommand(.3));
//		addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true, 2));
//    	
//		//move lift down to second tote
//    	addSequential(new SetLift(LiftSubsystem.OPEN));
//    	addSequential(new SetLift(LiftSubsystem.DOWN));
//    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
//    	
//    	//grab second tote
//    	addSequential(new SetLift(LiftSubsystem.CLOSED));
//    	addSequential(new WaitCommand(.3));
//    	
//    	//lift second tote and drive to third tote
//    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//		addSequential(new WaitCommand(.3));
//    	addParallel(new DriveToClosestTote(1));
//		addSequential(new WaitForIR(6, 2, true, 2));
//    	
//		//move lift down to third tote
//    	addSequential(new SetLift(LiftSubsystem.OPEN));
//    	addSequential(new SetLift(LiftSubsystem.DOWN));
//    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
//    	
//    	//lift up third tote a little
//    	addSequential(new SetLift(LiftSubsystem.CLOSED));
//    	addSequential(new WaitCommand(.3));
//    	addSequential(new SetLiftHeight(LiftSubsystem.OFF_GROUND_HEIGHT));
//    	
//    	if (turnAndMove){
//    		addSequential(new ManualTurn(.68, 1.2, true));
//    		addSequential(new MoveToAutoZone(1.6, true));
//    		addSequential(new SetLift(LiftSubsystem.DOWN));
//    		addSequential(new SetLift(LiftSubsystem.OPEN));
////    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
////    		addSequential(new DriveTime(1, -.5));
//    	}
    }
}
