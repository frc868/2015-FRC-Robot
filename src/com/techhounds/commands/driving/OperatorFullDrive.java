package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous :J
 */
public class OperatorFullDrive extends Command {
	
	private DriveSubsystem drive;
	private boolean fullSpeed;

    public OperatorFullDrive(boolean fullSpeed) {
        drive = DriveSubsystem.getInstance();
        this.fullSpeed = fullSpeed;
    }

    protected void initialize() {
    	if(fullSpeed){
    		if(!drive.getOverrideOperator())
    			drive.setHalfSpeed(false);
    	}else{
    		drive.setOverrideOperator(drive.getHalfSpeed());
    		drive.setHalfSpeed(true);
    	}
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
