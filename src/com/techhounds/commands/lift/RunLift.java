package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	
	private LiftSubsystem lift;
	
	public RunLift() {
		lift = LiftSubsystem.getInstance();
		requires(lift);
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
