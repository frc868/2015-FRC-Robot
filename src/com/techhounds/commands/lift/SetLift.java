package com.techhounds.commands.lift;

import com.techhounds.subsystems.GyroSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous
 */
public class SetLift extends Command {
	
	public LiftSubsystem lift;
	public LiftSubsystem.Action direction;
	public double power;
	public Boolean grabPosition;
	
    public SetLift(LiftSubsystem.Action direction) {
    	this(direction, LiftSubsystem.LIFT_POWER);
    }
    
    public SetLift(LiftSubsystem.Action direction, double power){
    	super("Set Lift");
    	lift = LiftSubsystem.getInstance();
    	this.direction = direction;
    	this.power = power;
    }
    
    public SetLift(boolean grabPosition) {
    	lift = LiftSubsystem.getInstance();
    	this.grabPosition = grabPosition;
    }

    protected void initialize() {
    	
    	if (grabPosition != null)
    		lift.setGrabPosition(grabPosition);
    	
    	if(LiftSubsystem.OP_STICK_CONTROL){
    		return;
    	}else{
    		LiftSubsystem.DRIVER_CONTROL = true;
    	}
    	
    	if (direction != null){
    		if (LiftSubsystem.LIFT_BRAKING)
    			lift.setBrake(power == 0 || direction == LiftSubsystem.Action.STOPPED);
    		lift.setLift(direction, power);
    	}
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