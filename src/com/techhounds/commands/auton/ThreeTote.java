package com.techhounds.commands.auton;

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
	
	public ThreeTote(boolean turnAndMove) {
//		addSequential(new FirstTote());
//		addSequential(new NextTote(false));
//		addSequential(new NextTote(true));
		
		//grab first tote
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true));
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.3));
		
		//lift first tote and drive to second tote
		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new WaitCommand(.3));
		addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true, 2));
    	
		//move lift down to second tote
    	addSequential(new SetLift(LiftSubsystem.OPEN));
    	addSequential(new SetLift(LiftSubsystem.DOWN));
    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    	
    	//grab second tote
    	addSequential(new SetLift(LiftSubsystem.CLOSED));
    	addSequential(new WaitCommand(.3));
    	
    	//lift second tote and drive to third tote
    	addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new WaitCommand(.3));
    	addParallel(new DriveToClosestTote(1));
		addSequential(new WaitForIR(6, 2, true, 2));
    	
		//move lift down to third tote
    	addSequential(new SetLift(LiftSubsystem.OPEN));
    	addSequential(new SetLift(LiftSubsystem.DOWN));
    	addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    	
    	//lift up third tote a little
    	addSequential(new SetLift(LiftSubsystem.CLOSED));
    	addSequential(new WaitCommand(.3));
    	addSequential(new SetLiftHeight(.2));
    	
    	if (turnAndMove){
    		addSequential(new ManualTurn(.75, 1.5, false));
    		addSequential(new MoveToAutoZone(1.5, true));
    		addSequential(new SetLift(LiftSubsystem.DOWN));
    		addSequential(new SetLift(LiftSubsystem.OPEN));
    		addSequential(new WaitForLiftSwitch(LiftSubsystem.DOWN));
    		addSequential(new DriveTime(1, -.5));
    	}
	}
}
