package com.techhounds.commands.lift;
//victor, 2 limit switches = digital input high / low final booleans, COMMANDS: up till limit, down till limit, stop
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDownToLimit extends Command {

	private LiftSubsystem lift;
	private boolean lower = lift.getTop();
	
    public LiftDownToLimit() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("Lift Down To Limit");
    	lift = lift.getInstance();
    	requires(lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while (lower == false) {
    		lift.setPower(1);
    	}
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
