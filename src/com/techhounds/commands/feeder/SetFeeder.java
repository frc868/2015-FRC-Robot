package com.techhounds.commands.feeder;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    			setPower(feed.getPower() != FeederSubsystem.STOPPED ? FeederSubsystem.STOPPED : power);
    		} else {
    			feed.setPosition(!feed.getPosition());
    			power = feed.getPower();
    		}
    	} else {
	    	if (power != null)
	    		setPower(power);
	    	else 
	    		power = feed.getPower();
	    	
	    	if (position != null)
	    		feed.setPosition(position);
    	}
    }

    protected void execute() {
    	setPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
    
    private void setPower(double power){
    	SmartDashboard.putBoolean("Feed Left Bool", feed.getLeftSensorInRange());
    	SmartDashboard.putBoolean("Feed Right Bool", feed.getRightSensorInRange());
    	SmartDashboard.putNumber("Feed Power", power);
    	if (feed.getLeftSensorInRange() && feed.getRightSensorInRange() && power * FeederSubsystem.FEED_IN > 0){
    		feed.stopArms();
    	}else{
    		feed.setPower(power);
    	}
    }
}
