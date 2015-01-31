package com.techhounds.commands.driving;

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

    protected void initialize() {
    	initTime = Timer.getFPGATimestamp();
    	drive.setPower(power);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - initTime >= time;
    }

    protected void end() {
    	drive.stopMotors();
    }

    protected void interrupted() {
    	end();
    }
}
