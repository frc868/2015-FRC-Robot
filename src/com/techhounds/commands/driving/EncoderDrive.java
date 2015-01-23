package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	
	DriveSubsystem drive;
	
	private double dist;
	private double leftPower;
	private double rightPower;

    public EncoderDrive(double dist, double power) {
       drive = DriveSubsystem.getInstance();
       this.dist = dist;
       leftPower = power;
       rightPower = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.setPower(leftPower, rightPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drive.getLeftDistance() >= dist;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
