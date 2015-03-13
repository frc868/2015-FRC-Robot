package com.techhounds.commands.lift;

import com.techhounds.subsystems.GyroSubsystem;
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

    protected void initialize() {
    	if (direction != null){
    		lift.setBrake(power == 0 || direction == LiftSubsystem.STOPPED);
    		lift.setLift(direction, power);
    		lift.setCmdRunning(power != 0 && direction != LiftSubsystem.STOPPED);
    		GyroSubsystem.getInstance().resetGyro();
    	}
    	if (grabPosition != null)
    		lift.setGrabPosition(grabPosition);
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
