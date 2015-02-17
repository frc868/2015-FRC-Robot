package com.techhounds.commands.auton;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Matthew Simons, Clayton Detke, Ayon Mitra, Alex Fleig
 */
public class AutonDrive extends Command {
	
	private static DriveSubsystem drive;
	
	public double distance;
	private double timeout; 

    public AutonDrive(double distance, double tol, double timeout) {
    	drive = DriveSubsystem.getInstance();
    	requires(drive);
    	drive.setDriveTolerance(tol);
    	this.distance = distance;
    	this.timeout = timeout;
    }
    
    public AutonDrive(double distance, double timeout) {
    	this(distance, 1, timeout);
    }

    protected void initialize() {
    	drive.setDrivePID(distance);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return drive.drivePIDOnTarget() || timeSinceInitialized() >= timeout;
    }

    protected void end(){
    	drive.stopDrivePID();
    }

    protected void interrupted() {
    	end();
    }
}
