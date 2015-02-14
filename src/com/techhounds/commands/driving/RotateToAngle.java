package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;
import com.techhounds.subsystems.GyroSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngle extends Command {

	private double angle;
	private DriveSubsystem drive;
	private GyroSubsystem gyro;
	
	public RotateToAngle(double angle) {
		drive = DriveSubsystem.getInstance();
		gyro = GyroSubsystem.getInstance();
		
		this.angle = angle;
		
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
		return drive.gyroPIDOnTarget();
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
