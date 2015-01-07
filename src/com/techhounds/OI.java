package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Atif Niyaz
 */
public class OI {
	
	private static OI instance;
	
	static Joystick gamepad;
	
	private static final int LIFT_UP = 0;
	private static final int LIFT_DOWN = 0;
	
	private static ControllerMap driver;
	private static ControllerMap operator;
	
	private boolean isInit;
	
	private static final double DEADZONE = 0.05;
		
	
	public OI() {
		
		driver = new ControllerMap(new Joystick(RobotMap.DRIVER_PORT), ControllerMap.PS3);
		operator = new ControllerMap(new Joystick(RobotMap.OPERATOR_PORT), ControllerMap.LOGITECH);
		
		isInit = false;
	}
	
	public static OI getInstance() {
		if(instance == null)
			instance = new OI();
		
		return instance;
	}

    public void init() {
    	
    	if(isInit)
    		return;

    	initDriver();
    	initOperator();
    	initSD();
    	
    	isInit = true;
    }
    
    public void initDriver() {
    	// TODO: Put Driver Buttons and Commands;
    }
    
    public void initOperator() {
    	// TODO: Put Operator Buttons and Commands;
    }
    
    public void initSD() {
    	// TODO: Put SmartDashboard Values;
    }
    
    public static double getDriverRightXAxis() {
    	return checkDeadZone(driver.getRightStickX());
    }
    
    public static double getDriverRightYAxis() {
    	return checkDeadZone(driver.getRightStickY());
    }
    
    public static double getDriverleftXAxis() {
    	return checkDeadZone(driver.getLeftStickX());
    }
    public static double getDriverLeftYAxis() {
    	return checkDeadZone(driver.getLeftStickY());
    }
    
    public static double getOperatorRightXAxis() {
    	return checkDeadZone(operator.getRightStickX());
    }
    
    public static double getOperatorRightYAxis() {
    	return checkDeadZone(operator.getRightStickY());
    }
    
    public static double getOperatorLeftXAxis() {
    	return checkDeadZone(operator.getLeftStickX());
    }
    
    public static double getOperatorLeftYAxis() {
    	return checkDeadZone(operator.getLeftStickY());
    }
    
    
    
    public static double checkDeadZone(double val) {
    	return Math.abs(val) < DEADZONE ? 0 : val;
    }
    
    Button liftUp = new JoystickButton(gamepad, LIFT_UP);
    Button liftDown = new JoystickButton(gamepad, LIFT_DOWN);
    
}

