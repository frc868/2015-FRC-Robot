package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunLift extends Command{
	
	LiftSubsystem lift;
	
	public RunLift() {
		lift = lift.getInstance();
		requires(lift);		
	}

	protected void initialize() {
		// TODO Auto-generated method stub
	}

	protected void execute() {
		if ((lift.getDirection() == lift.UP && !lift.isAtTop()) ||
				(lift.getDirection() == lift.DOWN && !lift.isAtBottom())){
		} else {
			lift.stopLift();			
		}
		lift.setPower();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
