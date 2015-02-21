package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz
 */
public class PlaystationMap extends ControllerMap {

	public static final int CROSS = 0, CIRCLE = 1, SQUARE = 2, TRIANGLE = 3, R1 = 4, R2 = 5, L1 = 6,
    		L2 = 7, START = 8, SELECT = 9, OPTIONS = 8, SHARE = 9;
    
    private static final int[] ps4_win =		{ 2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 5};
    
    // Separate One for DS4 Drivers
    private static final int[] ps4_ds4 =		{ 1, 2, 3, 4, 6, 10, 5, 9, 8, 7, 0, 4, 1, 5};
    
    private static final int[] ps3 =        	{ 1, 2, 3, 4, 6, 10, 5, 9, 8, 7, 0, 3, 1, 4};
    
    public static final int PS3 = 2, PS4_WIN = 3, PS4_DS4 = 4;
    
    public PlaystationMap(Joystick joystick, int type){
    	super(joystick);
    	
    	if(type == PS4_WIN)
    		buttonSet = ps4_win;
    	else if(type == PS4_DS4)
    		buttonSet = ps4_ds4;
    	else
    		buttonSet = ps3;
    }

    public PlaystationMap(Joystick joystick, int type, boolean isEightDirectional){
    	this(joystick, type);
    	eightDirectional = isEightDirectional;
    }
    
    public void startRumble() { 
    	if(buttonSet == ps4_win) 
    		return; 
    	
    	joystick.setRumble(RumbleType.kLeftRumble, 1); 
    	joystick.setRumble(RumbleType.kRightRumble, 1);
    	
    } 
    
    public void stopRumble() { 
    	if(buttonSet == ps4_win) 
    		return; 
    	
    	joystick.setRumble(RumbleType.kLeftRumble, 0); 
    	joystick.setRumble(RumbleType.kRightRumble, 0); 
    }
}
