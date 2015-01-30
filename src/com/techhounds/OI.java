package com.techhounds;

import com.techhounds.commands.Debug;
import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.commands.bin.SetBin;
import com.techhounds.commands.driving.OperatorHalfDrive;
import com.techhounds.commands.driving.ToggleDriveMode;
import com.techhounds.commands.lift.RunLift;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.subsystems.*;

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
	private final int toggleForward = ControllerMap.START;
    private final int toggleHalf = ControllerMap.BACK;
    private final int liftUp = ControllerMap.Y;
    private final int liftDown = ControllerMap.A;
    private final int liftIn = ControllerMap.X;
    private final int liftOut = ControllerMap.B;
    private final int binsUp = ControllerMap.UP;
    private final int binsDown = ControllerMap.DOWN;
    private final int binsOpen = ControllerMap.LEFT;
    private final int binsClose = ControllerMap.RIGHT;
    private final int binsTiltUp = ControllerMap.LB;
    private final int binsTiltDown = ControllerMap.LT;
    
    //Tweaker buttons
  	private final int opToggleForward = ControllerMap.START;
    private final int operatorPushHalf = ControllerMap.LB;
    private final int opLiftUp = ControllerMap.Y;
    private final int opLiftDown = ControllerMap.A;
    private final int opLiftIn = ControllerMap.X;
    private final int opLiftOut = ControllerMap.B;
    private final int opBinsUp = ControllerMap.UP;
    private final int opBinsDown = ControllerMap.DOWN;
    private final int opBinsOpen = ControllerMap.LEFT;
    private final int opBinsClose = ControllerMap.RIGHT;
    private final int opBinsTiltUp = ControllerMap.LB;
    private final int opBinsTiltDown = ControllerMap.LT;
    
	public OI() {
		
		driver = new ControllerMap(new Joystick(RobotMap.Drive.DRIVER_PORT), ControllerMap.LOGITECH, true);
		operator = new ControllerMap(new Joystick(RobotMap.Drive.OPERATOR_PORT), ControllerMap.LOGITECH, true);
		
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

        Button toggleDriveForward = driver.createButton(toggleForward);
        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false));
        
        Button toggleHalfSpeed = driver.createButton(toggleHalf);
        toggleHalfSpeed.whenPressed(new ToggleDriveMode(false, true));

        Button setLiftUp = driver.createButton(liftUp);
        setLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
        setLiftUp.whenReleased(new SetLift(LiftSubsystem.UP, 0));
        
        Button setLiftDown = driver.createButton(liftDown);
        setLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
        setLiftDown.whenReleased(new SetLift(LiftSubsystem.DOWN, 0));

        Button setLiftIn = driver.createButton(liftIn);
        setLiftIn.whenPressed(new SetLift(LiftSubsystem.IN));
        
        Button setLiftOut = driver.createButton(liftOut);
        setLiftOut.whenPressed(new SetLift(LiftSubsystem.OUT));

		Button binsButtonUp = driver.createButton(binsUp);
		binsButtonUp.whenPressed(new SetBin(BinSubsystem.UP));
		binsButtonUp.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button binsButtonDown = driver.createButton(binsDown);
		binsButtonDown.whenPressed(new SetBin(BinSubsystem.DOWN));
		binsButtonDown.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button binsButtonOpen = driver.createButton(binsOpen);
		binsButtonOpen.whenPressed(new SetBin(BinSubsystem.OPEN));
		
		Button binsButtonClosed = driver.createButton(binsClose);
		binsButtonClosed.whenPressed(new SetBin(BinSubsystem.CLOSED));
		
		Button binTiltUp= driver.createButton(binsTiltUp);
		binTiltUp.whenInactive(new SetBin(BinSubsystem.TILT_UP, 0));
		
		Button binTiltDown= driver.createButton(binsTiltDown);
		binTiltDown.whenInactive(new SetBin(BinSubsystem.TILT_DOWN, 0));
    }
    
    public void initOperator() {
    	
    	Button toggleDriveForward = operator.createButton(opToggleForward);
        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false));
        
        Button pushHalfSpeed = operator.createButton(operatorPushHalf);
        pushHalfSpeed.whenPressed(new OperatorHalfDrive(true));
        pushHalfSpeed.whenReleased(new OperatorHalfDrive(false));

        Button setLiftUp = operator.createButton(opLiftUp);
        setLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
        setLiftUp.whenReleased(new SetLift(LiftSubsystem.UP, 0));
        
        Button setLiftDown = operator.createButton(opLiftDown);
        setLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
        setLiftDown.whenReleased(new SetLift(LiftSubsystem.DOWN, 0));

        Button setLiftIn = operator.createButton(opLiftIn);
        setLiftIn.whenPressed(new SetLift(LiftSubsystem.IN));
        
        Button setLiftOut = operator.createButton(opLiftOut);
        setLiftOut.whenPressed(new SetLift(LiftSubsystem.OUT));
		
		Button opBinsButtonUp = operator.createButton(opBinsUp);
		opBinsButtonUp.whenPressed(new SetBin(BinSubsystem.UP));
		opBinsButtonUp.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button opBinsButtonDown = operator.createButton(opBinsDown);
		opBinsButtonDown.whenPressed(new SetBin(BinSubsystem.DOWN));
		opBinsButtonDown.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button opBinsButtonOpen = operator.createButton(opBinsOpen);
		opBinsButtonOpen.whenPressed(new SetBin(BinSubsystem.OPEN));
		
		Button opBinsButtonClosed = operator.createButton(opBinsClose);
		opBinsButtonClosed.whenPressed(new SetBin(BinSubsystem.CLOSED));
		
		Button opBinTiltUp= operator.createButton(opBinsTiltUp);
		opBinTiltUp.whenInactive(new SetBin(BinSubsystem.TILT_UP, 0));
		
		Button opBinTiltDown= operator.createButton(opBinsTiltDown);
		opBinTiltDown.whenInactive(new SetBin(BinSubsystem.TILT_DOWN, 0));
		
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
    	return driver.getRightStickX();
    }
    
    public static double getDriverRightYAxis() {
    	return driver.getRightStickY();
    }
    
    public static double getDriverleftXAxis() {
    	return driver.getLeftStickX();
    }
    
    public static double getDriverLeftYAxis() {
    	return driver.getLeftStickY();
    }
    
    public static double getOperatorRightXAxis() {
    	return operator.getRightStickX();
    }
    
    public static double getOperatorRightYAxis() {
    	return operator.getRightStickY();
    }
    
    public static double getOperatorLeftXAxis() {
    	return operator.getLeftStickX();
    }
    
    public static double getOperatorLeftYAxis() {
    	return operator.getLeftStickY();
    }
}

