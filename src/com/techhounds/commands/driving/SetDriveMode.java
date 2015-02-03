package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous :6
 */
public class SetDriveMode extends Command {
	
	private DriveSubsystem drive;
	private boolean forward;
	private boolean half;
	private boolean two;

    public SetDriveMode(boolean isForward, boolean isHalfSpeed, boolean isTwoPerson) {
        drive = DriveSubsystem.getInstance();
        
        forward = isForward;
        half = isHalfSpeed;
        two = isTwoPerson;
    }
    
    public SetDriveMode(boolean isForward){
    	this(isForward, DriveSubsystem.getInstance().getHalfSpeed(), DriveSubsystem.getInstance().getTwoPersonDrive());
    }
    
    public SetDriveMode(boolean isHalfSpeed, int doesntMatter){
    	this(DriveSubsystem.getInstance().getDriveForward(), isHalfSpeed, DriveSubsystem.getInstance().getTwoPersonDrive());
    }
    
    public SetDriveMode(boolean isTwoPerson, boolean doesntMatter){
    	this(DriveSubsystem.getInstance().getDriveForward(), DriveSubsystem.getInstance().getHalfSpeed(), isTwoPerson);
    }

    protected void initialize() {
    	drive.setDriveMode(forward);
    	drive.setHalfSpeed(half);
    	drive.setTwoPersonDrive(two);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
