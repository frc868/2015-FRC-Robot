package com.techhounds.commands.feeder;

import com.techhounds.OI;
import com.techhounds.commands.lift.AddTote;
import com.techhounds.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class OpFeederMultControl extends Command {

	public static OpFeederMultControl instance;
	private FeederSubsystem feeder;
	
	public static OpFeederMultControl getInstance(){
		if (instance == null)
			instance = new OpFeederMultControl();
		return instance;
	}
	
	public static class GetTote extends CommandGroup {
		
		public static GetTote instance;
		
		public static GetTote getInstance() {
			if(instance == null)
				instance = new GetTote();
			return instance;
		}
		
		public GetTote() {
			addSequential(new SetFeederNormal(FeederSubsystem.FEED_IN, true));
			addSequential(new WaitCommand(.25));
			addSequential(new SetFeederNormal(FeederSubsystem.CLOSED));
			addSequential(new WaitCommand(.5));
			addSequential(new SetFeederNormal(0, true));
			addSequential(AddTote.getInstance());
		}
		
		public boolean isFinished() {
			return super.isFinished(); 
		}
	}
	
    private OpFeederMultControl() {
    	feeder = FeederSubsystem.getInstance();
    }

    protected void initialize() {
    	GetTote.getInstance().start();
    	GetTote.getInstance().cancel();
    }

    protected void execute() {
    	
    	if (!OI.opFeedMode)
    		return;
    	
    	if(feeder.getLeftDistance() < 2 && feeder.getRightDistance() < 2) {
    		if(GetTote.getInstance().isFinished())
    			GetTote.getInstance().start();
    	}
    	
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