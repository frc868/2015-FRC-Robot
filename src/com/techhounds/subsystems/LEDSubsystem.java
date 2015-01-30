package com.techhounds.subsystems;

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
    
    final int LED_1 = RobotMap.LED.LED_1;
    final int[] LED_LIST = {LED_1};
    
    public int curCmd = 0;
    
    public static final byte OFF_CMD = 0;
    public static final byte STANDBY_CMD = 1;
    public static final byte YELLOW_DOT_CMD = 2;
    public static final byte DRIVE_CMD = 3;
    public static final byte FORWARD_FULL = 4;
    public static final byte FORWARD_HALF = 5;
    public static final byte BACKWARD_FULL = 6;
    public static final byte BACKWARD_HALF = 7;
    public static final byte BRIGHT_CMD = 10;
	
	private LEDSubsystem() {
		super("LEDSubsystem");
		
		leds = new I2C(Port.kMXP, RobotMap.LED.LEDS);
		setBrightness(DEFAULT_BRIGHTNESS);
		off();
	}
	
	public static LEDSubsystem getInstance() {
		if(instance == null)
			instance = new LEDSubsystem();
		return instance;
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
		leds.write(LED_LIST[strip], data);
	}
	
	public void sendToAll(byte data){
		for(int i = 0; i < LED_LIST.length; i++){
			send(i, data);
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void updateSmartDashboard(){
    	
    }
}

