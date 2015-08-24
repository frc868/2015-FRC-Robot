package com.techhounds.commands.feeder;

import edu.wpi.first.wpilibj.command.Command;

import com.techhounds.subsystems.FeederSubsystem;;

/**
 * @author Anonymous :1
 */
public class SetFeederNormal extends Command {
	
	public FeederSubsystem feed;
	
	private Double leftPower;
	private Double rightPower;
	
	private Boolean position;
	private Boolean isToFinish;
	
	public SetFeederNormal() {
		feed = FeederSubsystem.getInstance();
		setInterruptible(true);
		requires(feed);
	}
	
	public SetFeederNormal(double leftPower, double rightPower) {
		this();
		this.leftPower = leftPower;
		this.rightPower = rightPower;
		this.position = null;
		this.isToFinish = null;
	}
	
	public SetFeederNormal(boolean position) {
		this();
		this.leftPower = null;
		this.rightPower = null;
		this.position = position;
		this.isToFinish = true;
		
	}
	
	public SetFeederNormal(double power) {
		this(power, power);
		this.isToFinish = null;
	}

	public SetFeederNormal(double power, boolean isToFinish) {
		this(power, power);
		this.isToFinish = isToFinish;
		
	}
	@Override
	protected void initialize() {
		if(leftPower != null && rightPower != null) 
    		feed.setPower(leftPower, rightPower);
    	
    	if(position != null)
    		feed.setPosition(position);
	}

    protected void execute() {
    	if(leftPower != null && rightPower != null) 
    		feed.setPower(leftPower, rightPower);
    }

    protected boolean isFinished() {
    	return false || isToFinish != null;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }

	
}