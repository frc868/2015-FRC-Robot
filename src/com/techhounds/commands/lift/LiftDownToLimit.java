package com.techhounds.commands.lift;

import com.techhounds.OI;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDownToLimit extends Command {

	private LiftSubsystem lift;
	
    public LiftDownToLimit() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("Lift Down To Limit");
    	lift = lift.getInstance();
    	requires(lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lift.setPower(lift.LIFT_DOWN_POWER);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return lift.getBottom();
    }

    // Called once after isFinished returns true
    protected void end() {
    	lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
