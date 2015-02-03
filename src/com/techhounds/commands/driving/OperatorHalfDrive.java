package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous :J
 */
public class OperatorHalfDrive extends Command {
	
	DriveSubsystem drive;
	boolean halfSpeed;

    public OperatorHalfDrive(boolean halfSpeed) {
        drive = DriveSubsystem.getInstance();
        this.halfSpeed = halfSpeed;
    }

    protected void initialize() {
    	if(halfSpeed){
    		drive.setOverrideOperator(drive.getHalfSpeed());
    		drive.setHalfSpeed(true);
    	}else{
    		if(!drive.getOverrideOperator())
    			drive.setHalfSpeed(false);
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
