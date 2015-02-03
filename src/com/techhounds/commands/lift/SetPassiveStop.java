package com.techhounds.commands.lift;

import com.techhounds.subsystems.PassiveStopSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous :-{)
 */
public class SetPassiveStop extends Command {

	private PassiveStopSubsystem stop;
	
	private boolean pos;
	
	public SetPassiveStop(){
		this(!PassiveStopSubsystem.getInstance().getPosition());
	}
	
    public SetPassiveStop(boolean pos) {
    	stop = PassiveStopSubsystem.getInstance();
    	this.pos = pos;
    }

    protected void initialize() {
    	stop.setPosition(pos);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
