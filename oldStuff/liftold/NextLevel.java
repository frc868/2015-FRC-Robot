package com.techhounds.commands.liftold;

import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NextLevel extends Command {

	private LiftSubsystem lift;
	private int dir;
	
    public NextLevel(int direction) {
    	lift = LiftSubsystem.getInstance();
    	dir = direction;
    }

    protected void initialize() {
		double val = lift.getEncHeight();
		
    	if (dir == LiftSubsystem.UP){
    		if (val < LiftSubsystem.OFF_GROUND_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.OFF_GROUND_HEIGHT)).start();
    		}else if (val < LiftSubsystem.ONE_TOTE_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT)).start();
    		}else if (val < LiftSubsystem.TWO_TOTE_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.TWO_TOTE_HEIGHT)).start();
    		}else if (val < LiftSubsystem.THREE_TOTE_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.THREE_TOTE_HEIGHT)).start();
    		}
    	}else if (dir == LiftSubsystem.DOWN){
    		if (val > LiftSubsystem.THREE_TOTE_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.THREE_TOTE_HEIGHT)).start();
    		}else if (val > LiftSubsystem.TWO_TOTE_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.TWO_TOTE_HEIGHT)).start();
    		}else if (val > LiftSubsystem.ONE_TOTE_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT)).start();
    		}else if (val > LiftSubsystem.OFF_GROUND_HEIGHT){
    			(new SetLiftHeight(LiftSubsystem.OFF_GROUND_HEIGHT)).start();
    		}else{
    			(new SetLiftHeight(0)).start();
    		}
    	}
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {

    }

    protected void interrupted() {
    	end();
    }
}