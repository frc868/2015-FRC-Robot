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

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		SmartDashboard.putString("Update DB", "Init");
	}

	@Override
	protected void execute() {
		
		if(timer.get() >= 0.3) {
			for(BasicSubsystem sub : BasicSubsystem.subsystems)
				sub.updateSmartDashboard();
			SmartDashboard.putString("Update DB", "Count: " + BasicSubsystem.subsystems.size());
			timer.reset();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		SmartDashboard.putString("Update DB", "End");
	}

	@Override
	protected void interrupted() {	}

}
