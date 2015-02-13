package com.techhounds;

import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.commands.auton.DriveToClosestTote;
import com.techhounds.commands.auton.MoveToAutoZone;
import com.techhounds.commands.auton.NextTote;
import com.techhounds.commands.auton.FirstTote;
import com.techhounds.commands.auton.ReverseThreeTote;
import com.techhounds.commands.auton.ThreeTote;
import com.techhounds.commands.auton.TwoTote;
import com.techhounds.commands.bin.SetBin;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.ManualTurn;
import com.techhounds.commands.driving.OperatorHalfDrive;
import com.techhounds.commands.driving.ToggleDriveMode;
import com.techhounds.commands.lift.NextLevel;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
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
	private final int toggleForward = 	PS4Map.OPTIONS; // i like it
	private final int toggleHalf = 		PS4Map.R2;
	private final int liftUp = 			PS4Map.TRIANGLE;
	private final int liftDown = 		PS4Map.CROSS;
	private final int liftIn = 			PS4Map.SQUARE;
	private final int liftOut = 		PS4Map.CIRCLE;
	private final int oneToteHeight = 	PS4Map.R1;
	private final int toteOnGround = 	PS4Map.R2;
	private final int upOneLevel = 		PS4Map.UP;
	private final int downOneLevel = 	PS4Map.DOWN;
//	private final int binsUp = 			PS4Map.UP;
//	private final int binsDown = 		PS4Map.DOWN;
//	private final int binsOpen = 		PS4Map.LEFT;
//	private final int binsClose = 		PS4Map.RIGHT;
//	private final int binsTiltUp = 		PS4Map.L1;
//	private final int binsTiltDown = 	PS4Map.L2;
//	private final int togglePassive = 	PS4Map.R1;

//	private final int toggleForward = 	ControllerMap.START;
//    private final int toggleHalf = 		ControllerMap.RT;
//    private final int liftUp = 			ControllerMap.Y;
//    private final int liftDown = 		ControllerMap.A;
//    private final int liftIn = 			ControllerMap.X;
//    private final int liftOut = 		ControllerMap.B;
////    private final int binsUp = 			ControllerMap.UP;
////    private final int binsDown = 		ControllerMap.DOWN;
////    private final int binsOpen = 		ControllerMap.LEFT;
////    private final int binsClose = 		ControllerMap.RIGHT;
////    private final int binsTiltUp = 		ControllerMap.LB;
////    private final int binsTiltDown = 	ControllerMap.LT;
////    private final int togglePassive = 	ControllerMap.RB;
    
	
    //Tweaker buttons
  	private final int opToggleForward = 	ControllerMap.START;
    private final int opPushHalf = 			ControllerMap.RT;
    private final int opLiftUp = 			ControllerMap.Y;
    private final int opLiftDown = 			ControllerMap.A;
    private final int opLiftIn = 			ControllerMap.X;
    private final int opLiftOut = 			ControllerMap.B;
	private final int opOneToteHeight = 	ControllerMap.RB;
	private final int opToteOnGround =	 	ControllerMap.RT;
	private final int opUpOneLevel = 		ControllerMap.UP;
	private final int opDownOneLevel = 		ControllerMap.DOWN;
//    private final int opBinsUp = 			ControllerMap.UP;
//    private final int opBinsDown = 			ControllerMap.DOWN;
//    private final int opBinsOpen = 			ControllerMap.LEFT;
//    private final int opBinsClose = 		ControllerMap.RIGHT;
//    private final int opBinsTiltUp = 		ControllerMap.LB;
//    private final int opBinsTiltDown = 		ControllerMap.LT;
//    private final int opTogglePassive = 	ControllerMap.RB;
    
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

        Button oneHeight = driver.createButton(oneToteHeight);
        oneHeight.whenPressed(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
        
        Button offGround = driver.createButton(toteOnGround);
        offGround.whenPressed(new SetLiftHeight(0));
        
        Button upLevel = driver.createButton(upOneLevel);
        upLevel.whenPressed(new NextLevel(LiftSubsystem.UP));

        Button downLevel = driver.createButton(downOneLevel);
        downLevel.whenPressed(new NextLevel(LiftSubsystem.DOWN));
        
//		Button binsButtonUp = driver.createDPadButton(binsUp);
//		binsButtonUp.whenPressed(new SetBin(BinSubsystem.UP));
//		binsButtonUp.whenReleased(new SetBin(BinSubsystem.STOPPED));
//		
//		Button binsButtonDown = driver.createDPadButton(binsDown);
//		binsButtonDown.whenPressed(new SetBin(BinSubsystem.DOWN));
//		binsButtonDown.whenReleased(new SetBin(BinSubsystem.STOPPED));
//		
//		Button binsButtonOpen = driver.createDPadButton(binsOpen);
//		binsButtonOpen.whenPressed(new SetBin(BinSubsystem.OPEN));
//		
//		Button binsButtonClosed = driver.createDPadButton(binsClose);
//		binsButtonClosed.whenPressed(new SetBin(BinSubsystem.CLOSED));
//		
//		Button binTiltUp = driver.createButton(binsTiltUp);
//		binTiltUp.whenPressed(new SetBin(BinSubsystem.TILT_UP, 0));
//		
//		Button binTiltDown = driver.createButton(binsTiltDown);
//		binTiltDown.whenPressed(new SetBin(BinSubsystem.TILT_DOWN, 0));
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

        Button oneHeight = operator.createButton(opOneToteHeight);
        oneHeight.whenPressed(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
        
        Button offGround = operator.createButton(opToteOnGround);
        offGround.whenPressed(new SetLiftHeight(0));
        
        Button upLevel = operator.createButton(opUpOneLevel);
        upLevel.whenPressed(new NextLevel(LiftSubsystem.UP));

        Button downLevel = operator.createButton(opDownOneLevel);
        downLevel.whenPressed(new NextLevel(LiftSubsystem.DOWN));
        
//        Button opBinsButtonUp = operator.createDPadButton(binsUp);
//		opBinsButtonUp.whenPressed(new SetBin(BinSubsystem.UP));
//		opBinsButtonUp.whenReleased(new SetBin(BinSubsystem.STOPPED));
//		
//		Button opBinsButtonDown = operator.createDPadButton(opBinsDown);
//		opBinsButtonDown.whenPressed(new SetBin(BinSubsystem.DOWN));
//		opBinsButtonDown.whenReleased(new SetBin(BinSubsystem.STOPPED));
//		
//		Button opBinsButtonOpen = operator.createDPadButton(opBinsOpen);
//		opBinsButtonOpen.whenPressed(new SetBin(BinSubsystem.OPEN));
//		
//		Button opBinsButtonClosed = operator.createDPadButton(opBinsClose);
//		opBinsButtonClosed.whenPressed(new SetBin(BinSubsystem.CLOSED));
//		
//		Button opBinTiltUp = operator.createButton(opBinsTiltUp);
//		opBinTiltUp.whenPressed(new SetBin(BinSubsystem.TILT_UP, 0));
//		
//		Button opBinTiltDown = operator.createButton(opBinsTiltDown);
//		opBinTiltDown.whenPressed(new SetBin(BinSubsystem.TILT_DOWN, 0));
		
    }
    
    public void initSD() {
    	SmartDashboard.putData("Move To Zone", new MoveToAutoZone(1.5));
    	SmartDashboard.putData("Goto Closest Tote", new DriveToClosestTote());
    	SmartDashboard.putData("One Crate", new FirstTote());
    	SmartDashboard.putData("Next Crate", new NextTote(false));
    	SmartDashboard.putData("Final Crate", new NextTote(true));
    	SmartDashboard.putData("Two Tote", new TwoTote());
    	SmartDashboard.putData("Three Tote", new ThreeTote(true, 2));
    	SmartDashboard.putData("Turn 90?", new ManualTurn(.75, 1.5, false));
    	SmartDashboard.putData("Reverse Three Tote, Start L", new ReverseThreeTote(true, true, true));
    	SmartDashboard.putData("Reverse Three Tote, Start R", new ReverseThreeTote(true, false, true));
    	SmartDashboard.putData("Move Forward", new DriveTime(.75, .4, false));
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

