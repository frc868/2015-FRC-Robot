
package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team868.robot.gyro.GyroItg3200;
import org.usfirst.frc.team868.robot.gyro.GyroItg3200Int;
import org.usfirst.frc.team868.robot.subsystems.RobotParts;

/**
 * Tells background gyro thread to keep a buffer of the last N samples then writes them to a file when the command ends.
 */
public class RecordGyro extends Command {

	/** Maximum number of raw samples to record. */
    private int m_numSamples;
    
    /** Gyro to record raw data from. */
	private GyroItg3200Int m_gyro;

    /**
     * Construct a new instance of the command with a maximum number of samples.
     * 
     * @param numSamples
     */
	public RecordGyro(int numSamples) {
    	m_numSamples = numSamples;
    	m_gyro = RobotParts.getInstance().getGyro();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Tell gyro to start recording data
    	m_gyro.enableBuffer(m_numSamples);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_gyro.isBufferFull();
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_gyro.saveBuffer();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// If command is interrupted early, go ahead and save what is currently in the buffer
    	end();
    }
}
