package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftHeight extends Command {

	private static final double TOLERANCE = .25;
	
	private LiftSubsystem lift;
	private double dist;
	private int direction;
	private double initHeight;
	private double finalHeight;
	
    public MoveLiftHeight(double dist, int direction) {
    	lift = LiftSubsystem.getInstance();
    	this.dist = dist;
    	this.direction = direction;
    }

    protected void initialize() {
    	initHeight = lift.getEncHeight();
    	lift.setLift(direction, LiftSubsystem.LIFT_POWER);
    	if (direction == LiftSubsystem.UP)
    		finalHeight = initHeight + dist;
    	else if (direction == LiftSubsystem.DOWN)
    		finalHeight = initHeight - dist;
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return (direction == LiftSubsystem.UP && lift.isAtTop()) || 
        	   (direction == LiftSubsystem.DOWN && lift.isAtBottom()) ||
        	   (Math.abs(finalHeight - lift.getEncHeight()) < TOLERANCE);
    }

    protected void end() {
    	lift.setLift(LiftSubsystem.STOPPED, 0);
    }

    protected void interrupted() {
    	end();
    }
}
