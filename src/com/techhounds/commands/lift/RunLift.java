package com.techhounds.commands.lift;

import com.techhounds.subsystems.GyroSubsystem;
import com.techhounds.subsystems.LiftSubsystem;
import com.techhounds.subsystems.PassiveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	
	private LiftSubsystem lift;
	private PassiveSubsystem stop;
	private GyroSubsystem gyro;
	
	public RunLift() {
		lift = LiftSubsystem.getInstance();
		stop = PassiveSubsystem.getInstance();
		gyro = GyroSubsystem.getInstance();
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
			double pow = diff > 0 ? diff * lift.getBrakeMult() * LiftSubsystem.LIFT_POWER : 0;
			int dir = diff > 0 ? LiftSubsystem.UP : LiftSubsystem.DOWN;
			lift.setLift(dir, pow);
			stop.setStopPosition(PassiveSubsystem.STOPPED);
		}else{
			stop.setStopPosition(PassiveSubsystem.FREE);
		}
		
		if (lift.isAtBottom()){
			lift.resetEncHeight();
			lift.setBrakeHeight(0);
		}
		
		if(lift.getDirection() == LiftSubsystem.DOWN && gyro.getTilt() > 5 && lift.getWatchForTilt()) {
			lift.stopLift();
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
