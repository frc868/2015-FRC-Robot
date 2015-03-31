package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetBrake extends Command {

	private LiftSubsystem lift;
	
	private boolean braking;
	
    public SetBrake(boolean braking) {
    	lift = LiftSubsystem.getInstance();
    	this.braking = braking;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lift.setBrake(braking);
    	if (braking){
        	lift.setLift(lift.STOPPED, 0);
    		lift.setBrakeHeight(lift.getEncHeight());
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
