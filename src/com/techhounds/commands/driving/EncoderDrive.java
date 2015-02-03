package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous ;O
 */
public class EncoderDrive extends Command {
	
	DriveSubsystem drive;
	
	private double dist;
	private double leftPower;
	private double rightPower;

    public EncoderDrive(double dist, double power) {
       drive = DriveSubsystem.getInstance();
       this.dist = dist;
       leftPower = power;
       rightPower = power;
    }

    protected void initialize() {
    	drive.setPower(leftPower, rightPower);
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return drive.getLeftDistance() >= dist;
    }

    protected void end() {
    	drive.setPower(0);
    }

    protected void interrupted() {
    	end();
    }
}
