package com.techhounds;

public class RobotMap {
	
	public static final int DOES_NOT_EXIST = -1;
	
	public static final int DRIVER_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	
	public static final int CAMERA_ENABLED = 1;
	
	public static class Drive{
		
		public static final int RIGHT_MOTOR_1 = 0;
		public static final int RIGHT_MOTOR_2 = 1;
		public static final int RIGHT_MOTOR_3 = 2;
		public static final int LEFT_MOTOR_1 = 3;
		public static final int LEFT_MOTOR_2 = 4;
		public static final int LEFT_MOTOR_3 = 5;
		
		public static final int LEFT_ENC = DOES_NOT_EXIST;
		public static final int RIGHT_ENC = DOES_NOT_EXIST;
	}
	
	public static class Lift{
		public static final int MOTOR_1 = 6;
		public static final int MOTOR_2 = 7;
		public static final int DIGITAL_INPUT_TOP = 0;
		public static final int DIGITAL_INPUT_BOTTOM = 1;
		public static final int GRAB_SOL = 0;
		public static final int BRAKE_SOL = DOES_NOT_EXIST;
		public static final int PASSIVE_STOP_SOL = DOES_NOT_EXIST;//3;
		public static final int ENCODER_A = 4;
		public static final int ENCODER_B = 5;
		public static final int IRSensor = 3;
	}

	public static class Bin{
		public static final int GRABSOL = 2;
		public static final int TILTSOL = 1;
		public static final int MOTOR = 8;
		public static final int TOP_CHECK = 9;
		public static final int DOWN_CHECK = 3;
		public static final int ENCODER_A = 6;
		public static final int ENCODER_B = 7;
	}
	
	public static class Arms{
		public static final int LEFT_ARM = DOES_NOT_EXIST;
		public static final int RIGHT_ARM = DOES_NOT_EXIST;
		public static final int ARM_SOL = DOES_NOT_EXIST;
	}
	
	public static class Compressor {
		public static final int COMP = 0;
	}
	
	public static class LED {
		public static final int LEDS = 36;
		public static final int LED_1 = 1;
	}
}
