package com.techhounds.commands;

import com.techhounds.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/*
 * Author Shaurya Doger*/

public class DriveTime extends Command {
	
	private DriveSubsystem drive;
	private double time;
	private double power;
	
	private double initTime;

    public DriveTime(double time, double power) {
        super("Drive Time");
        drive = DriveSubsystem.getInstance();
        this.time = time;
        this.power = power;
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initTime = Timer.getFPGATimestamp();
    	drive.setPower(power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - initTime >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
