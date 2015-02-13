package com.techhounds.commands.auton;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToAutoZone extends Command {

	public static final double TIME = 1.5;
	
	private DriveSubsystem drive;
	private double time;
	private final double power = .75;
	private boolean noEnc = false;
	private double deccelTime = 0;

	public MoveToAutoZone(double time) {
		drive = DriveSubsystem.getInstance();
		requires(drive);
		
		this.time = time;
	}
	
	public MoveToAutoZone(double time, boolean noEnc){
		this(time);
		this.noEnc = noEnc;
	}
	
	public MoveToAutoZone(double time, double deccelTime){
    	this(time, true);
    	this.deccelTime = deccelTime;
    }
	
	public MoveToAutoZone(boolean noEnc){
		this(TIME, noEnc);
	}

	protected void initialize() {
		if (noEnc)
			drive.setPower(power, power * .6);
		else
			drive.setPower(power);
	}

	protected void execute() {
		if (timeSinceInitialized() >= time - deccelTime){
    		double pow = power * ((time - timeSinceInitialized()) / deccelTime);
    		drive.setPower(pow, pow * .6);
    	}
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