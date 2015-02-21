package com.techhounds.commands;

import com.techhounds.PlaystationMap;

import edu.wpi.first.wpilibj.command.Command;

public class Rumble extends Command {
	
	private PlaystationMap joystick;
	private double time = 1;
	
	public Rumble(PlaystationMap joystick) {
		this.joystick = joystick;
	}
	
	public Rumble(PlaystationMap joystick, double time) {
		this(joystick);
		this.time = time;
	}
	
	public void initialize() {
		joystick.startRumble();
	}
	
	public void execute() {
		
	}
	
	public boolean isFinished() {
		return timeSinceInitialized() >= time;
	}
	
	public void end() {
		joystick.stopRumble();
	}

	public void interrupted() {
		end();
	}
}
