package com.techhounds.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.ArmsSubsystem;;

/**
 *
 */
public class SetArms extends Command {
	
	public ArmsSubsystem arms;
	public Double power;
	
	private Boolean position;

    public SetArms(double power, boolean position) {
        this(power);
        this.position = position;
    }
    
    public SetArms(double power){
    	arms = ArmsSubsystem.getInstance();
    	requires(arms);
    	this.power = power;
    }
    
    public SetArms(boolean position){
    	arms = ArmsSubsystem.getInstance();
    	requires(arms);
    	this.position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (power != null)
    		arms.setPower(power);
    	if (position != null)
    		arms.setPosition(position);
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
