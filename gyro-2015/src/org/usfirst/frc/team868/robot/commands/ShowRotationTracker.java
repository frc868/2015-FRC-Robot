
package org.usfirst.frc.team868.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team868.robot.gyro.RotationTracker;
import org.usfirst.frc.team868.robot.subsystems.RobotParts;

/**
 * A rotation tracker shows the total rotation in the range of [-INF,+INF] (you can rotate 720 degrees).
 */
public class ShowRotationTracker extends Command {

	private RotationTracker m_rotation;
	private String m_name;

	public ShowRotationTracker() {
		this("Rotation");
    }

	public ShowRotationTracker(String name) {
		m_name = name;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_rotation = RobotParts.getInstance().createDriveRotationTracker();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber(m_name, m_rotation.getAngle());
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
