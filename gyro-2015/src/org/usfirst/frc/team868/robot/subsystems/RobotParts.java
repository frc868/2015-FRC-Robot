/**
 * Copyright (c) 2015, www.techhounds.com
 * All rights reserved.
 *
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * </p>
 * <ul>
 * <li>Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.</li>
 * <li>Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.</li>
 * <li>Neither the name of the www.techhounds.com nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.</li>
 * </ul>
 *
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </p>
 */
package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.gyro.DirectionTracker;
import org.usfirst.frc.team868.robot.gyro.GyroItg3200;
import org.usfirst.frc.team868.robot.gyro.GyroItg3200Int;
import org.usfirst.frc.team868.robot.gyro.RotationTracker;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author paul__000
 *
 */
public final class RobotParts {
	private static RobotParts c_instance;

	private GyroItg3200Int m_gyro;

	private Gyro m_oldGyro;

	private DirectionTracker m_direction;

	private RobotParts() {
		m_gyro = new GyroItg3200Int(I2C.Port.kMXP, false);
		m_oldGyro = new Gyro(1);
		m_direction = new DirectionTracker(m_gyro.getRotationZ());
	}

	public static RobotParts getInstance() {
		if (c_instance == null) {
			c_instance = new RobotParts();
		}
		return c_instance;
	}

	/**
	 * @return Reference to the single {@link GyroItg3200Int} object.
	 */
	public GyroItg3200Int getGyro() {
		return m_gyro;
	}

	/**
	 * Create a {@link RotationTracker} that you can use to turn/steer the
	 * robot.
	 */
	public RotationTracker createDriveRotationTracker() {
		return m_gyro.getRotationZ();
	}

	/**
	 * Create a {@link RotationTracker} that you can use to check how much the
	 * robot is tipping forwards or backwards.
	 */
	public RotationTracker createForwardTipRotationTracker() {
		return m_gyro.getRotationX();
	}

	/**
	 * Create a {@link RotationTracker} that you can use to check how much the
	 * robot is tipping forwards or backwards.
	 */
	public RotationTracker createSideTipRotationTracker() {
		return m_gyro.getRotationZ();
	}

	public Gyro getOldGyro() {
		return m_oldGyro;
	}

	/**
	 * Dumps information about the running state of the robot components and subsystems.
	 */
	public void updateDashboard() {
		m_gyro.updateDashboard("ITG-3200", true);
		SmartDashboard.putNumber("Roboting Pointing At", m_direction.getAngle());
	}

}
