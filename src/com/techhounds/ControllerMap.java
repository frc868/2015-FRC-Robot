package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz
 */
public class ControllerMap {

	//These are index values for the controller arrays
    public static final int A = 0, B = 1, X = 2, Y = 3, RB = 4, RT = 5, LB = 6,
            LT = 7, START = 8, BACK = 9, LEFT_HORIZONTAL = 10,
            RIGHT_HORIZONTAL = 11, LEFT_VERTICAL = 12, RIGHT_VERTICAL = 13;
    
    //This is for PS4
    public static final int CROSS = 0, CIRCLE = 1, SQUARE = 2, TRIANGLE = 3, R1 = 4, R2 = 5, L1 = 6,
    		L2 = 7, OPTIONS = 8, SHARAE = 9;
    
    //These are magic numbers
    public static final int RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3, DIAG_UP_RIGHT = 4,
    		DIAG_UP_LEFT = 5, DIAG_DOWN_RIGHT = 6, DIAG_DOWN_LEFT = 7;
                
    //These are magic numbers
    public static final int LOGITECH = 0, TOMEE = 1, PS3 = 2, PS4 = 3;
    
    private static String[] buttonName = {"A", "B", "X", "Y", "RB", "RT", "LB",
            "LT", "START", "BACK"};
    
    private static String[] axeNames = {"LH", "RH", "LV", "RV"};
    
    /** TOMEE Cannot get the whole DPad */
    private static final int[] tomee =      { 3, 2, 4, 1, 8, 6, 7, 5, 10, 9, 1, 3, 2, 5};
    
    private static final int[] logitech =   { 2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 3};
    
    /** PS3 Cannot get DPAD UP and DOWN) */
    private static final int[] ps3 =        { 1, 2, 3, 4, 6, 10, 5, 9, 8, 7, 1, 4, 2, 5};
    
    /** PS4 Controller */
    private static final int[] ps4 =		{ 2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 5};
    
    private static final double DEADZONE = 0.05;
    
    public boolean eightDirectional;
    private int[] buttonSet;
    private Joystick joystick;
    
    public ControllerMap(Joystick joystick){
        this(joystick, LOGITECH);
    }
    
    public ControllerMap(Joystick joystick, int type){
        this(type);
        this.joystick = joystick;
    }
    
    private ControllerMap(int type){
        if(type == PS3)
            buttonSet = ps3;
        else if(type == PS4)
        	buttonSet = ps4;
        else if(type == TOMEE)
            buttonSet = tomee;
        else
            buttonSet = logitech;
        
        eightDirectional = false;
    }
    
    public ControllerMap(Joystick joystick, int type, boolean isEightDirectional){
    	this(joystick, type);
    	eightDirectional = isEightDirectional;
    }
    
    public void putValues(String label, Joystick stick){
        for(int i = 0; i < buttonName.length; i++){
            SmartDashboard.putBoolean(label + " " + buttonName[i], stick.getRawButton(buttonSet[i]));
        }
        for(int i = 0 ; i < axeNames.length; i++){
            SmartDashboard.putNumber(label + " " + axeNames[i], stick.getRawAxis(buttonSet[i + buttonName.length]));
        }
    }
    
    public double getLeftStickX(){
        return checkDeadZone(joystick.getRawAxis(buttonSet[LEFT_HORIZONTAL]));
    }
    
    public double getLeftStickY(){
        return checkDeadZone(joystick.getRawAxis(buttonSet[LEFT_VERTICAL]));
    }
    
    public double getRightStickX(){
        return checkDeadZone(joystick.getRawAxis(buttonSet[RIGHT_HORIZONTAL]));
    }
    
    public double getRightStickY(){
        return checkDeadZone(joystick.getRawAxis(buttonSet[RIGHT_VERTICAL]));
    }
    
    public static double checkDeadZone(double val) {
    	if (Math.abs(val) < DEADZONE)
    		return 0;
    	return val;
    }
    
    public Button createButton(int buttonID){
        return new JoystickButton(joystick, buttonSet[buttonID]);
    }
    
    public DPadButton createDPadButton(int buttonID) {
        return new DPadButton(buttonID);
    }
    
    protected class DPadButton extends Button {
    
        private int button;

        private DPadButton(int button) {
            this.button = button;
        }

        public boolean get() {
        	
        	int angle = joystick.getPOV();
        	
        	if (!eightDirectional){
	            if (button == ControllerMap.RIGHT) {
	                return angle == 270 || angle == 315 || angle == 225;
	            } else if (button == ControllerMap.LEFT) {
	                return angle == 90 || angle == 45 || angle== 135;
	            } else if (button == ControllerMap.UP) {
	                return angle == 0 || angle == 45 || angle == 315;
	            } else if (button == ControllerMap.DOWN) {
	                return angle == 180 || angle == 135 || angle == 225;
	            } else {
	                return false;
	            }
        	}else{
        		if(button == ControllerMap.UP){
        			return angle == 0;
        		}else if(button == ControllerMap.DIAG_UP_LEFT){
        			return angle == 45;
        		}else if(button == ControllerMap.LEFT){
        			return angle == 90;
        		}else if(button == ControllerMap.DIAG_DOWN_LEFT){
        			return angle == 135;
        		}else if(button == ControllerMap.DOWN){
        			return angle == 180;
        		}else if(button == ControllerMap.DIAG_DOWN_RIGHT){
        			return angle == 225;
        		}else if(button == ControllerMap.RIGHT){
        			return angle == 270;
        		}else if(button == ControllerMap.DIAG_UP_RIGHT){
        			return angle == 315;
        		}else{
        			return false;
        		}
        	}
        }
    }
}
