package com.techhounds.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Anonymous :y
 */
public class Debug extends Command {

	String text;
	
    public Debug(String text) {
    	this.text = text;
    }

    protected void initialize() {
    	SmartDashboard.putString("Debug", text);
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
