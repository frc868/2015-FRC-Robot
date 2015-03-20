package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayBrake extends Command {

	private LiftSubsystem lift;
	
	private double time;
	private int dir;
	private Double height;
	
    public DelayBrake(double time, int dir) {
    	lift = LiftSubsystem.getInstance();
    	this.time = time;
    	this.dir = dir;
    }
    
    public DelayBrake(double time, int dir, Double height) {
    	lift = LiftSubsystem.getInstance();
    	this.time = time;
    	this.dir = dir;
    	this.height = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lift.setLift(dir, 0);
    	lift.setBrake(true);
    	if (height != null)
    		lift.setBrakeHeight(height);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
