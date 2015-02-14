package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualTurn extends Command {

	private DriveSubsystem drive;
	private double power;
	private boolean turnLeft;
	private double time;
	
    public ManualTurn(double power, double time, boolean turnLeft) {
    	drive = DriveSubsystem.getInstance();
    	requires(drive);
    	this.power = power;
    	this.time = time;
    	this.turnLeft = turnLeft;
    }

    protected void initialize() {
    	if (turnLeft)
    		power *= -1;
    	drive.setPower(power, -power);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return timeSinceInitialized() >= time;
    }

    protected void end() {
    	drive.stopMotors();
    }

    protected void interrupted() {
    	end();
    }
}