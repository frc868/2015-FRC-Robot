package com.techhounds;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class RobotMap {
	
	public static final int DOES_NOT_EXIST = -1;
	
	public static final int DRIVER_PORT = 0;
	public static final int OPERATOR_PORT = 1;
	
	public static class Drive{
		
		public static final int RIGHT_DRIVE_MOTOR_1 = 0;
		public static final int RIGHT_DRIVE_MOTOR_2 = 1;
		public static final int RIGHT_DRIVE_MOTOR_3 = 2;
		public static final int LEFT_DRIVE_MOTOR_1 = 3;
		public static final int LEFT_DRIVE_MOTOR_2 = 4;
		public static final int LEFT_DRIVE_MOTOR_3 = 5;
		
		public static final int LEFT_ENC = DOES_NOT_EXIST;
		public static final int RIGHT_ENC = DOES_NOT_EXIST;
	}
	
	public static class Lift{
		public static final int LIFT_MOTOR_1 = 6;
		public static final int LIFT_MOTOR_2 = 7;
		public static final int DIGITAL_INPUT_TOP = 0;
		public static final int DIGITAL_INPUT_BOTTOM = 1;
		public static final int LIFT_SOL = 0;
		public static final int PASSIVE_STOP_SOL = DOES_NOT_EXIST;
	}

	public static class Bin{
		public static final int BIN_GRABSOL = 1;
		public static final int BIN_TILTSOL = 2;
		public static final int BIN_MOTOR = 8;
		public static final int BIN_TOP_CHECK = 2;
		public static final int BIN_DOWN_CHECK = 3;
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
