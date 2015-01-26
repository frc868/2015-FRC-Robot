package com.techhounds.commands.bin;

import com.techhounds.subsystems.BinSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetBin extends Command {
	
	private BinSubsystem bin;
	private int direction;
	private Double power;

    public SetBin(int direction, Double power) {
    	super("Set Bin");
    	bin = BinSubsystem.getInstance();
    	this.direction = direction;
    	this.power = power;
    }
    
    public SetBin(int direction) {
    	this(direction, null);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	bin.direction = direction;
    	if (power != null)
    		bin.power = power;
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
