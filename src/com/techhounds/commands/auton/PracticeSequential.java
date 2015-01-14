/*Ayon Mitra, Matt Simons, the interns
 * after school
 * 1/14/15
 * this program autons a series of stuff without User Input.  It will go on until the sequential ends
 * or the driver hits stop on his/her laptop
*/
package com.techhounds.commands.auton;

import com.techhounds.commands.DriveTime;
import com.techhounds.commands.arms.SetArms;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.subsystems.ArmsSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class PracticeSequential extends CommandGroup {
    
    public  PracticeSequential() {
    	
    	addSequential(new SetLift(LiftSubsystem.DOWN));
    	addSequential(new DriveTime(5 , 1));
    	addSequential(new SetArms(ArmsSubsystem.FEED_IN, ArmsSubsystem.CLOSED));
    	addSequential(new WaitCommand(.5));
    	addSequential(new SetArms(ArmsSubsystem.STOPPED , ArmsSubsystem.OPEN));
    	addSequential(new SetLift(LiftSubsystem.UP));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveTime(5 , -1));
    	
    }
}
