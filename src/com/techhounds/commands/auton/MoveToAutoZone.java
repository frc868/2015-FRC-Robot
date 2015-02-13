package com.techhounds.commands.auton;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveToAutoZone extends Command {

	public static final double TIME = 1.5;
	
	private DriveSubsystem drive;
	private double time;
	private final double power = .75;
	private boolean noEnc = false;

	public MoveToAutoZone(double time) {
		drive = DriveSubsystem.getInstance();
		requires(drive);
		
		this.time = time;
	}
	
	public MoveToAutoZone(double time, boolean noEnc){
		this(time);
		this.noEnc = noEnc;
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