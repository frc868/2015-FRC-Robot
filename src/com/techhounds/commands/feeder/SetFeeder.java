package com.techhounds.commands.feeder;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.FeederSubsystem;;

/**
 * @author Anonymous :1
 */
public class SetFeeder extends Command {
	
	public FeederSubsystem feed;
	
	public Double power;
	private Boolean position, toggle;
	
	private SetFeeder(){
		setInterruptible(true);
    	feed = FeederSubsystem.getInstance();
    	requires(feed);
	}
	
    public SetFeeder(double power, boolean position) {
        this(power);
        this.position = position;
    }
    
    public SetFeeder(double power){
    	this();
    	this.power = power;
    }
    
    public SetFeeder(boolean position){
    	this();
    	this.position = position;
    }
    
    public SetFeeder(boolean toggle, boolean doesntMatter) {
    	this();
    	this.toggle = toggle;
    }
    
    public SetFeeder(boolean toggle, double direction) {
    	this(direction);
    	this.toggle = toggle;
    }
    
    protected void initialize() {
    	
    	if(toggle != null && toggle) {
    		if(power != null) {
    			feed.setPower(feed.getPower() != FeederSubsystem.STOPPED ? FeederSubsystem.STOPPED : power);
    		} else {
    			feed.setPosition(!feed.getPosition());
    		}
    	} else {
	    	if (power != null)
	    		feed.setPower(power);
	    	if (position != null)
	    		feed.setPosition(position);
    	}
    }

    protected void execute() {
    	if (feed.getLeftSensorInRange() && feed.getRightSensorInRange()){
    		feed.stopArms();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
