
package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team868.robot.gyro.DirectionTracker;
import org.usfirst.frc.team868.robot.subsystems.RobotParts;

/**
 * A direction tracker always reports normalized angles in the range of [-180,+180].
 */
public class ShowDirectionTracker extends Command {

	private DirectionTracker m_direction;
	private String m_name;

	public ShowDirectionTracker() {
		this("Direction");
    }

	public ShowDirectionTracker(String name) {
		m_name = name;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_direction = new DirectionTracker(RobotParts.getInstance().createDriveRotationTracker());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber(m_name, m_direction.getAngle());
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
