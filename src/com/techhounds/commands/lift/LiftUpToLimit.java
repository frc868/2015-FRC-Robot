package com.techhounds.commands.lift;

import com.techhounds.RobotMap;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftUpToLimit extends Command {

	private static LiftSubsystem lift;
	
    public LiftUpToLimit() {
       lift = LiftSubsystem.getInstance();
       requires(lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(lift.getTop()) {
    		lift.setPower(lift.LIFT_UP_POWER);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lift.setPower(lift.LIFT_UP_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
