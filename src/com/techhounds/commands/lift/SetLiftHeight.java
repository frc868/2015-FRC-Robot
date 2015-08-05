package com.techhounds.commands.lift;

import com.techhounds.OI;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetLiftHeight extends Command {
	
	private static final double TOLERANCE = .1;
	
	private LiftSubsystem lift;
	private boolean isAbsolute;
	private double height;
	private LiftSubsystem.Action action;
	
	private double initHeight;
	private Boolean doesNotMatter;
	private Double timeDelay;
	
	public SetLiftHeight() {
		this(0.0);
	}
	
	public SetLiftHeight(double height) {
		lift = LiftSubsystem.getInstance();
		this.height = height - .3;
		isAbsolute = true;
		doesNotMatter = null;
		this.timeDelay = null;
	}
	
	public SetLiftHeight(double height, double timeDelay) {
		this(height);
		this.timeDelay = timeDelay;
		
	}
	
	public SetLiftHeight(double height, LiftSubsystem.Action action) {
		this(height);
		this.action = action;
		isAbsolute = false;
		this.timeDelay = null;
	}
	
	public SetLiftHeight(double height, LiftSubsystem.Action action, boolean doesNotMatter) {
		this(height, action);
		this.doesNotMatter = doesNotMatter;
		this.timeDelay = null;
	}
    
    protected void initialize() {

    	initHeight = lift.getEncHeight();
    	LiftSubsystem.DRIVER_CONTROL = true;
    	
    	if (isAbsolute){
    		action = initHeight > height ? LiftSubsystem.Action.DOWN : LiftSubsystem.Action.UP;
    	} else {
    		if(doesNotMatter == null){
	    		if(LiftSubsystem.Action.UP == action){
	    			height = initHeight + height;
	    		}
	    		else if(LiftSubsystem.Action.DOWN == action){
	    			height = initHeight - height;
	    		}
    		}
    	
    	}

    	lift.setLift(action, LiftSubsystem.LIFT_POWER);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	
    	SmartDashboard.putBoolean("Is At Top", action == LiftSubsystem.Action.UP && lift.isAtTop());
    	SmartDashboard.putBoolean("Is At Bottom", action == LiftSubsystem.Action.DOWN && lift.isAtBottom());
    	SmartDashboard.putBoolean("Is Direction Same", action != lift.getDirection());
    	SmartDashboard.putBoolean("Is Tolerance", Math.abs(height - lift.getEncHeight()) < TOLERANCE);
    	SmartDashboard.putBoolean("Is Beyond Up", height < initHeight);
    	SmartDashboard.putBoolean("Is Beyond Down", height > initHeight);
    	SmartDashboard.putString("Direction", action == LiftSubsystem.Action.UP ? "UP" : "DOWN");
    	
    	return (action == LiftSubsystem.Action.UP && lift.isAtTop()) || 
        	   (action == LiftSubsystem.Action.DOWN && lift.isAtBottom()) ||
        	   (action != lift.getDirection()) ||
        	   (Math.abs(height - lift.getEncHeight()) < TOLERANCE) ||
        	   (doesNotMatter != null ? (action == LiftSubsystem.Action.UP ? height < initHeight : height > initHeight) : false) ||
        	   (timeDelay != null ? timeSinceInitialized() > timeDelay : false);
    }

    protected void end() {
    	LiftSubsystem.DRIVER_CONTROL = false;
    }

    protected void interrupted() {
    	end();
    }
}
