package com.techhounds.commands.driving;

import com.techhounds.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Anonymous ;3
 */
public class DriveWithGamepad extends Command {
	
	DriveSubsystem drive;
    
	public DriveWithGamepad() {
		drive = DriveSubsystem.getInstance();
    	requires(drive);
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    protected void initialize() {
    	drive.setPower(.1);
    	delay(1);
    	drive.setPower(0);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    protected void execute() {
    	
    	drive.gamePadDrive();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
    public void delay(long num){
    	try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
