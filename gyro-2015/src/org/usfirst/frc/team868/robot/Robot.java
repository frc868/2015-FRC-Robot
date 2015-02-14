
package org.usfirst.frc.team868.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team868.robot.subsystems.RobotParts;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
	private double m_dashboardLastUpdate;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotParts.getInstance();
		OI.getInstance();
        // instantiate the command used for the autonomous period
        autonomousCommand = new WaitCommand(1.0);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	// If it looks like something terrible is happening while running
    	// autonomous, then shut it down
    	if (autonomousCommand.isRunning() && isBlowingUp()) {
    		autonomousCommand.cancel();
    	}
        Scheduler.getInstance().run();
        updateDashboard();
    }

	private boolean isBlowingUp() {
		// TODO Auto-generated method stub
		return false;
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    private void updateDashboard() {
    	double now = Timer.getFPGATimestamp();
    	if ((now - m_dashboardLastUpdate) >= .1) {
    		m_dashboardLastUpdate = now;
    		RobotParts.getInstance().updateDashboard();
    	}
	}
}
