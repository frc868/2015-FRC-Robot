package com.techhounds.commands.bin;

import com.techhounds.subsystems.BinSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Evan Dexter
 */
public class SetBin extends Command {
	
	private BinSubsystem bin;
	private Integer direction;
	private Double power;
	private Boolean grabPos, tiltPos;

    public SetBin(int direction, double power) {
    	this(direction);
    	this.power = power;
    }
    
    public SetBin(int direction) {
    	bin = BinSubsystem.getInstance();
    	this.direction = direction;
    	this.power = BinSubsystem.LIFT_POWER;
    }
    
    public SetBin(boolean grabPosition){
    	bin = BinSubsystem.getInstance();
    	grabPos = grabPosition;
    }
    
    public SetBin(boolean tiltPosition, int DOESNT_MATTER){
    	bin = BinSubsystem.getInstance();
    	tiltPos = tiltPosition;
    }

    protected void initialize() {
    	if (direction != null)
    		bin.setBin(direction, power);
    	if (grabPos != null)
    		bin.setGrabSol(grabPos);
    	if (tiltPos != null)
    		bin.setTiltSol(tiltPos);
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
