package com.techhounds.commands;

import com.techhounds.subsystems.BasicSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz
 */
public class UpdateDashboard extends Command {
	
	private Timer timer;
	
	public UpdateDashboard() {
		super("Update Dashboard");
		timer = new Timer();
	}

	protected void initialize() {
		timer.reset();
		timer.start();
	}

	protected void execute() {
		
		if(timer.get() >= 0.3) {
			for(BasicSubsystem sub : BasicSubsystem.subsystems)
				sub.updateSmartDashboard();
			timer.reset();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		
	}

	protected void interrupted() {	
		end();
	}
}