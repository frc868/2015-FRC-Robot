package com.techhounds.commands.auton.tote;

import com.techhounds.subsystems.CameraSubsystem;
import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToClosestTote extends Command {

	DriveSubsystem drive;
	CameraSubsystem cam;
	
	private double power = 0;
	
    public DriveToClosestTote() {
    	drive = DriveSubsystem.getInstance();
    	cam = CameraSubsystem.getInstance();
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	power = cam.getDistValue();
    	double offset = cam.getOffsetValue();
    	
    	drive.setPower(power - offset, -(power + offset));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return power < .1;
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
