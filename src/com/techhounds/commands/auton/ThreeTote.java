package com.techhounds.commands.auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeTote extends CommandGroup {
	
	public ThreeTote() {
		addSequential(new FirstTote());
		addSequential(new NextTote(false));
		addSequential(new NextTote(true));
	}
}
