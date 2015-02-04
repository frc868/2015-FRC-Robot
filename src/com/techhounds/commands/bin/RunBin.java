package com.techhounds.commands.bin;

import edu.wpi.first.wpilibj.command.Command;
import com.techhounds.subsystems.BinSubsystem;

/**
 * @author Shaurya Doger
 */
public class RunBin extends Command {
	
	private BinSubsystem bin;

    public RunBin() {
    	bin = BinSubsystem.getInstance();
    	requires(bin);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if((bin.getDirection() == BinSubsystem.UP && !bin.isAtTop()) ||
        		(bin.getDirection() == BinSubsystem.DOWN && !bin.isAtBottom())){
    	}else{
    		bin.stopLift();
    	}
    	
    	bin.setPower();
    	
    	if (bin.isAtBottom())
    		bin.resetEncHeight();
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
