package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	
	LiftSubsystem lift;
	
	public RunLift() {
		lift = LiftSubsystem.getInstance();
		requires(lift);		
	}

	protected void initialize() {
		
	}

	protected void execute() {
		if ((lift.getDirection() == LiftSubsystem.UP && !lift.isAtTop()) ||
				(lift.getDirection() == LiftSubsystem.DOWN && !lift.isAtBottom())){
		} else {
			lift.stopLift();
		}
		lift.setPower();
		
		if (lift.getPower() == 0)
			lift.setBrakePosition(LiftSubsystem.BRAKE);
		else
			lift.setBrakePosition(LiftSubsystem.UNBRAKE);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		
	}
}
