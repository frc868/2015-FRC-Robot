package com.techhounds.commands.auton;

import com.techhounds.Robot;
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
	
	private int badFrames = 0;
	private boolean finished = false;
	
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
    	power = cam.getDistValue() * 4 / 3;				//getDistValueis .3
    	offset = cam.getOffsetValue() * 10;       //getOffsetValue is .1
    	
    	if (Robot.isFinal())
    		drive.setPower((power + offset) * .9, power - offset);
    	else
    		drive.setPower(power + offset, (power - offset) * .9);
    	
    	if (power < .1){
    		badFrames++;
    		if (badFrames > 2)
    			finished = true;
    	}else
    		badFrames = 0;
    }

    protected boolean isFinished() {
        return minTime == null ? finished : timeSinceInitialized() >= minTime && finished;
    }

    protected void end() {
    	drive.setPower(.05);
    }

    protected void interrupted() {
    	end();
    }
}