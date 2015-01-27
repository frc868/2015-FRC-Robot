package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetDriveMode extends Command {
	
	private DriveSubsystem drive;
	boolean forward;
	boolean half;

    public SetDriveMode(boolean isForward, boolean isHalfSpeed) {
        drive = DriveSubsystem.getInstance();
        
        forward = isForward;
        half = isHalfSpeed;
    }
    
    public SetDriveMode(boolean isForward){
    	this(isForward, DriveSubsystem.getInstance().getHalfSpeed());
    }
    
    public SetDriveMode(boolean isHalfSpeed, int doesntMatter){
    	this(DriveSubsystem.getInstance().getDriveForward(), isHalfSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.setDriveMode(forward);
    	drive.setHalfSpeed(half);
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
