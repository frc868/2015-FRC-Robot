package com.techhounds.commands.auton.tote;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeToteBin extends CommandGroup {
	
	public ThreeToteBin() {
		this(false);
	}
	
	public ThreeToteBin(boolean collectBin) {
		
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || !DriverStation.getInstance().isAutonomous();
	}

}
