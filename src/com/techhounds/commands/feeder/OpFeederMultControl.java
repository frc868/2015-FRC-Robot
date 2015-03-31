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
    	
    	double x = OI.getOperatorRightXAxis();
    	double val = (Math.acos(x) - (Math.PI / 2)) * (-2 / Math.PI);//-1 to 1   left to right
    	
    	double left = FeederSubsystem.FEED_IN;
    	double right = FeederSubsystem.FEED_IN;
    	
    	if (val > 0)
    		left = FeederSubsystem.FEED_IN + Math.abs(FeederSubsystem.SLOW_FEED_OUT - FeederSubsystem.FEED_IN) * val;
    	if (val < 0)
    		right = FeederSubsystem.FEED_IN - Math.abs(FeederSubsystem.SLOW_FEED_OUT - FeederSubsystem.FEED_IN) * val;
    	
    	(new SetFeederMult(left, right)).start();
    	
//    	if (val < -.9){		//far left
//    		(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.SLOW_FEED_OUT)).start();
//    	}else if (val < -.3){	//left
//    		(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.STOPPED)).start();
//    	}else if (val < .3){	//straight
//    		(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN)).start();
//    	}else if (val < .9){	//right
//    		(new SetFeederMult(FeederSubsystem.STOPPED, FeederSubsystem.FEED_IN)).start();
//    	}else{					//far right
//    		(new SetFeederMult(FeederSubsystem.SLOW_FEED_OUT, FeederSubsystem.FEED_IN)).start();
//    	}
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
