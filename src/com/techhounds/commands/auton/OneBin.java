package com.techhounds.commands.auton;

import com.techhounds.commands.Wink;
import com.techhounds.commands.driving.ManualTurn;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.driving.WaitForIR;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OneBin extends CommandGroup {
    
    public  OneBin() {
    	
    	//grab bin
		addSequential(new SetLift(LiftSubsystem.CLOSED));
		addSequential(new WaitCommand(.1));
		
		//lift first tote and turn and move
		addParallel(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
		addSequential(new RotateToAngle(-90, 1));
		addSequential(new AutonDrive(8.5, 6));
//		addSequential(new ManualTurn(.6, .9, true));
//		addSequential(new MoveToAutoZone(1.75, .25));
		addSequential(new Wink());
    }
}
