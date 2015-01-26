package com.techhounds.commands.bin;

import com.techhounds.subsystems.BinSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetBin extends Command {
	
	private BinSubsystem bin;
	private Integer direction;
	private Double power;
	private Boolean pos;

    public SetBin(int direction, double power) {
    	this(direction);
    	this.power = power;
    }
    
    public SetBin(Integer direction) {
    	bin = BinSubsystem.getInstance();
    	this.direction = direction;
    	power = BinSubsystem.LIFT_POWER;
    }
    
    public SetBin(boolean position){
    	this(null);
    	pos = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (direction != null)
    		bin.setBin(direction, power);
    	if (pos != null)
    		bin.setSolenoid(pos);
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
    	end();
    }
}
