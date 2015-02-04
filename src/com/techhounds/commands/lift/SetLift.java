package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous
 */
public class SetLift extends Command {
	
	public LiftSubsystem lift;
	public Integer direction;
	public Double power;
	public Boolean grabPosition;
	public Boolean brakePosition;
	
    public SetLift(int direction) {
    	this(direction, LiftSubsystem.LIFT_POWER);
    }
    
    public SetLift(int direction, double pwr){
    	super("Set lift");
    	this.direction = direction;
    	lift = LiftSubsystem.getInstance();
    	this.power = pwr;
    }
    
    public SetLift(boolean grabPosition) {
    	lift = LiftSubsystem.getInstance();
    	this.grabPosition = grabPosition;
    }
    
    public SetLift(boolean brakePosition, int dontCare){
    	lift = LiftSubsystem.getInstance();
    	this.brakePosition = brakePosition;
    }

    protected void initialize() {
    	if (direction != null){
    		lift.setLift(direction, power);
    		lift.setBrake(power == 0 || direction == LiftSubsystem.STOPPED);
    	}
    	if (grabPosition != null)
    		lift.setGrabPosition(grabPosition);
    	if (brakePosition != null)
    		lift.setBrakePosition(brakePosition);
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
