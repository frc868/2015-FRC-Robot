package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForLiftSwitch extends Command {

	private LiftSubsystem lift;
	private LiftSubsystem.Action dir;
	
    public WaitForLiftSwitch(LiftSubsystem.Action direction) {
    	lift = LiftSubsystem.getInstance();
    	dir = direction;
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return dir == LiftSubsystem.Action.UP ? lift.isAtTop() : lift.isAtBottom();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}