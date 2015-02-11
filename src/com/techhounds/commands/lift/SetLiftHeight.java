package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetLiftHeight extends Command {

	private static final double TOLERANCE = .1;
	
	private LiftSubsystem lift;
	private double dist;
	private int direction;
	private double initHeight;
	private double finalHeight;
	
	private boolean isAbsolute;
	private double target;
	
    public SetLiftHeight(double dist, int direction) {
    	lift = LiftSubsystem.getInstance();
    	this.dist = dist;
    	this.direction = direction;
    	isAbsolute = false;
    }

    public SetLiftHeight(double height){
    	lift = LiftSubsystem.getInstance();
    	isAbsolute = true;
    	target = height;
    }
    
    protected void initialize() {

    	initHeight = lift.getEncHeight();
    	if (isAbsolute){
    		direction = initHeight < target ? LiftSubsystem.UP : LiftSubsystem.DOWN;
    		dist = Math.abs(initHeight - target);
    	}
    	lift.setBrake(false);
    	lift.setLift(direction, LiftSubsystem.LIFT_POWER);
    	
    	if (direction == LiftSubsystem.UP)
    		finalHeight = initHeight + dist;
    	else if (direction == LiftSubsystem.DOWN)
    		finalHeight = initHeight - dist;
    	
    	SmartDashboard.putString("SetLiftHeight", "init: " + initHeight + ", isAbs: " + isAbsolute + ", dir: " + direction + ", dist: " + dist + ", Fin: " + finalHeight);
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
    	lift.setBrake(true);
    	lift.setBrakeHeight(finalHeight);
    }

    protected void interrupted() {
    	end();
    }
}
