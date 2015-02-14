package com.techhounds.commands.auton;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetWatchTilt extends Command {

	private boolean shouldWatch;
	
	public SetWatchTilt(boolean shouldWatch) {
		this.shouldWatch = shouldWatch;
	}
	
	@Override
	protected void initialize() {
		LiftSubsystem.getInstance().setWatchForTilt(shouldWatch);
	}

	@Override
	protected void execute() {	
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
