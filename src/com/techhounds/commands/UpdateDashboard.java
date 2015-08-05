package com.techhounds.commands;

import com.techhounds.OI;
import com.techhounds.subsystems.BasicSubsystem;
import com.techhounds.subsystems.CameraSubsystem;
import com.techhounds.subsystems.LEDSubsystem;

import edu.wpi.first.wpilibj.Joystick;
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
		setRunWhenDisabled(true);
		timer = new Timer();
	}

	protected void initialize() {
		timer.reset();
		timer.start();
	}

	protected void execute() {
		
		
		if(timer.get() >= 0.35) {
			for(BasicSubsystem sub : BasicSubsystem.subsystems)
				sub.updateSmartDashboard();
			LEDSubsystem.getInstance().standby();
	    	SmartDashboard.putNumber("Camera Pow", CameraSubsystem.getInstance().getDistValue());
	    	SmartDashboard.putNumber("Camera Steer", CameraSubsystem.getInstance().getOffsetValue());
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