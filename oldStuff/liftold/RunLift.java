package com.techhounds.commands.liftold;

import com.techhounds.OI;
import com.techhounds.subsystems.GyroSubsystem;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	
	private LiftSubsystem lift;
	private GyroSubsystem gyro;
	
	public RunLift() {
		lift = LiftSubsystem.getInstance();
		gyro = GyroSubsystem.getInstance();
		
		requires(lift);
		setInterruptible(false);
	}

	protected void initialize() {
		
	}

	protected void execute() {
	
		if (LiftSubsystem.OP_STICK_CONTROL){
			double pow = OI.getOperatorLeftYAxis();
			int dir = pow != 0 ? (pow > 0 ? LiftSubsystem.DOWN : LiftSubsystem.UP) : LiftSubsystem.STOPPED;
			pow = Math.abs(pow);
			
			if (pow != 0 || !lift.getCmdRunning()){
			
				lift.setLift(dir, pow);
				if(LiftSubsystem.LIFT_BRAKING){
					if (pow != 0){
						lift.setBrake(false);
					}else if (!lift.getBraked()){
						lift.setBrake(true);
					}
				}
			}
		}
		
		if ((lift.getDirection() == LiftSubsystem.UP && !lift.isAtTop()) ||
				(lift.getDirection() == LiftSubsystem.DOWN && !lift.isAtBottom())){
		} else {
			lift.stopLift();
		}
		
		if (lift.isAtBottom()){
			lift.resetEncHeight();
			lift.setBrakeHeight(0);
		}
		
		if (lift.getBraked()){
			double diff = lift.getBrakeHeight() - lift.getEncHeight();
			double pow = diff > 0 ? diff * lift.getBrakeMult() * LiftSubsystem.LIFT_POWER : 0;
			int dir = diff > 0 ? LiftSubsystem.UP : LiftSubsystem.DOWN;
			lift.setLift(dir, pow);
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
