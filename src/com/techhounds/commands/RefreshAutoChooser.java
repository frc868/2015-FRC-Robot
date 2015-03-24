package com.techhounds.commands;

import com.techhounds.OI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RefreshAutoChooser extends Command {

    private int count;

	public RefreshAutoChooser() {
    	setRunWhenDisabled(true);
    	count = 0;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.getInstance().autonChoice = OI.getInstance().createAutonChoices();
    	count++;
    	SmartDashboard.putNumber("Refresh Count", count);
    	System.out.println("Refresh Count " + count);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
