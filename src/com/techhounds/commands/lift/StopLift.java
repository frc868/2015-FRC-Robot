package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Clayton Detke
 */
public class StopLift extends Command {
	
	private LiftSubsystem lift;

    public StopLift() {
    	lift = lift.getInstance();
    	requires(lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lift.stopLift();
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
    }
}
