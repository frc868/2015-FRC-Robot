package com.techhounds;

import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.commands.lift.RunLift;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz, Alex Fleig, Matt Simmons, Ayon Mitra, Clayton Detke
 */
public class OI {
	
	private static OI instance;
	
	private static ControllerMap driver;
	private static ControllerMap operator;
	
	private SendableChooser autonChoice;
	
	private boolean isInit;
	
	private static final double DEADZONE = 0.05;
		
	//Driver buttons
	private int setLiftIn = ControllerMap.X;
	private int setLiftOut = ControllerMap.Y;
	
	public OI() {
		
		driver = new ControllerMap(new Joystick(RobotMap.Drive.DRIVER_PORT), ControllerMap.LOGITECH);
		operator = new ControllerMap(new Joystick(RobotMap.Drive.OPERATOR_PORT), ControllerMap.LOGITECH);
		
		autonChoice = createChoices("Auton Choices", AutonChooser.AUTON_CHOICES);
		
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
    	
    	Button liftIn = driver.createButton(setLiftIn);
    	liftIn.whenPressed(new SetLift(LiftSubsystem.IN));
    	
    	Button liftOut = driver.createButton(setLiftOut);
    	liftOut.whenPressed(new SetLift(LiftSubsystem.OUT));
    }
    
    public void initOperator() {
    	// TODO: Put Operator Buttons and Commands;
    }
    
    public void initSD() {
    	// TODO: Put SmartDashboard Values;
    }
    
    public int getAutonChoice() {
        return ((Integer) this.autonChoice.getSelected()).intValue();
   }
   
   private SendableChooser createChoices(String label, String [] choices) {
       SendableChooser send = new SendableChooser();
       
       if(choices.length > 0) {
           send.addDefault(choices[0], new Integer(0));
           
           for(int i = 1; i < choices.length; i++) {
               send.addObject(choices[i], new Integer(i));
           }
           
           SmartDashboard.putData(label, send);
       }
       
       return send;
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

