package com.techhounds.commands.lift;

import com.techhounds.OI;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetLiftHeight extends Command {
	
	public static double USE_SLIDE = -1;

	private static final double TOLERANCE = .1;
	
	private boolean useSlide = false;
	
	private LiftSubsystem lift;
	private double dist;
	private int direction;
	private double initHeight;
	private double finalHeight;
	
	private boolean isAbsolute;
	private double target;
	private Boolean downIsGood;
	
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
    	useSlide = target == USE_SLIDE;
    }
    
    public SetLiftHeight(double height, boolean downIsGood){
    	lift = LiftSubsystem.getInstance();
    	isAbsolute = true;
    	target = height;
    	useSlide = target == USE_SLIDE;
    	this.downIsGood = downIsGood;
    }
    
    protected void initialize() {

    	if (useSlide)
    		target = OI.getOPBoardSlider();
    	
    	initHeight = lift.getEncHeight();
    	if (isAbsolute){
    		direction = initHeight < target ? LiftSubsystem.UP : LiftSubsystem.DOWN;
    		dist = Math.abs(initHeight - target);
    	}
    	lift.setBrake(false);
    	lift.setCmdRunning(true);
    	lift.setLift(direction, LiftSubsystem.LIFT_POWER);
    	
    	if (direction == LiftSubsystem.UP)
    		finalHeight = initHeight + dist;
    	else if (direction == LiftSubsystem.DOWN)
    		finalHeight = initHeight - dist;
    	
    	SmartDashboard.putString("SetLiftHeight", "init: " + initHeight + ", isAbs: " + isAbsolute + ", dir: " + direction + ", dist: " + dist + ", Fin: " + finalHeight + ", Target: " + target);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	
    	if (downIsGood != null){
    		if(downIsGood){
    			return (direction == LiftSubsystem.UP && lift.isAtTop()) || 
    	        	   (direction == LiftSubsystem.DOWN && lift.isAtBottom()) ||
    	        	   (direction != lift.getDirection()) || 
    	        	   (lift.getEncHeight() < finalHeight);
    		}
    		
    		return (direction == LiftSubsystem.UP && lift.isAtTop()) || 
    	        	   (direction == LiftSubsystem.DOWN && lift.isAtBottom()) ||
    	        	   (direction != lift.getDirection()) ||
    	        	   (lift.getEncHeight() > finalHeight);
    	}
    	
        return (direction == LiftSubsystem.UP && lift.isAtTop()) || 
        	   (direction == LiftSubsystem.DOWN && lift.isAtBottom()) ||
        	   (direction != lift.getDirection()) ||
        	   (Math.abs(finalHeight - lift.getEncHeight()) < TOLERANCE);
    }

    protected void end() {
//		(new DelayBrake(.25, lift.getDirection(), finalHeight)).start();
    	lift.setLift(LiftSubsystem.STOPPED, 0);
    	lift.setBrake(true);
    	lift.setBrakeHeight(finalHeight);
    	lift.setCmdRunning(false);
    }

    protected void interrupted() {
    	end();
    }
}
