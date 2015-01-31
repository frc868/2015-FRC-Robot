package com.techhounds.commands.auton.tote;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneToteBin extends CommandGroup {
	
	public OneToteBin() {
		this(false);
	}
	
	public OneToteBin(boolean collectBin) {
		
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || !DriverStation.getInstance().isAutonomous();
	}

}
