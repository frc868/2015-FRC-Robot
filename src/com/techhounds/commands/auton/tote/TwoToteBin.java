package com.techhounds.commands.auton.tote;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoToteBin extends CommandGroup {
	
	public TwoToteBin() {
		this(false);
	}
	
	public TwoToteBin(boolean collectBin) {
		
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || !DriverStation.getInstance().isAutonomous();
	}

}
