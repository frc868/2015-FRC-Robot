package com.techhounds.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Push extends CommandGroup {

	public Push() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || !DriverStation.getInstance().isAutonomous();
	}
}
