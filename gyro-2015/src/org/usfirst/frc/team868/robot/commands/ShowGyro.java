
package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team868.robot.gyro.GyroItg3200;
import org.usfirst.frc.team868.robot.gyro.GyroItg3200Int.Rotation;
import org.usfirst.frc.team868.robot.gyro.GyroItg3200Int;
import org.usfirst.frc.team868.robot.subsystems.RobotParts;

/**
 *
 */
public class ShowGyro extends Command {
	/** Set to true to see additional diagnostic output on the smartdashboard, set to false to disable. */
	private static final boolean DEBUG = true;
	
	/** Set to true if you always want to zero out your direction when the command is started. */
	private static final boolean ZERO_AT_START = true;
	
    private Rotation m_xaxis;
	private Rotation m_yaxis;
	private Rotation m_zaxis;

	private Gyro oldGyro;

	private GyroItg3200Int gyro;

	public ShowGyro() {
		RobotParts rp = RobotParts.getInstance();
    	gyro = rp.getGyro();
    	oldGyro = rp.getOldGyro();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_xaxis = gyro.getRotationX();
    	m_yaxis = gyro.getRotationY();
    	m_zaxis = gyro.getRotationZ();
    	
    	if (ZERO_AT_START) {
        	m_xaxis.zero();
    		m_zaxis.zero();
        	m_yaxis.zero();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String name = gyro.toString();
    	SmartDashboard.putNumber(name + " xaxis", m_xaxis.getAngle());
    	SmartDashboard.putNumber(name + " yaxis", m_yaxis.getAngle());
    	SmartDashboard.putNumber(name + " zaxis", m_zaxis.getAngle());
    	SmartDashboard.putNumber("Old Gyro", oldGyro.getAngle());
    	
    	if (DEBUG) {
        	SmartDashboard.putNumber(name + " x-axis raw:", m_xaxis.getAngleRateRaw());
        	SmartDashboard.putNumber(name + " y-axis raw:", m_yaxis.getAngleRateRaw());
        	SmartDashboard.putNumber(name + " z-axis raw:", m_zaxis.getAngleRateRaw());
    		
        	SmartDashboard.putNumber(name + " x-axis deg/sec:", m_xaxis.getAngleRate());
        	SmartDashboard.putNumber(name + " y-axis deg/sec:", m_yaxis.getAngleRate());
        	SmartDashboard.putNumber(name + " z-axis deg/sec:", m_zaxis.getAngleRate());
        	
        	// The number of times the gyro was successfully read should be close to the same for each tracker
        	SmartDashboard.putNumber(name + " readings:", m_zaxis.getReadings());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
