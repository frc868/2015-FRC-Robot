package com.techhounds.commands.lift;

import com.techhounds.subsystems.PassiveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous :-{)
 */
public class SetPassiveStop extends Command {

	private PassiveSubsystem pass;
	
	private Boolean stopPos;
	private Boolean armPos;
	
    public SetPassiveStop(Boolean stopPos, Boolean armPos) {
    	pass = PassiveSubsystem.getInstance();
    	this.stopPos = stopPos;
    	this.armPos = armPos;
    }

    public SetPassiveStop(boolean stopPos){
    	this(stopPos, null);
    }
    
    public SetPassiveStop(boolean armPos, int dontCare){
    	this(null, armPos);
    }
    
    protected void initialize() {
    	if (stopPos != null)
    		pass.setStopPosition(stopPos);
    	if (armPos != null)
    		pass.setArmPosition(armPos);
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
