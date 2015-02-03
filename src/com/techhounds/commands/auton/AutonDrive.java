package com.techhounds.commands.auton;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Matthew Simons, Clayton Detke, Ayon Mitra, Alex Fleig
 */
public class AutonDrive extends Command {
	
	private static DriveSubsystem drive;
	
	public double distance;

    public AutonDrive(double distance, double tol) {
    	drive = DriveSubsystem.getInstance();
    	requires(drive);
    	drive.setDriveTolerance(tol);
    	this.distance = distance;
    }
    
    public AutonDrive(double distance) {
    	this(distance, 1);
    }

    protected void initialize() {
    	drive.setDrivePID(distance);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return drive.drivePIDOnTarget();
    }

    protected void end(){
    	drive.stopDrivePID();
    }

    protected void interrupted() {
    	end();
    }
}
