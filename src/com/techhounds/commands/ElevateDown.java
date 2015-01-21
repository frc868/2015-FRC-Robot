package com.techhounds.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.techhounds.subsystems.ElevatorSubsystem;

/**
 *
 */
public class ElevateDown extends Command {

	private ElevatorSubsystem elevate;
	
    public ElevateDown() {
        elevate = ElevatorSubsystem.getInstance();
        requires(elevate);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevate.startLoweringElevator();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !elevate.getBottomSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevate.stopElevator();
    	elevate.setLed(ElevatorSubsystem.ON);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
