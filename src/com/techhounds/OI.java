package com.techhounds;

import com.techhounds.commands.lift.RunLift;
import com.techhounds.notSureIfWorks.MoveLift;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * @author Atif Niyaz, Alex Fleig, Matt Simmons, Ayon Mitra, Clayton Detke
 */
public class OI {
	
	private static OI instance;
	
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
    	
    	/* Hi Alex, Matt, Clayton and Ayon,
    	 * I want to introduce you to the new way of calling Joystick buttons.
    	 * Here is the current way you did it.
    	 * 
    	 * Button liftUp = new JoystickButton(gamepad, LIFT_UP);
    	 * liftUp.whenPressed(new MoveLift());
    	 * 
    	 * This isnt going to work for us today. Instead we got this new awesome 
    	 * class called the ControllerMap. It is in com.techhounds if you want to
    	 * see it. With this class, it contains a unique library which allows us to
    	 * use more than one type of controller such as Logtiech or PS3.
    	 * 
    	 * Look at the constructor for OI. If you notice, there are two ControllerMaps.
    	 * One is for teh driver, one is for the operator. 
    	 * 
    	 * So up above I told you that you called that button weird right? Well guess what?
    	 * Ill tell you how to call it with out new code.
    	 * So here we go!
    	 * 
    	 * Button liftUp = driver.createButton(ControllerMap.A);
    	 * liftUp.whenPressed(new MoveLift(LiftSubsystem.Direction.UP));
    	 * 
    	 * The parameter in driver.createButton() is ControllerMap.A. ControllerMap.A is
    	 * the button A on the controller, so basically, you dont have to fiddle with ports!
    	 * 
    	 * Other from that it works like a normal button. Good Work so far. Proud of what
    	 * you have done so far! Keep up the good work!
    	 * 
    	 * Please delete this comment since it is using so many lines. Lol.
    	 * 
    	 * ~~ Atif
    	 */
    	
    	Button liftUp = driver.createButton(ControllerMap.A);
    	liftUp.whenPressed(new RunLift());
    	
    	Button liftDown = driver.createButton(ControllerMap.B);
    	liftDown.whenPressed(new RunLift());
    	
    	Button liftStop = driver.createButton(ControllerMap.X);
    	liftStop.whenPressed(new RunLift());
    }
    
    public  initOperator() {
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
    
    
    
}

