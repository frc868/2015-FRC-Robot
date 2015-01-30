
package com.techhounds;

import com.techhounds.commands.UpdateDashboard;
import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.subsystems.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	
	private Command auton;
	
    public void robotInit() {
    	initSubsystems();
		OI.getInstance().init();
		(new UpdateDashboard()).start();
		
		System.out.println("*******\n"+
							"TEAM 868 CAN ROBOT NOW!\n" +
							"*******");
		LEDSubsystem.getInstance().standby();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {

    	auton = AutonChooser.getSelected();
    	auton.start();
    	
		System.out.println("*******\n"+
							"TEAM 868 CAN AUTON NOW!\n" +
							"*******");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {

    	if (auton != null)
    		auton.cancel();
    	
		System.out.println("*******\n"+
							"TEAM 868 CAN TELEOP NOW!\n" +
							"*******");
		DriveSubsystem.getInstance().updateLEDCommand();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	LEDSubsystem.getInstance().standby();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * This function is called to initialize the Subsystems
     */
    public void initSubsystems() {
    	
    	// Init Subsystems
    	ArmsSubsystem.getInstance();
    	BinSubsystem.getInstance();
    	CompSubsystem.getInstance();
    	DriveSubsystem.getInstance();
    	LEDSubsystem.getInstance();
    	LiftSubsystem.getInstance();
    }
    
    public static double checkRange(double curr, double min, double max) {
    	return Math.max(Math.min(curr, max), min);
    }
}
