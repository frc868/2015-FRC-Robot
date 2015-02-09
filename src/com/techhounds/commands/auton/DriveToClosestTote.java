package com.techhounds.commands.auton;

import com.techhounds.subsystems.CameraSubsystem;
import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Anonymous :(
 */
public class DriveToClosestTote extends Command {

	DriveSubsystem drive;
	CameraSubsystem cam;
	
	private double power = 0;
	private double offset = 0;
	
    public DriveToClosestTote() {
    	drive = DriveSubsystem.getInstance();
    	cam = CameraSubsystem.getInstance();
    	requires(drive);
    	requires(cam);
    }

    protected void initialize() {
    }

    protected void execute() {
    	power = cam.getDistValue();
    	offset = cam.getOffsetValue();
    	
    	drive.setPower(power + offset, power - offset);
    }

    protected boolean isFinished() {
        return power < .1;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
