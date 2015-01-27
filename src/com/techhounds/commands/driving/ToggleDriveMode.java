package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleDriveMode extends Command {
	
	private DriveSubsystem drive;
	private boolean toggleForward;
	private boolean toggleHalf;
	
	public ToggleDriveMode(boolean toggleForward, boolean toggleHalf){
		drive = DriveSubsystem.getInstance();
		
		this.toggleForward = toggleForward;
		this.toggleHalf = toggleHalf;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(toggleForward)
        	drive.setDriveMode(!drive.getDriveForward());
        if(toggleHalf)
        	drive.setHalfSpeed(!drive.getHalfSpeed());
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
