package com.techhounds.commands;

import com.techhounds.subsystems.CameraSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForToteLift extends Command {

	private CameraSubsystem camera;
	
    public WaitForToteLift() {
    	camera = CameraSubsystem.getInstance();
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return timeSinceInitialized() > 2 || camera.getDistValue() > .25;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}