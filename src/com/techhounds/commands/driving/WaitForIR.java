package com.techhounds.commands.driving;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WaitForIR extends Command {

	private double tolerance = 2.5;
	
	private LiftSubsystem lift;
	private double dist;
	private Boolean lowTarget;
	private Double minTime;
	
    public WaitForIR(double dist) {
    	lift = LiftSubsystem.getInstance();
    	this.dist = dist;
    }
    
    public WaitForIR(double dist, boolean lowTarget){
    	this(dist);
    	this.lowTarget = lowTarget;
    }

    public WaitForIR(double dist, double tolerance){
    	this(dist);
    	this.tolerance = tolerance;
    }
    
    public WaitForIR(double dist, double tolerance, boolean lowTarget){
    	this(dist, tolerance);
    	this.lowTarget = lowTarget;
    }
    
    public WaitForIR(double dist, double tolerance, boolean lowTarget, double minTime){
    	this(dist, tolerance, lowTarget);
    	this.minTime = minTime;
    }
    
    protected void initialize() {
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	if (minTime != null && minTime > timeSinceInitialized())
    		return false;
    	
    	if (lowTarget == null){
    		return Math.abs(lift.getIRDist() - dist) < tolerance;
    	}else{
    		if (lowTarget){
    			SmartDashboard.putNumber("IR Val", lift.getIRDist());
    			return lift.getIRDist() < dist + tolerance;
    		}
    		else
    			return lift.getIRDist() > dist - tolerance;
    	}
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}