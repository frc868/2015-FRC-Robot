package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithGamepad extends Command {
	
	DriveSubsystem drive;
    
	public DriveWithGamepad() {
		drive = DriveSubsystem.getInstance();
    	requires(drive);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	drive.driveWithGamepad();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
