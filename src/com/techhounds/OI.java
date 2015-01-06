package com.techhounds;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Atif Niyaz
 */
public class OI {
	
	private static OI instance;
	
	private ControllerMap driver;
	private ControllerMap operator;
	
	private boolean isInit;
	
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
}

