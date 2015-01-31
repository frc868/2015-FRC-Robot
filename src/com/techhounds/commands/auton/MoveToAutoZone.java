package com.techhounds.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveToAutoZone extends CommandGroup {

	public MoveToAutoZone() {
		
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || !DriverStation.getInstance().isAutonomous();
	}
}
