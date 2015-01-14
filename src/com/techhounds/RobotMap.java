package com.techhounds;

public class RobotMap {
	
	public static class Drive{
		public static final int DRIVER_PORT = 1;
		public static final int OPERATOR_PORT = 2;
		public static final int RIGHT_DRIVE_MOTOR_1 = 0;
		public static final int RIGHT_DRIVE_MOTOR_2 = 0;
		public static final int RIGHT_DRIVE_MOTOR_3 = 0;
		public static final int LEFT_DRIVE_MOTOR_1 = 0;
		public static final int LEFT_DRIVE_MOTOR_2 = 0;
		public static final int LEFT_DRIVE_MOTOR_3 = 0;
	}
	
	public static class Lift{
		public static final int LIFT_MOTOR = 0;
		public static final int DIGITAL_INPUT_TOP = 0;
		public static final int DIGITAL_INPUT_BOTTOM = 0;
		public static final int LIFT_POWER = 1;
	}
	
	public static class Arms{
		public static final int LEFT_ARM = 0;
		public static final int RIGHT_ARM = 1;
		public static final int DIRECTION = 0;
	}
	
}
