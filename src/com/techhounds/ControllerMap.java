package com.techhounds;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Atif Niyaz
 */
public class ControllerMap {

	//These are index values for the controller arrays
    public static final int A = 0, B = 1, X = 2, Y = 3, RB = 4, RT = 5, LB = 6,
            LT = 7, START = 8, BACK = 9, LEFT_HORIZONTAL = 10,
            RIGHT_HORIZONTAL = 11, LEFT_VERTICAL = 12, RIGHT_VERTICAL = 13;
    
    //These are magic numbers
    public static final int RIGHT = 24, LEFT = 25, UP = 26, DOWN = 27, DIAG_UP_RIGHT = 28,
    		DIAG_UP_LEFT = 29, DIAG_DOWN_RIGHT = 30, DIAG_DOWN_LEFT = 31;
                
    //These are also magic numbers
    public static final int LOGITECH = 0, TOMEE = 1, XBOX = 2;
    
    /** TOMEE Cannot get the whole DPad */
    protected static final int[] tomee =      { 3, 2, 4, 1, 8, 6, 7, 5, 10, 9, 1, 3, 2, 5};
    
    protected static final int[] logitech =   { 2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 3};	
    
    protected static final int[] xbox360 = { 1, 2, 3, 4, 6, -1, 5, -1, 8, 7, 0, 4, 1, 5};
    
    protected static final double DEADZONE = 0.05;
    
    protected int type;
    
    public boolean eightDirectional;
    protected int[] buttonSet;
    public Joystick joystick;
    
    public ControllerMap(Joystick joystick){
        this(joystick, LOGITECH);
    }
    
    public ControllerMap(Joystick joystick, int type){
        this(type);
        this.joystick = joystick;
    }
    
    protected ControllerMap(int type){
    	if(type == TOMEE)
            buttonSet = tomee;
        else if(type == XBOX)
        	buttonSet = xbox360;
        else
            buttonSet = logitech;
        
        this.type = type;
        
        eightDirectional = false;
    }
    
    public ControllerMap(Joystick joystick, int type, boolean isEightDirectional){
    	this(joystick, type);
    	eightDirectional = isEightDirectional;
    }
    
    public int getType() {
    	return type;
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
    
    public void startRumble() { 
    	if(buttonSet != xbox360)
    		return;
    	
    	joystick.setRumble(RumbleType.kLeftRumble, 1); 
    	joystick.setRumble(RumbleType.kRightRumble, 1);
    	
    } 
    
    public void stopRumble() { 
    	if(buttonSet != xbox360) 
    		return; 
    	
    	joystick.setRumble(RumbleType.kLeftRumble, 0); 
    	joystick.setRumble(RumbleType.kRightRumble, 0); 
    }
    
  
    public Button createButton(int buttonID){
    	if (buttonID == UP || buttonID == DOWN || buttonID == LEFT || buttonID == RIGHT ||
    		buttonID == DIAG_DOWN_LEFT || buttonID == DIAG_DOWN_RIGHT || buttonID == DIAG_UP_LEFT|| buttonID == DIAG_UP_RIGHT)
    		return new DPadButton(buttonID);
    	
    	if(type == XBOX && (buttonID == LT || buttonID == RT)) {
    		return new TriggerButton(buttonID);
    	}
    	
        return new JoystickButton(joystick, buttonSet[buttonID]);
    }
    
    protected class TriggerButton extends Button {
    	
    	private Hand hand;
    	
    	private TriggerButton(int buttonID) {
    		if(buttonID == LT)
    			hand = Hand.kLeft;
    		else if(buttonID == RT)
    			hand = Hand.kRight;
    		else
    			hand = null;
    	}
    	
    	public boolean get() {
    		if(hand == Hand.kLeft)
    			return joystick.getRawAxis(2) > .6;
    		else if(hand == Hand.kRight)
    			return joystick.getRawAxis(3) > .6;
    		else
    			return false;
    	}
    }
    
    protected class DPadButton extends Button {
    
        private int button;

        private DPadButton(int button) {
            this.button = button;
        }

        public boolean get() {
        	
        	int angle = joystick.getPOV();
        	
        	if (!eightDirectional){
	            if (button == ControllerMap.LEFT) {
	                return angle == 270 || angle == 315 || angle == 225;
	            } else if (button == ControllerMap.RIGHT) {
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
