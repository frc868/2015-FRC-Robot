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
	
    private OpFeederMultControl() {

    }

    protected void initialize() {
    	
    }

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
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}