package com.techhounds.commands.bin;

import com.techhounds.subsystems.BinSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveBinHeight extends Command {

	private static final double TOLERANCE = .25;
	
	private BinSubsystem bin;
	private double dist;
	private int direction;
	private double initHeight;
	private double finalHeight;
	
    public MoveBinHeight(double dist, int direction) {
    	bin = BinSubsystem.getInstance();
    	this.dist = dist;
    	this.direction = direction;
    }

    protected void initialize() {
    	initHeight = bin.getEncHeight();
    	bin.setBin(direction, LiftSubsystem.LIFT_POWER);
    	if (direction == LiftSubsystem.UP)
    		finalHeight = initHeight + dist;
    	else if (direction == LiftSubsystem.DOWN)
    		finalHeight = initHeight - dist;
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return (direction == LiftSubsystem.UP && bin.isAtTop()) || 
        	   (direction == LiftSubsystem.DOWN && bin.isAtBottom()) ||
        	   (Math.abs(finalHeight - bin.getEncHeight()) < TOLERANCE);
    }

    protected void end() {
    	bin.setBin(LiftSubsystem.STOPPED, 0);
    }

    protected void interrupted() {
    	end();
    }
}
