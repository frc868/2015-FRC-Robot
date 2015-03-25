package com.techhounds.commands.feeder;

import com.techhounds.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetFeederMult extends Command {

	private FeederSubsystem feed;
	
	private double left, right;
	
    public SetFeederMult(double left, double right) {
    	feed = FeederSubsystem.getInstance();
    	this.left = left / FeederSubsystem.FEED_IN;
    	this.right = right / FeederSubsystem.FEED_IN;
    }

    protected void initialize() {
    	feed.setPowerMults(left, right);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}