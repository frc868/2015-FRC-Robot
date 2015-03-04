package com.techhounds.commands;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpdateDriverCont extends Command {

	private OI oi;
	
    public UpdateDriverCont() {
    	oi = OI.getInstance();
    }

    protected void initialize() {
    	oi.updateDriverCont();
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