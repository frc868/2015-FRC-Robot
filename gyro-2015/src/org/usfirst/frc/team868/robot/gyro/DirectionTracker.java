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

package org.usfirst.frc.team868.robot.gyro;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A direction tracker allows you to keep track of what direction the robot is
 * facing (always in the range of -180.0 to +180.0 degrees) since the object was
 * constructed or zeroed.
 * 
 * <p>
 * This object is most useful when you want to make field aware decisions and
 * trust that your gyro doesn't drift too much.
 * </p>
 * 
 * <p>
 * You will need to construct a {@link RotationTracker} first in order to create
 * one of these classes. This is typically done using the
 * {@link #getRotationTracker} method associated with the gyro class associated
 * with your hardware. For example {@link GyroItg3200.getRotationTrackerZ()}.
 * </p>
 */
public final class DirectionTracker implements PIDSource {

	/** Used to keep track of which way we are facing. */
	private RotationTracker m_RotationTracker;

	/**
	 * Construct a new instance with an underlying {@link RotationTracker} to
	 * provide angle information.
	 * 
	 * @param rt
	 *            The source to get our angle information from (the values that
	 *            we need to normalize).
	 */
	public DirectionTracker(RotationTracker rt) {
		if (rt == null) {
			throw new NullPointerException("You must provide a RotationTracker");
		}
		m_RotationTracker = rt;
	}

	/**
	 * Normalize an arbitrary number of degrees to the equivalent direction in
	 * the range of [-180, +180].
	 * 
	 * @param angle
	 *            The arbitrary number of degrees (45.0, -270.0, +735.0) to
	 *            normalize to the nice range.
	 * @return The normalized value in the range of [-180, +180].
	 */
	public static double normalize180(double angle) {
		// First step is to normalize to range of [-360, +360]
		double normalized = (angle % 360);

		// Second step reduces range to [-180, +180]
		if (angle < 0) {
			if (normalized < -180.0)
				normalized = (normalized + 360.0);
		} else {
			if (normalized > 180.0)
				normalized = (normalized - 360.0);
		}

		return normalized;
	}

	/**
	 * Returns the angle in signed decimal degrees since construction or
	 * zeroing.
	 * 
	 * <p>
	 * This value is used as the PID sensor value.
	 * </p>
	 * 
	 * @return Number of degrees in the range of [-180, +180] indicating which
	 *         way the robot is facing. For example a value of 45.0 indicates
	 *         that the robots has rotated clockwise 45 degrees. This value is
	 *         normalized, you won't ever see numbers like 270.0 (you would see
	 *         -90.0 instead).
	 */
	public double getAngle() {
		return normalize180(m_RotationTracker.getAngle());
	}

	/**
	 * Zeros the rotation tracker so the current direction we are pointing now
	 * becomes the zero point.
	 */
	public void zero() {
		m_RotationTracker.zero();
	}

	/**
	 * Returns the angle in the range of [-180.0, +180.0] degrees.
	 */
	@Override
	public double pidGet() {
		return getAngle();
	}

	/**
	 * Puts the current value to the dashboard in a range that is nice for the
	 * "compass" display.
	 * 
	 * @param label
	 *            The lable to be associated with the angle.
	 */
	public void putDashboard(String label) {
		double angle = getAngle();
		// TODO: Figure out if [-180, +180] or [0, 360] is better for dashboard
		// display
		SmartDashboard.putNumber(label, angle);
	}

}
