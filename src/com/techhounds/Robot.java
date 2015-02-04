
package com.techhounds;

import com.techhounds.commands.UpdateDashboard;
import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private Command auton;
	
    public void robotInit() {
    	initSubsystems();
		OI.getInstance();
//		(new UpdateDashboard()).start();
		
		System.out.println("*******\n"+
							"TEAM 868 CAN ROBOT NOW!\n" +
							"*******");
    }
    
    public void initSubsystems() {
    	
    	ArmsSubsystem.getInstance();
    	BinSubsystem.getInstance();
    	CompSubsystem.getInstance();
    	DriveSubsystem.getInstance();
    	LEDSubsystem.getInstance().standby();
    	LiftSubsystem.getInstance();
    	PassiveStopSubsystem.getInstance();
    }	
    
    public void autonomousInit() {

    	auton = AutonChooser.getSelected();
    	auton.start();
		(new UpdateDashboard()).start();
    	
		System.out.println("*******\n"+
							"TEAM 868 CAN AUTON NOW!\n" +
							"*******");
    }
    
    public void teleopInit() {

    	if (auton != null)
    		auton.cancel();
    	
		System.out.println("*******\n"+
							"TEAM 868 CAN TELEOP NOW!\n" +
							"*******");

		(new UpdateDashboard()).start();
		DriveSubsystem.getInstance().updateLEDCommand();
    }
    
    public void disabledInit(){
    	LEDSubsystem.getInstance().standby();
    }
    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
    
    public static double checkRange(double curr, double min, double max) {
    	return Math.max(Math.min(curr, max), min);
    }
}
