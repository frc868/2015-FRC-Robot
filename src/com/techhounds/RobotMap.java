package com.techhounds;

public class RobotMap {
	
	public static final int DOES_NOT_EXIST = -1;
	
	public static class Drive{
		public static final int DRIVER_PORT = 0;
		public static final int OPERATOR_PORT = 1;
		
		public static final int RIGHT_DRIVE_MOTOR_1 = 16;
		public static final int RIGHT_DRIVE_MOTOR_2 = 15;
		public static final int RIGHT_DRIVE_MOTOR_3 = 14;
		public static final int LEFT_DRIVE_MOTOR_1 = 19;
		public static final int LEFT_DRIVE_MOTOR_2 = 18;
		public static final int LEFT_DRIVE_MOTOR_3 = 17;
		
		public static final int LEFT_ENC = DOES_NOT_EXIST;
		public static final int RIGHT_ENC = DOES_NOT_EXIST;
	}
	
	public static class Lift{
		public static final int LIFT_MOTOR = 9;
		public static final int DIGITAL_INPUT_TOP = 0;
		public static final int DIGITAL_INPUT_BOTTOM = 1;
		public static final int LIFT_SOL = 0;
	}
	
	public static class Arms{
		public static final int LEFT_ARM = DOES_NOT_EXIST;//8
		public static final int RIGHT_ARM = DOES_NOT_EXIST;//7
		public static final int ARM_SOL = DOES_NOT_EXIST;//3
	}
	
	public static class Compressor {
		public static final int COMP = 0;
	}
	
	private static class LED {
		public static final int LEDS = 36;
		public static final int LED_1 = 1;
	}
}
