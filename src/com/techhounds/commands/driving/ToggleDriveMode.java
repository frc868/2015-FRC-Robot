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
	private boolean toggleTwo;
	
	public ToggleDriveMode(boolean toggleForward, boolean toggleHalf, boolean toggleTwoPerson){
		drive = DriveSubsystem.getInstance();
		
		this.toggleForward = toggleForward;
		this.toggleHalf = toggleHalf;
		this.toggleTwo = toggleTwoPerson;
	}

    protected void initialize() {
    	if (toggleForward)
        	drive.setDriveMode(!drive.getDriveForward());
        if (toggleHalf)
        	drive.setHalfSpeed(!drive.getHalfSpeed());
        if (toggleTwo)
        	drive.setTwoPersonDrive(!drive.getTwoPersonDrive());
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
