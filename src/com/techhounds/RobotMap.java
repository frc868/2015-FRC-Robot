package com.techhounds;

import edu.wpi.first.wpilibj.I2C;

public class RobotMap {
	
	public static final int DOES_NOT_EXIST = -1;
	
	public static final int DRIVER_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	public static final int OPERATOR_BOARD_PORT = 2;
	
	public static final int CAMERA_ENABLED = 1;
	public static final int USB_CAMERA_ENABLED = DOES_NOT_EXIST;
	public static final String CAMERA_USB = "cam0";
	
	public static final String OP_CONSOLE = "COM4";
	
	public static class Drive{

		public static final int RIGHT_MOTOR_1 = 18;
		public static final int RIGHT_MOTOR_2 = 14;
		public static final int LEFT_MOTOR_1 = 12;
		public static final int LEFT_MOTOR_2 = 19;
		
		public static final int LEFT_ENC = DOES_NOT_EXIST;
		public static final int RIGHT_ENC = DOES_NOT_EXIST;
		
		public static final int RIGHT_MOTOR_1_PRACT = 0;
		public static final int RIGHT_MOTOR_2_PRACT = 1;
		public static final int RIGHT_MOTOR_3_PRACT = 2;
		public static final int LEFT_MOTOR_1_PRACT = 3;
		public static final int LEFT_MOTOR_2_PRACT = 4;
		public static final int LEFT_MOTOR_3_PRACT = 5;
		
		public static final int LEFT_ENC_PRACT = DOES_NOT_EXIST;
		public static final int RIGHT_ENC_PRACT = DOES_NOT_EXIST;
		
	}
	
	public static class Lift{
		
		public static final int MOTOR_1 = 20;
		public static final int MOTOR_2 = 15;
		public static final int DIGITAL_INPUT_TOP = DOES_NOT_EXIST;
		public static final int DIGITAL_INPUT_BOTTOM = DOES_NOT_EXIST;
		public static final int GRAB_SOL = 7;
		public static final int BRAKE_SOL = DOES_NOT_EXIST;
		public static final int ENCODER_A = DOES_NOT_EXIST;
		public static final int ENCODER_B = DOES_NOT_EXIST;
		public static final int IR_SENSOR = 0;
		
		public static final int PASSIVE_STOP_SOL = 5;
		public static final int PASSIVE_ARM_SOL = 6;
		public static final int PASSIVE_LIMIT = 0;
		
		public static final int MOTOR_1_PRACT = 6;
		public static final int MOTOR_2_PRACT = 7;
		public static final int DIGITAL_INPUT_TOP_PRACT = 0;
		public static final int DIGITAL_INPUT_BOTTOM_PRACT = 1;
		public static final int GRAB_SOL_PRACT = 0;
		public static final int BRAKE_SOL_PRACT = DOES_NOT_EXIST;
		public static final int ENCODER_A_PRACT = 4;
		public static final int ENCODER_B_PRACT = 5;
		public static final int IR_SENSOR_PRACT = 3;

		public static final int PASSIVE_STOP_SOL_PRACT = DOES_NOT_EXIST;//3;
		public static final int PASSIVE_ARM_SOL_PRACT = DOES_NOT_EXIST;
		public static final int PASSIVE_LIMIT_PRACT = DOES_NOT_EXIST;
		
	}

	public static class Bin{
		public static final int GRABSOL = 1;
		public static final int TILTSOL = 2;
		public static final int MOTOR = 8;
		public static final int TOP_CHECK = 9;
		public static final int DOWN_CHECK = 3;
		public static final int ENCODER_A = 6;
		public static final int ENCODER_B = 7;
	}
	
	public static class Feeder{
		public static final int LEFT_MOTOR = 10;
		public static final int RIGHT_MOTOR = 13;
		public static final int SOL = 4;
		public static final int LEFT_SENSOR = 2;
		public static final int RIGHT_SENSOR = 1;
		
		public static final int LEFT_MOTOR_PRACT = DOES_NOT_EXIST;
		public static final int RIGHT_MOTOR_PRACT = DOES_NOT_EXIST;
		public static final int SOL_PRACT = DOES_NOT_EXIST;
		public static final int LEFT_SENSOR_PRACT = DOES_NOT_EXIST;
		public static final int RIGHT_SENSOR_PRACT = DOES_NOT_EXIST;
	}
	
	public static class FishingPole{
		public static final int SOL = 3;
		
		public static final int SOL_PRACT = DOES_NOT_EXIST;
	}
	
	public static class Compressor {
		public static final int COMP = 0;
		
		public static final int COMP_PRACT = 0;
	}
	
	public static class LED {
		public static final int LEDS = 36;
		public static final int LED_1 = 1;
		public static final int LED_2 = 2;
		
		public static final int LEDS_PRACT = 36;
		public static final int LED_1_PRACT = 1;
		public static final int LED_2_PRACT = DOES_NOT_EXIST;
	}
	
	public static class Gyro {
		public static final I2C.Port GYRO = I2C.Port.kMXP;
		public static final I2C.Port GYRO_PRACT = null;//I2C.Port.kMXP;
	}
}
