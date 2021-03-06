package com.techhounds.subsystems;

import com.techhounds.Robot;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDSubsystem extends BasicSubsystem {
    
	private static LEDSubsystem instance;
    private I2C leds;
    private static final byte DEFAULT_BRIGHTNESS = (byte)127;
    
    final int LED_1;
    final int LED_2;
    final int[] LED_LIST;
    
    public int curCmd = 0;
    
    public static final byte OFF_CMD = 0, STANDBY_CMD = 1, YELLOW_DOT_CMD = 2, DRIVE_CMD = 3, 
    		FORWARD_FULL = 4, FORWARD_HALF = 5, BACKWARD_FULL = 6, BACKWARD_HALF = 7, WINK_CMD = 8, BRIGHT_CMD = 10;
	
    private boolean[] enabled;
    
	private LEDSubsystem() {
		super("LEDSubsystem");
		
		if (Robot.isFinal()){
			LED_1 = RobotMap.LED.LED_1;
			LED_2 = RobotMap.LED.LED_2;
			LED_LIST = new int[]{LED_1, LED_2};
			enabled = new boolean [LED_LIST.length];
			
			if ((enabled[0] = LED_LIST[0] != RobotMap.DOES_NOT_EXIST) || (enabled[1] = LED_LIST[1] != RobotMap.DOES_NOT_EXIST))
				leds = new I2C(Port.kMXP, RobotMap.LED.LEDS);
		}else{
			LED_1 = RobotMap.LED.LED_1_PRACT;
			LED_2 = RobotMap.LED.LED_2_PRACT;
			LED_LIST = new int[]{LED_1, LED_2};
			enabled = new boolean [LED_LIST.length];
			
			if ((enabled[0] = LED_LIST[0] != RobotMap.DOES_NOT_EXIST) || (enabled[1] = LED_LIST[1] != RobotMap.DOES_NOT_EXIST))
				leds = new I2C(Port.kMXP, RobotMap.LED.LEDS_PRACT);
		}
		
		setBrightness(DEFAULT_BRIGHTNESS);
		off();
	}
	
	public static LEDSubsystem getInstance() {
		if(instance == null)
			instance = new LEDSubsystem();
		return instance;
	}
	
	public int getCommand(){
		return curCmd;
	}
	
	public void standby(){
		sendToAll(STANDBY_CMD);
		curCmd = STANDBY_CMD;
	}
	
	public void off(){
		sendToAll(OFF_CMD);
		curCmd = OFF_CMD;
	}
	
	public void yellowDot(){
		sendToAll(YELLOW_DOT_CMD);
		curCmd = YELLOW_DOT_CMD;
	}
	
	public void drive(){
		sendToAll(DRIVE_CMD);
		curCmd = DRIVE_CMD;
	}
	
	public void forwardFull(){
		sendToAll(FORWARD_FULL);
		curCmd = FORWARD_FULL;
	}
	
	public void forwardHalf(){
		sendToAll(FORWARD_HALF);
		curCmd = FORWARD_HALF;
	}
	
	public void backwardFull(){
		sendToAll(BACKWARD_FULL);
		curCmd = BACKWARD_FULL;
	}
	
	public void backwardHalf(){
		sendToAll(BACKWARD_HALF);
		curCmd = BACKWARD_HALF;
	}
	
	public void wink(){
		sendToAll(WINK_CMD);
		curCmd = WINK_CMD;
	}
	
	public void updateDrive(){
		if(curCmd != DRIVE_CMD)
			return;
		
		byte rVal = (byte) ((Math.abs(DriveSubsystem.getInstance().getRightPower()) * 30));
		byte lVal = (byte) ((Math.abs(DriveSubsystem.getInstance().getLeftPower()) * 30));
		sendToAll(rVal);
		sendToAll(lVal);
	}
	
	public void setBrightness(byte brightness){
		sendToAll(BRIGHT_CMD);
		sendToAll(brightness);
		curCmd = BRIGHT_CMD;
	}
	
	private void send(int strip, byte data){
		if (enabled[strip])
			leds.write(LED_LIST[strip], data);
	}
	
	public void sendToAll(byte data){
		for(int i = 0; i < LED_LIST.length; i++)
			send(i, data);
	}

    public void initDefaultCommand() {

    }
    
    public void updateSmartDashboard(){
    	
    }
}

