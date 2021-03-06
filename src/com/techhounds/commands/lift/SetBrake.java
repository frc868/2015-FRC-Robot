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

    protected void initialize() {
    	lift.setBrake(braking);
    	if (braking){
        	lift.setLift(LiftSubsystem.Action.STOPPED, 0);
    		lift.setBrakeHeight(lift.getEncHeight());
    	}
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
