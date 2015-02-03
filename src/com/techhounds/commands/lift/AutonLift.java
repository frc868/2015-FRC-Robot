package com.techhounds.commands.lift;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Ayon Mitra, Matt Simons, the interns, the ex-intern
 */
public class AutonLift extends Command {
	
	public LiftSubsystem lift;
	public boolean goingUp;

    public AutonLift(int direction) {
        lift = LiftSubsystem.getInstance();
        requires(lift);
        
        goingUp = direction == LiftSubsystem.UP;
    }

    protected void initialize() {
    	if (goingUp)
    		lift.setLift(LiftSubsystem.UP, LiftSubsystem.LIFT_POWER);
    	else
    		lift.setLift(LiftSubsystem.DOWN, LiftSubsystem.LIFT_POWER);    		
    	
    	lift.setPower();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return goingUp ? lift.isAtTop() : lift.isAtBottom();
    }

    protected void end() {
    	lift.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
