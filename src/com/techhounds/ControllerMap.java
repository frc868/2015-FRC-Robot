package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz
 */
public class ControllerMap {

    public static final int A = 0, B = 1, X = 2, Y = 3, RB = 4, RT = 5, LB = 6,
            LT = 7, START = 8, BACK = 9, LEFT_HORIZONTAL = 10,
            RIGHT_HORIZONTAL = 11, LEFT_VERTICAL = 12, RIGHT_VERTICAL = 13, 
            D_PAD_HORIZONTAL = 14, D_PAD_VERTICAL = 15;
    
    public static final int RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3; 
                
    public static final int LOGITECH = 0, TOMEE = 1, PS3 = 2;
    
    private static String[] buttonName = {"A", "B", "X", "Y", "RB", "RT", "LB",
            "LT", "START", "BACK"};
    
    private static String[] axeNames = {"LH", "RH", "LV", "RV", "DPAD-H", "DPAD-V"};
    
    /** TOMEE Cannot get the whole DPad */
    private static final int[] tomee =      { 3, 2, 4, 1, 8, 6, 7, 5, 10, 9, 1, 3, 2, 5, -1, -1};
    
    private static final int[] logitech =   { 2, 3, 1, 4, 6, 8, 5, 7, 10, 9, 0, 2, 1, 3, 4, 5};
    
    /** PS3 Cannot get DPAD UP and DOWN) */
    private static final int[] ps3 =        { 1, 2, 3, 4, 6, 10, 5, 9, 8, 7, 1, 4, 2, 5, 6, -1};
    
    int[] controller;
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
            controller = ps3;
        else if(type == TOMEE)
            controller = tomee;
        else
            controller = logitech;
    }
    
    public int index(int id) {
        return controller[id];
    }
    
    public void putValues(String label, Joystick js){
        for(int i = 0; i < buttonName.length; i++){
            SmartDashboard.putBoolean(label+" "+buttonName[i], js.getRawButton(index(i)));
        }
        for(int i = 0 ; i < axeNames.length; i++){
            SmartDashboard.putNumber(label+" "+axeNames[i], js.getRawAxis(index(i+LEFT_HORIZONTAL)));
        }
    }
    
    public double getLeftStickX(){
        return joystick.getRawAxis(index(LEFT_HORIZONTAL));
    }
    
    public double getLeftStickY(){
        return joystick.getRawAxis(index(LEFT_VERTICAL));
    }
    
    public double getRightStickX(){
        return joystick.getRawAxis(index(RIGHT_HORIZONTAL));
    }
    
    public double getRightStickY(){
        return joystick.getRawAxis(index(RIGHT_VERTICAL));
    }
    
    public Button createButton(int buttonID){
        return new JoystickButton(joystick, index(buttonID));
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
        }
    }
}
