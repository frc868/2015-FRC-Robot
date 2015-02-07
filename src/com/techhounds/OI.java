package com.techhounds;

import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.commands.auton.DriveToClosestTote;
import com.techhounds.commands.bin.SetBin;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.OperatorHalfDrive;
import com.techhounds.commands.driving.ToggleDriveMode;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetPassiveStop;
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
		
	//Driver buttons
	private final int toggleForward = 	PS4Map.OPTIONS;
	private final int toggleHalf = 		PS4Map.R2;
	private final int liftUp = 			PS4Map.TRIANGLE;
	private final int liftDown = 		PS4Map.CROSS;
	private final int liftIn = 			PS4Map.SQUARE;
	private final int liftOut = 		PS4Map.CIRCLE;
	private final int binsUp = 			PS4Map.UP;
	private final int binsDown = 		PS4Map.DOWN;
	private final int binsOpen = 		PS4Map.LEFT;
	private final int binsClose = 		PS4Map.RIGHT;
	private final int binsTiltUp = 		PS4Map.L1;
	private final int binsTiltDown = 	PS4Map.L2;
	private final int togglePassive = 	PS4Map.R1;

//	private final int toggleForward = 	ControllerMap.START;
//    private final int toggleHalf = 		ControllerMap.RT;
//    private final int liftUp = 			ControllerMap.Y;
//    private final int liftDown = 		ControllerMap.A;
//    private final int liftIn = 			ControllerMap.X;
//    private final int liftOut = 		ControllerMap.B;
//    private final int binsUp = 			ControllerMap.UP;
//    private final int binsDown = 		ControllerMap.DOWN;
//    private final int binsOpen = 		ControllerMap.LEFT;
//    private final int binsClose = 		ControllerMap.RIGHT;
//    private final int binsTiltUp = 		ControllerMap.LB;
//    private final int binsTiltDown = 	ControllerMap.LT;
//    private final int togglePassive = 	ControllerMap.RB;
    
	
    //Tweaker buttons
  	private final int opToggleForward = 	ControllerMap.START;
    private final int opPushHalf = 			ControllerMap.RT;
    private final int opLiftUp = 			ControllerMap.Y;
    private final int opLiftDown = 			ControllerMap.A;
    private final int opLiftIn = 			ControllerMap.X;
    private final int opLiftOut = 			ControllerMap.B;
    private final int opBinsUp = 			ControllerMap.UP;
    private final int opBinsDown = 			ControllerMap.DOWN;
    private final int opBinsOpen = 			ControllerMap.LEFT;
    private final int opBinsClose = 		ControllerMap.RIGHT;
    private final int opBinsTiltUp = 		ControllerMap.LB;
    private final int opBinsTiltDown = 		ControllerMap.LT;
    private final int opTogglePassive = 	ControllerMap.RB;
    
	public OI() {
		
		driver = new PS4Map(new Joystick(RobotMap.DRIVER_PORT));
//		driver = new ControllerMap(new Joystick(RobotMap.DRIVER_PORT), ControllerMap.LOGITECH, true);
		operator = new ControllerMap(new Joystick(RobotMap.OPERATOR_PORT), ControllerMap.LOGITECH, true);
		
		autonChoice = createChoices("Auton Choices", AutonChooser.AUTON_CHOICES);

    	initDriver();
    	initOperator();
    	initSD();
	}
	
	public static OI getInstance() {
		if(instance == null)
			instance = new OI();
		return instance;
	}
    
    public void initDriver() {

        Button toggleDriveForward = driver.createButton(toggleForward);
        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false, false));
        
//        Button toggleHalfSpeed = driver.createButton(toggleHalf);
//        toggleHalfSpeed.whenPressed(new ToggleDriveMode(false, true, false));
        
        Button toggleHalfSpeed = driver.createButton(toggleHalf);
        toggleHalfSpeed.whenPressed(new OperatorHalfDrive(true));
        toggleHalfSpeed.whenReleased(new OperatorHalfDrive(false));

        Button setLiftUp = driver.createButton(liftUp);
        setLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
        setLiftUp.whenReleased(new SetLift(LiftSubsystem.STOPPED));
        
        Button setLiftDown = driver.createButton(liftDown);
        setLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
        setLiftDown.whenReleased(new SetLift(LiftSubsystem.STOPPED));

        Button setLiftIn = driver.createButton(liftIn);
        setLiftIn.whenPressed(new SetLift(LiftSubsystem.CLOSED));
        
        Button setLiftOut = driver.createButton(liftOut);
        setLiftOut.whenPressed(new SetLift(LiftSubsystem.OPEN));

		Button binsButtonUp = driver.createDPadButton(binsUp);
		binsButtonUp.whenPressed(new SetBin(BinSubsystem.UP));
		binsButtonUp.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button binsButtonDown = driver.createDPadButton(binsDown);
		binsButtonDown.whenPressed(new SetBin(BinSubsystem.DOWN));
		binsButtonDown.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button binsButtonOpen = driver.createDPadButton(binsOpen);
		binsButtonOpen.whenPressed(new SetBin(BinSubsystem.OPEN));
		
		Button binsButtonClosed = driver.createDPadButton(binsClose);
		binsButtonClosed.whenPressed(new SetBin(BinSubsystem.CLOSED));
		
		Button binTiltUp = driver.createButton(binsTiltUp);
		binTiltUp.whenPressed(new SetBin(BinSubsystem.TILT_UP, 0));
		
		Button binTiltDown = driver.createButton(binsTiltDown);
		binTiltDown.whenPressed(new SetBin(BinSubsystem.TILT_DOWN, 0));
		
//		Button togglePassiveStop = driver.createButton(togglePassive);
//		togglePassiveStop.whenPressed(new SetPassiveStop());
    }
    
    public void initOperator() {
    	
    	Button toggleDriveForward = operator.createButton(opToggleForward);
        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false, false));
        
        Button pushHalfSpeed = operator.createButton(opPushHalf);
        pushHalfSpeed.whenPressed(new OperatorHalfDrive(true));
        pushHalfSpeed.whenReleased(new OperatorHalfDrive(false));

        Button setLiftUp = operator.createButton(opLiftUp);
        setLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
        setLiftUp.whenReleased(new SetLift(LiftSubsystem.STOPPED));
        
        Button setLiftDown = operator.createButton(opLiftDown);
        setLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
        setLiftDown.whenReleased(new SetLift(LiftSubsystem.STOPPED));

        Button setLiftIn = operator.createButton(opLiftIn);
        setLiftIn.whenPressed(new SetLift(LiftSubsystem.CLOSED));
        
        Button setLiftOut = operator.createButton(opLiftOut);
        setLiftOut.whenPressed(new SetLift(LiftSubsystem.OPEN));
        
        Button opBinsButtonUp = operator.createDPadButton(binsUp);
		opBinsButtonUp.whenPressed(new SetBin(BinSubsystem.UP));
		opBinsButtonUp.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button opBinsButtonDown = operator.createDPadButton(opBinsDown);
		opBinsButtonDown.whenPressed(new SetBin(BinSubsystem.DOWN));
		opBinsButtonDown.whenReleased(new SetBin(BinSubsystem.STOPPED));
		
		Button opBinsButtonOpen = operator.createDPadButton(opBinsOpen);
		opBinsButtonOpen.whenPressed(new SetBin(BinSubsystem.OPEN));
		
		Button opBinsButtonClosed = operator.createDPadButton(opBinsClose);
		opBinsButtonClosed.whenPressed(new SetBin(BinSubsystem.CLOSED));
		
		Button opBinTiltUp = operator.createButton(opBinsTiltUp);
		opBinTiltUp.whenPressed(new SetBin(BinSubsystem.TILT_UP, 0));
		
		Button opBinTiltDown = operator.createButton(opBinsTiltDown);
		opBinTiltDown.whenPressed(new SetBin(BinSubsystem.TILT_DOWN, 0));
		
//		Button opTogglePassiveStop = operator.createButton(opTogglePassive);
//		opTogglePassiveStop.whenPressed(new SetPassiveStop());
		
    }
    
    public void initSD() {
    	SmartDashboard.putData("Goto Closest Tote", new DriveToClosestTote());
    	SmartDashboard.putData("Drive 3 sec", new DriveTime(3, .5));
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

