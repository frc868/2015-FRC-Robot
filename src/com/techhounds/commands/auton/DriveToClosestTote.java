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
	private Double minTime;
	
    public DriveToClosestTote() {
    	drive = DriveSubsystem.getInstance();
    	cam = CameraSubsystem.getInstance();
    	requires(drive);
    	requires(cam);
    }

    public DriveToClosestTote(double minTime){
    	this();
    	this.minTime = minTime;
    }
    
    protected void initialize() {
    	
    }

    protected void execute() {
    	power = cam.getDistValue();
    	offset = cam.getOffsetValue();
    	
    	drive.setPower(power + offset, (power - offset) * .85);
    }

    protected boolean isFinished() {
        return minTime == null ? power < .1 : timeSinceInitialized() >= minTime && power < .1;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
