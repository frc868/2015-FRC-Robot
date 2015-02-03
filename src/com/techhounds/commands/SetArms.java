package com.techhounds.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.ArmsSubsystem;;

/**
 * @author Anonymous :1
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

    protected void initialize() {
    	if (power != null)
    		arms.setPower(power);
    	if (position != null)
    		arms.setPosition(position);
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
