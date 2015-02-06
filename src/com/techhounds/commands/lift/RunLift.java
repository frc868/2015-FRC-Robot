package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;
import com.techhounds.subsystems.PassiveStopSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	
	private LiftSubsystem lift;
	private PassiveStopSubsystem stop;
	
	public RunLift() {
		lift = LiftSubsystem.getInstance();
		stop = PassiveStopSubsystem.getInstance();
		requires(lift);
		requires(stop);
		setInterruptible(false);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		if ((lift.getDirection() == LiftSubsystem.UP && !lift.isAtTop()) ||
				(lift.getDirection() == LiftSubsystem.DOWN && !lift.isAtBottom())){
		} else {
			lift.stopLift();
		}
		
		if (lift.getBraked()){
			double diff = lift.getBrakeHeight() - lift.getEncHeight();
			double pow = diff > 0 ? (diff * 4) * LiftSubsystem.LIFT_POWER : 0;
			int dir = diff > 0 ? LiftSubsystem.UP : LiftSubsystem.DOWN;
			lift.setLift(dir, pow);
			stop.setPosition(PassiveStopSubsystem.STOP);
		}else{
			stop.setPosition(PassiveStopSubsystem.OPEN);
		}
		
		if (lift.isAtBottom()){
			lift.resetEncHeight();
			lift.setBrakeHeight(0);
		}
			
		lift.setPower();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		
	}
}
