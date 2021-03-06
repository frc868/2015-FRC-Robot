package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayBrake extends Command {

	private LiftSubsystem lift;
	
	private double time;
	private LiftSubsystem.Action dir;
	private Double height;
	
    public DelayBrake(double time, LiftSubsystem.Action dir) {
    	lift = LiftSubsystem.getInstance();
    	this.time = time;
    	this.dir = dir;
    }
    
    public DelayBrake(double time, LiftSubsystem.Action dir, Double height) {
    	lift = LiftSubsystem.getInstance();
    	this.time = time;
    	this.dir = dir;
    	this.height = height;
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timeSinceInitialized() >= time;
    }

    protected void end() {
    	lift.setLift(dir, 0);
    	lift.setBrake(true);
    	if (height != null)
    		lift.setBrakeHeight(height);
    }

    protected void interrupted() {
    }
}
