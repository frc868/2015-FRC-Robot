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
	public boolean goingUp;

    public AutonLift(int direction) {
        lift = LiftSubsystem.getInstance();
        requires(lift);
        
        goingUp = direction == LiftSubsystem.UP;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (goingUp){
    		lift.setLift(LiftSubsystem.UP, LiftSubsystem.LIFT_POWER);
    	}else{
    		lift.setLift(LiftSubsystem.DOWN, LiftSubsystem.LIFT_POWER);    		
    	}
    	lift.setPower();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (goingUp){
        	return lift.isAtTop();
        }
        return lift.isAtBottom();
    }

    // Called once after isFinished returns true
    protected void end() {
    	lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
