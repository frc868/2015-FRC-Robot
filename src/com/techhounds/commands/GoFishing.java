package com.techhounds.commands;

import com.techhounds.subsystems.FishingPoleSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoFishing extends Command {
	
	private boolean position;
	private boolean toggle;
	private FishingPoleSubsystem fish;
	
	public GoFishing() {
		fish = FishingPoleSubsystem.getInstance();
		requires(fish);
		toggle = true;
	}
	
	public GoFishing(boolean position) {
		fish = FishingPoleSubsystem.getInstance();
		requires(fish);
		this.position = position;
	}

	@Override
	protected void initialize() {
		if(toggle)
			position = !fish.getPos();
		fish.setPos(position);
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
		end();
	}
}
