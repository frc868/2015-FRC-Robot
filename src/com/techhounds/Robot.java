
package com.techhounds;

import com.techhounds.commands.UpdateDashboard;
import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.subsystems.BinSubsystem;
import com.techhounds.subsystems.CameraSubsystem;
import com.techhounds.subsystems.CompSubsystem;
import com.techhounds.subsystems.DriveSubsystem;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.GyroSubsystem;
import com.techhounds.subsystems.LEDSubsystem;
import com.techhounds.subsystems.LiftSubsystem;
import com.techhounds.subsystems.PassiveSubsystem;
import com.techhounds.subsystems.USBCameraSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	
	private Command auton;
	private static boolean finalRobot = true;
	
	public static boolean isFinal(){
		return finalRobot;
	}
	
    public void robotInit() {
    	
    	initSubsystems();
		OI.getInstance();
		(new UpdateDashboard()).start();
		
		System.out.println("*******\n"+
							"TEAM 868 CAN ROBOT NOW!\n" +
							"*******");
    }
    
    public void initSubsystems() {

    	USBCameraSubsystem.getInstance();
    	GyroSubsystem.getInstance();
    	FeederSubsystem.getInstance();
    	BinSubsystem.getInstance();
    	CompSubsystem.getInstance();
    	DriveSubsystem.getInstance();
    	LEDSubsystem.getInstance().standby();
    	LiftSubsystem.getInstance();
    	PassiveSubsystem.getInstance();
    	CameraSubsystem.getInstance();
    }	
    
    public void autonomousInit() {

    	(auton = AutonChooser.getSelected()).start();

    	System.out.println("*******\n"+
							"TEAM 868 CAN AUTON NOW!\n" +
							"*******");
    }
    
    public void teleopInit() {

    	if(auton != null)
    		auton.cancel();
    	
		System.out.println("*******\n"+
							"TEAM 868 CAN TELEOP NOW!\n" +
							"*******");
		
		DriveSubsystem.getInstance().updateLEDCommand();
    }
    
    public void disabledInit(){
    	
    	if(auton != null)
    		auton.cancel();
    	
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
