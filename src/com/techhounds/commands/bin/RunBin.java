package com.techhounds.commands.bin;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.BinSubsystem;

/*Author Shaurya doger*/

public class RunBin extends Command {
	
	private BinSubsystem bin;
	private boolean dir;

    public RunBin() {
    	bin = BinSubsystem.getInstance();
    	requires(bin);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(bin.getDirection() == BinSubsystem.UP && dir == !bin.isAtTop() ||
    		bin.getDirection() == BinSubsystem.DOWN && dir == !bin.isAtBottom()){
    	}else{
    		bin.stopLift();
    	}
    	bin.setPower();
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
    	end();
    }
}
