package com.techhounds.commands.lift;

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
		setInterruptible(true);
		requires(lift);
	}

	protected void initialize() {
		
	}

	protected void execute() {
		
		double power = OI.getOperatorLeftYAxis();
		
		if(LiftSubsystem.OP_STICK_CONTROL = power > .1 || power < -.1) {
			
			LiftSubsystem.DRIVER_CONTROL = false;
			AddTote.getInstance().cancel();
			LiftSubsystem.Action action = power != 0 ? (power > 0.1 ? LiftSubsystem.Action.DOWN : 
				(power < 0.1 ? LiftSubsystem.Action.UP : LiftSubsystem.Action.STOPPED)) : LiftSubsystem.Action.STOPPED;
			
			lift.setLift(action, Math.abs(power > .1 || power < -.1 ? power : 0));

		}else if(!LiftSubsystem.DRIVER_CONTROL){
			lift.stopLift();
		}
		
		if ((lift.getDirection() == LiftSubsystem.Action.UP && !lift.isAtTop()) ||
				(lift.getDirection() == LiftSubsystem.Action.DOWN && !lift.isAtBottom())){
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
			lift.setLift(diff > 0 ? LiftSubsystem.Action.DOWN : LiftSubsystem.Action.UP, pow);
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
