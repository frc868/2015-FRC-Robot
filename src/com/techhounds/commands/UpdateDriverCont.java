package com.techhounds.commands;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpdateDriverCont extends Command {
	
    public UpdateDriverCont() {
    }

    protected void initialize() {
    	OI.updateDriverCont();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}