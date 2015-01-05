package com.techhounds.commands;

import com.techhounds.util.BasicSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Atif Niyaz
 */
public class UpdateDashboard extends Command {
	
	private Timer timer;
	
	public UpdateDashboard() {
		super("Update Dashboard");
		timer = new Timer();
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		
		if(timer.get() >= 0.3) {
			for(BasicSubsystem sub : BasicSubsystem.subsystems)
				sub.updateSmartDashboard();
			
			timer.reset();
		}
	}

	@Override
	protected boolean isFinished() { 	return false;	}

	@Override
	protected void end() {	}

	@Override
	protected void interrupted() {	}

}
