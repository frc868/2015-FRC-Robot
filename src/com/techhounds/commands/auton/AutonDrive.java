package com.techhounds.commands.auton;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Matthew Simons, Clayton Detke, Ayon Mitra, Alex Fleig
 */
public class AutonDrive extends Command {
	
	private static DriveSubsystem drive;
	
	public double distance;

    public AutonDrive(double distance, double tol) {
    	drive = DriveSubsystem.getInstance();
    	requires(drive);
    	drive.setTolerance(tol);
    	this.distance = distance;
    }
    
    public AutonDrive(double distance) {
    	this(distance, 1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.driveWithEncoder(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drive.drivePIDOnTarget();
    }

    // Called once after isFinished returns true
    protected void end(){
    	drive.stopDriveWithEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
