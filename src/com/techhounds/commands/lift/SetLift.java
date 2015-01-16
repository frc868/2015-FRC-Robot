package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLift extends Command {
	
	public LiftSubsystem lift;
	public Integer direction;
	public Double power;
	public Boolean position;
	
    public SetLift(int direction) {
    	this(direction, LiftSubsystem.LIFT_POWER);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public SetLift(int direction, double pwr){
    	super("Set lift");
    	this.direction = direction;
    	lift = LiftSubsystem.getInstance();
    	this.power = pwr;
    }
    
    public SetLift(boolean position) {
    	lift = LiftSubsystem.getInstance();
    	this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (direction != null)
    		lift.setLift(direction, power);
    	if (position != null)
    		lift.setPosition(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
