/* Ayon Mitra, Matt Simons, the interns, the ex-intern
 * after school
 * 1/14/15
 * This program will auton lift, without user input.  It is impossible for the user to leave
 * this program.  Only the artificially intelligent mind of the robot will decide when to leave,
 * unless the drivers press the stop button.
*/

package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class AutonLift extends Command {
	
	public LiftSubsystem lift;
	boolean goingUp = true;
	boolean goingDown = false;
	public int direction;

    public AutonLift() {
        lift = LiftSubsystem.getInstance();
        requires(lift);
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (goingUp) {
    		lift.power = lift.LIFT_POWER;
    		lift.setPower();
        	if (lift.isAtTop())
        		lift.stopLift();
    	}
    	
    	if (goingDown) {
    		lift.power = -lift.LIFT_POWER;
    		lift.setPower();
    		if (lift.isAtBottom()) {
    			lift.stopLift();
    		}
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
