package com.techhounds.commands.feeder;

import com.techhounds.OI;
import com.techhounds.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpFeederMultControl extends Command {

	public static OpFeederMultControl instance;
	
	public static OpFeederMultControl getInstance(){
		if (instance == null)
			instance = new OpFeederMultControl();
		return instance;
	}
	
	private FeederSubsystem feed;
	
    private OpFeederMultControl() {
    	feed = FeederSubsystem.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!OI.opFeedMode)
    		return;
    	
    	double val = OI.getOperatorRightXAxis();
    	
    	if (val < -.75){		//far left
    		(new SetFeederMult(FeederSubsystem.FEED_OUT, FeederSubsystem.FEED_IN)).start();
    	}else if (val < -.25){	//left
    		(new SetFeederMult(FeederSubsystem.STOPPED, FeederSubsystem.FEED_IN)).start();
    	}else if (val < .25){	//straight
    		(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN)).start();
    	}else if (val < .75){	//right
    		(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.STOPPED)).start();
    	}else{					//far right
    		(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_OUT)).start();
    	}
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
