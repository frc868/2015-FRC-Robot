package com.techhounds.commands.bin;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.BinSubsystem;

/*Author Shaurya doger*/

public class RunBin extends Command {
	
	private BinSubsystem bin;
	private double power;
	private boolean dir;

    public RunBin(double power) {
    	bin = BinSubsystem.getInstance();
    	requires(bin);
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(bin.getDirection() == BinSubsystem.UP && !bin.)
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
