package com.techhounds.commands.feeder;

import edu.wpi.first.wpilibj.command.Command;

import com.techhounds.subsystems.FeederSubsystem;;

/**
 * @author Anonymous :1
 */
public class SetFeeder extends Command {
	
	public FeederSubsystem feed;
	
	public Double left, right;
	private Boolean position;
	private boolean powSet = false;
	
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
    	this(power, power);
    }
    
    public SetFeeder(double left, double right){
    	this();
    	this.left = left;
    	this.right = right;
    	powSet = true;
    }
    
    public SetFeeder(boolean position){
    	this();
    	this.position = position;
    }
    
    protected void initialize() {
    	
    	if (powSet)
    		setPower(left, right);
    	else{
    		left = feed.getLeftPower();
    		right = feed.getRightPower();
    	}
    	
    	if (position != null)
    		feed.setPosition(position);
    }

    protected void execute() {
    	setPower(left, right);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
    
    private void setPower(double left, double right){
//    	SmartDashboard.putBoolean("Feed Left Bool", feed.getLeftSensorInRange());
//    	SmartDashboard.putBoolean("Feed Right Bool", feed.getRightSensorInRange());
//    	SmartDashboard.putNumber("Feed Power", left);
    	if (feed.getLeftSensorInRange() && feed.getRightSensorInRange() && left * FeederSubsystem.FEED_IN > 0){
    		left = 0;
    	}
    	if (feed.getLeftSensorInRange() && feed.getRightSensorInRange() && right * FeederSubsystem.FEED_IN > 0){
    		right = 0;
    	}
		feed.setPower(left, right);
    }
}