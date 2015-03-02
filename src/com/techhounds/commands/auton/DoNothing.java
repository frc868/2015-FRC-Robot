package com.techhounds.commands.auton;

import com.techhounds.commands.Wink;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * @author Atif Niyaz
 */
public class DoNothing extends CommandGroup {
    
    public DoNothing() {
    	addSequential(new WaitCommand(14));
		addSequential(new Wink());
    }
}
