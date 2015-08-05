package com.techhounds.commands.liftold;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForLiftSwitch extends Command {

	private LiftSubsystem lift;
	private int dir;
	
    public WaitForLiftSwitch(int direction) {
    	lift = LiftSubsystem.getInstance();
    	dir = direction;
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return dir == LiftSubsystem.UP ? lift.isAtTop() : lift.isAtBottom();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}