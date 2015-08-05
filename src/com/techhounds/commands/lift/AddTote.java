package com.techhounds.commands.lift;

import com.techhounds.commands.Debug;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.feeder.SetFeederNormal;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AddTote extends CommandGroup {

	public AddTote() {
		this.setInterruptible(false);
		addParallel(new MakeTote());
	}
	
	public static class KeepToteIn extends CommandGroup {
		
		public KeepToteIn() {
			addSequential(new SetFeederNormal(FeederSubsystem.CLOSED));
			addSequential(new SetFeederNormal(FeederSubsystem.FEED_IN));
			addSequential(new WaitCommand(1));
			addSequential(new SetFeederNormal(FeederSubsystem.STOPPED));
		}
	}
	
	public static class MakeTote extends CommandGroup {
		
		public MakeTote() {
			addSequential(new SetLiftHeight(1.1, LiftSubsystem.Action.DOWN, (new Boolean(true))));
			addSequential(new Debug("SetLiftHeight 1"));
			addSequential(new SetLift(LiftSubsystem.OPEN));
			addSequential(new Debug("Set lift Open 2"));
			addSequential(new SetLift(LiftSubsystem.Action.DOWN));
			addSequential(new Debug("Set Lift Down 3"));
			addSequential(new WaitForLiftSwitch(LiftSubsystem.Action.DOWN));
			addSequential(new Debug("Wait for lift 4"));
			addSequential(new WaitCommand(.15));
			addSequential(new Debug("Wait 5"));
			addSequential(new SetFeederNormal(FeederSubsystem.OPEN));
			addSequential(new Debug("set feeder 6"));
			addSequential(new SetLift(LiftSubsystem.CLOSED));
			addSequential(new Debug("setLift closed 7"));
			addSequential(new WaitCommand(.1));
			addSequential(new Debug("wait 8"));
			addSequential(new SetLiftHeight(1.33, 0.9));
			addSequential(new Debug("set lift height 9"));
		}
	}
}
