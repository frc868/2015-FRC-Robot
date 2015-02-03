package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz
 */
public class PS4Map extends ControllerMap {

	public static final int CROSS = 0, CIRCLE = 1, SQUARE = 2, TRIANGLE = 3, R1 = 4, R2 = 5, L1 = 6,
    		L2 = 7, OPTIONS = 8, SHARE = 9, 
    		LEFT_HOR = 10, RIGHT_HOR = 11, LEFT_VERT = 12, RIGHT_VERT = 13;
    
    private static final int[] ps4 =		{ 2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 5};
    
    public PS4Map(Joystick joystick){
    	super(joystick);
        buttonSet = ps4;
    }

    public PS4Map(Joystick joystick, int type, boolean isEightDirectional){
    	this(joystick);
    	eightDirectional = isEightDirectional;
    }
}
