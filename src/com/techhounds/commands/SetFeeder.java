package com.techhounds.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.FeederSubsystem;;

/**
 * @author Anonymous :1
 */
public class SetFeeder extends Command {
	
	public FeederSubsystem feed;
	
	public Double power;
	private Boolean position;

    public SetFeeder(double power, boolean position) {
        this(power);
        this.position = position;
    }
    
    public SetFeeder(double power){
    	feed = FeederSubsystem.getInstance();
    	requires(feed);
    	this.power = power;
    }
    
    public SetFeeder(boolean position){
    	feed = FeederSubsystem.getInstance();
    	requires(feed);
    	this.position = position;
    }

    protected void initialize() {
    	if (power != null)
    		feed.setPower(power);
    	if (position != null)
    		feed.setPosition(position);
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
