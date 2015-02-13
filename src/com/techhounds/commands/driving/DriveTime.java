package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author Shaurya Doger
 */
public class DriveTime extends Command {
	
	private DriveSubsystem drive;
	private double time;
	private double power;
	private boolean stopAtEnd;
	private double deccelTime;

    public DriveTime(double time, double power, boolean stopAtEnd) {
        super("Drive Time");
        drive = DriveSubsystem.getInstance();
        this.time = time;
        this.power = power;
        this.stopAtEnd = stopAtEnd;
        deccelTime = 0;
        requires(drive);
    }
    
    public DriveTime(double time, double power, double deccelTime){
    	this(time, power, true);
    	this.deccelTime = deccelTime;
    }

    protected void initialize() {
    	drive.setPower(power, power * .9);
    }

    protected void execute() {
    	if (timeSinceInitialized() >= time - deccelTime){
    		double pow = power * ((time - timeSinceInitialized()) / deccelTime);
    		drive.setPower(pow, pow * .9);
    	}
    }

    protected boolean isFinished() {
        return timeSinceInitialized() >= time;
    }

    protected void end() {
    	if (stopAtEnd)
    		drive.stopMotors();
    }

    protected void interrupted() {
    	end();
    }
}