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
	private Boolean toggle;
	
    public SetPassiveStop(Boolean stopPos, Boolean armPos, Boolean toggle) {
    	pass = PassiveSubsystem.getInstance();
    	this.stopPos = stopPos;
    	this.armPos = armPos;
    	this.toggle = toggle;
    }
    
    public SetPassiveStop(boolean stopPos){
    	this(stopPos, null, null);
    }
    
    public SetPassiveStop(boolean armPos, int dontCare){
    	this(null, armPos, null);
    }

    public SetPassiveStop(boolean stopPos, boolean toggle){
    	this(stopPos, null, toggle);
    }
    
    public SetPassiveStop(boolean armPos, boolean toggle, int dontCare){
    	this(null, armPos, toggle);
    }
    
    protected void initialize() {
    	
    	if(toggle != null) {
    		if(stopPos != null)
    			stopPos = !pass.getStopPosition();
    		if(armPos != null)
    			armPos = !pass.getArmPosition();
    	}
    	
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
