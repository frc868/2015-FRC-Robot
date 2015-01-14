package com.techhounds.commands;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class InvertDrive extends Command {
	
	public DriveSubsystem drive;

    public InvertDrive() {
        drive = DriveSubsystem.getInstance();
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.invert();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
