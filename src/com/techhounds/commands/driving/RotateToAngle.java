package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;
import com.techhounds.subsystems.GyroSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngle extends Command {

	private DriveSubsystem drive;
	private GyroSubsystem gyro;
	
	private double angle;
	private double timeout;
	
	public RotateToAngle(double angle, double timeout) {
		drive = DriveSubsystem.getInstance();
		gyro = GyroSubsystem.getInstance();
		
		this.angle = angle;
		this.timeout = timeout;
		
		requires(drive);
	}
	
	@Override
	protected void initialize() {
		drive.setGyroPID(gyro.getRotation() + angle);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return drive.gyroPIDOnTarget() || timeSinceInitialized() >= timeout;
	}

	@Override
	protected void end() {
		drive.stopGyroPID();
		drive.stopMotors();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
