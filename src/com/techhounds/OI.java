package com.techhounds;

import com.techhounds.commands.UpdateDriverCont;
import com.techhounds.commands.WaitForToteLift;
import com.techhounds.commands.Wink;
import com.techhounds.commands.Rumble;
import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.commands.auton.AutonDrive;
import com.techhounds.commands.auton.DriveToClosestTote;
import com.techhounds.commands.auton.FirstTote;
import com.techhounds.commands.auton.MoveToAutoZone;
import com.techhounds.commands.auton.NextTote;
import com.techhounds.commands.auton.ReverseThreeTote;
import com.techhounds.commands.auton.ThreeTote;
import com.techhounds.commands.auton.TwoTote;
import com.techhounds.commands.driving.DriveTime;
import com.techhounds.commands.driving.OperatorFullDrive;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.lift.NextLevel;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetPassiveStop;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.LiftSubsystem;
import com.techhounds.subsystems.PassiveSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Atif Niyaz, Alex Fleig, Matt Simmons, Ayon Mitra, Clayton Detke
 */
public class OI {
	
	private static OI instance;
	
	private static PlaystationMap driver;
	private static ControllerMap operator;
	
	private SendableChooser autonChoice;
	private SendableChooser driverContChoice;
		
	//Driver buttons
	private int liftUp;		Button setLiftUp;
	private int liftDown;	Button setLiftDown;
	private int liftIn;		Button setLiftIn;
	private int liftOut;	Button setLiftOut;
//	private int oneToteHeight;
//	private int toteOnGround;
//	private int upOneLevel;
//	private int downOneLevel;
	private int passiveIn;		Button passIn;
	private int passiveOut;		Button passOut;
	private int passivePushStop;Button passStop;
	private int feederClose;	Button feedClose;
	private int feederOpen;		Button feedOpen;
	private int feederIn;		Button feedIn;
	private int feederOut;		Button feedOut;
	private int allOpen;		Button openAll;
	private int wink;			Button winky;
	
    //Tweaker buttons
    private final int opLiftUp = 			ControllerMap.Y;		Button opSetLiftUp;
    private final int opLiftDown = 			ControllerMap.A;		Button opSetLiftDown;
    private final int opLiftIn = 			ControllerMap.X;		Button opSetLiftIn;
    private final int opLiftOut = 			ControllerMap.B;		Button opSetLiftOut;
//	private final int opOneToteHeight = 	ControllerMap.RB;
//	private final int opToteOnGround =	 	ControllerMap.RT;
	private final int opUpOneLevel = 		ControllerMap.BACK;		Button opUpLevel;
//	private final int opDownOneLevel = 		ControllerMap.DOWN;
	private final int opPassiveIn =			ControllerMap.RB;		Button opPassIn;
	private final int opPassiveOut =		ControllerMap.RT;		Button opPassOut;
	private final int opPassiveStop = 		ControllerMap.LT;		Button opPassStop;
//	private final int opFeederClose =		ControllerMap.LB;
	private final int opFeederOpen =		ControllerMap.LB;		Button opFeedOpen;
	private final int opFeederIn =			ControllerMap.DOWN;		Button opFeedIn;
	private final int opFeederOut =			ControllerMap.UP;		Button opFeedOut;
	private final int opAllOpen = 			ControllerMap.START;	Button opOpenAll;
	private final int opFeederOffA =		ControllerMap.LEFT;		Button opFeedOffA;
	private final int opFeederOffB = 		ControllerMap.RIGHT;	Button opFeedOffB;
	
	public void updateDriverCont(){
		driver = (PlaystationMap) driverContChoice.getSelected();
		if (driver.type == PlaystationMap.LOGITECH){

			liftUp = 			ControllerMap.Y;
			liftDown = 			ControllerMap.A;
			liftIn = 			ControllerMap.X;
			liftOut = 			ControllerMap.B;
			passiveIn =			ControllerMap.LEFT;
			passiveOut =		ControllerMap.RIGHT;
			passivePushStop = 	ControllerMap.UP;
			feederClose =		ControllerMap.LB;
			feederOpen =		ControllerMap.LT;
			feederIn =			ControllerMap.RB;
			feederOut =			ControllerMap.RT;
			allOpen = 			ControllerMap.START;
			wink = 				ControllerMap.BACK;
			
		}else{
			
			liftUp = 			PlaystationMap.TRIANGLE;
			liftDown = 			PlaystationMap.CROSS;
			liftIn = 			PlaystationMap.SQUARE;
			liftOut = 			PlaystationMap.CIRCLE;
			passiveIn =			PlaystationMap.LEFT;
			passiveOut =		PlaystationMap.RIGHT;
			passivePushStop = 	PlaystationMap.UP;
			feederClose =		PlaystationMap.L1;
			feederOpen =		PlaystationMap.L2;
			feederIn =			PlaystationMap.R1;
			feederOut =			PlaystationMap.R2;
			allOpen = 			PlaystationMap.OPTIONS;
			wink = 				PlaystationMap.SHARE;
		}
		
		initDriver();
	}
	
	public OI() {
		
		updateDriverCont();
		operator = new ControllerMap(new Joystick(RobotMap.OPERATOR_PORT), ControllerMap.LOGITECH, true);
		
		autonChoice = createAutonChoices();
		driverContChoice = createDriverChoices();

    	initOperator();
    	initSD();
	}
	
	public static OI getInstance() {
		if(instance == null)
			instance = new OI();
		return instance;
	}

    public void initDriver() {

        setLiftUp = driver.createButton(liftUp);
        setLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
        setLiftUp.whenReleased(new SetLift(LiftSubsystem.STOPPED));
        
        setLiftDown = driver.createButton(liftDown);
        setLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
        setLiftDown.whenReleased(new SetLift(LiftSubsystem.STOPPED));

        setLiftIn = driver.createButton(liftIn);
        setLiftIn.whenPressed(new SetLift(LiftSubsystem.CLOSED));
        
        setLiftOut = driver.createButton(liftOut);
        setLiftOut.whenPressed(new SetLift(LiftSubsystem.OPEN));

//        Button oneHeight = driver.createButton(oneToteHeight);
//        oneHeight.whenPressed(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//        
//        Button offGround = driver.createButton(toteOnGround);
//        offGround.whenPressed(new SetLiftHeight(0));
        
//        Button upLevel = driver.createButton(upOneLevel);
//        upLevel.whenPressed(new NextLevel(LiftSubsystem.UP));
//
//        Button downLevel = driver.createButton(downOneLevel);
//        downLevel.whenPressed(new NextLevel(LiftSubsystem.DOWN));
        
        passIn = driver.createButton(passiveIn);
        passIn.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));

        passOut = driver.createButton(passiveOut);
        passOut.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        
        passStop = driver.createButton(passivePushStop);
        passStop.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
        passStop.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
        
        feedClose = driver.createButton(feederClose);
        feedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
        
        feedOpen = driver.createButton(feederOpen);
        feedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        
        feedIn = driver.createButton(feederIn);
        feedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        feedIn.whenReleased(new SetFeeder(0));
        
        feedOut = driver.createButton(feederOut);
        feedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        feedOut.whenReleased(new SetFeeder(0));

        openAll = driver.createButton(allOpen);
        openAll.whenPressed(new SetLift(LiftSubsystem.OPEN));
        openAll.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        openAll.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        openAll.whenPressed(new Wink());
        
        winky = driver.createButton(wink);
        winky.whenPressed(new Wink());
        
        // Rumble Test
//        Button rumblePS4 = driver.createButton(PlaystationMap.OPTIONS);
//        rumblePS4.whenPressed(new Rumble(driver, 2.0));
    }
    
    public void initOperator() {

        opSetLiftUp = operator.createButton(opLiftUp);
        opSetLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
        opSetLiftUp.whenReleased(new SetLift(LiftSubsystem.STOPPED));
        
        opSetLiftDown = operator.createButton(opLiftDown);
        opSetLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
        opSetLiftDown.whenReleased(new SetLift(LiftSubsystem.STOPPED));

        opSetLiftIn = operator.createButton(opLiftIn);
        opSetLiftIn.whenPressed(new SetLift(LiftSubsystem.CLOSED));
        
        opSetLiftOut = operator.createButton(opLiftOut);
        opSetLiftOut.whenPressed(new SetLift(LiftSubsystem.OPEN));

//        Button oneHeight = operator.createButton(opOneToteHeight);
//        oneHeight.whenPressed(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//        
//        Button offGround = operator.createButton(opToteOnGround);
//        offGround.whenPressed(new SetLiftHeight(0));
        
	    opUpLevel = operator.createButton(opUpOneLevel);
	    opUpLevel.whenPressed(new NextLevel(LiftSubsystem.UP));

        opPassIn = operator.createButton(opPassiveIn);
        opPassIn.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));

        opPassOut = operator.createButton(opPassiveOut);
        opPassOut.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        
        opPassStop = operator.createButton(opPassiveStop);
        opPassStop.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
        opPassStop.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
        
//        Button feedClose = operator.createButton(opFeederClose);
//        feedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
//        feedClose.whenReleased(new SetFeeder(FeederSubsystem.OPEN));
        
        opFeedOpen = operator.createButton(opFeederOpen);
        opFeedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        opFeedOpen.whenReleased(new SetFeeder(FeederSubsystem.CLOSED));
        
        opFeedIn = operator.createButton(opFeederIn);
        opFeedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        opFeedIn.whenReleased(new SetFeeder(0));
        
        opFeedOut = operator.createButton(opFeederOut);
        opFeedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        opFeedOut.whenReleased(new SetFeeder(0));
        
        opOpenAll = operator.createButton(opAllOpen);
        opOpenAll.whenPressed(new SetLift(LiftSubsystem.OPEN));
        opOpenAll.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        opOpenAll.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        opOpenAll.whenPressed(new Wink());
        
        opFeedOffA = operator.createButton(opFeederOffA);
        opFeedOffA.whenPressed(new SetFeeder(FeederSubsystem.STOPPED));
        
        opFeedOffB = operator.createButton(opFeederOffB);
        opFeedOffB.whenPressed(new SetFeeder(FeederSubsystem.STOPPED));
        
    }
    
    public void initSD() {
    	
    	SmartDashboard.putData("Update Controller", new UpdateDriverCont());
    	
//    	SmartDashboard.putData("Move To Zone", new MoveToAutoZone(1.5));
    	SmartDashboard.putData("Goto Closest Tote", new DriveToClosestTote());
//    	SmartDashboard.putData("One Crate", new FirstTote());
//    	SmartDashboard.putData("Next Crate", new NextTote(false));
//    	SmartDashboard.putData("Final Crate", new NextTote(true));
//    	SmartDashboard.putData("Two Tote", new TwoTote(true, 2));
//    	SmartDashboard.putData("Three Tote", new ThreeTote(true, 2));
//    	SmartDashboard.putData("Reverse Three Tote, Start L", new ReverseThreeTote(true, true, true));
//    	SmartDashboard.putData("Reverse Three Tote, Start R", new ReverseThreeTote(true, false, true));
//    	
//    	SmartDashboard.putData("Move Forward", new DriveTime(.75, .4, false));
//
//    	SmartDashboard.putData("Gyro Rotate 90", new RotateToAngle(90, 1));
//    	SmartDashboard.putData("Gyro Rotate 270", new RotateToAngle(270, 3));
//    	SmartDashboard.putData("Gyro Rotate 180", new RotateToAngle(180, 2));
//    	SmartDashboard.putData("Drive PID", new AutonDrive(6.75, 3));
//    	
//    	SmartDashboard.putData("Wait Tote Lift", new WaitForToteLift());
//    	SmartDashboard.putData("Wink", new Wink());
    }
    
    public int getAutonChoice() {
        return ((Integer) autonChoice.getSelected()).intValue();
    }
   
    private SendableChooser createAutonChoices() {
        SendableChooser send = new SendableChooser();
        String[] choices = AutonChooser.AUTON_CHOICES;
	       
	       if(choices.length > 0) {
	           send.addDefault(choices[0], new Integer(0));
	           
	           for(int i = 1; i < choices.length; i++) {
	               send.addObject(choices[i], new Integer(i));
	           }
	           
	           SmartDashboard.putData("Auton Choices", send);
	       }
	       
	       return send;
	}
    
	private SendableChooser createDriverChoices(){
		SendableChooser send = new SendableChooser();
	    send.addDefault("Aryaman's DS4", new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), PlaystationMap.PS4_DS4));
	    send.addObject("Aryaman's WIN", new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), PlaystationMap.PS4_WIN));
	    send.addObject("Logitech", new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), ControllerMap.LOGITECH, true));
        SmartDashboard.putData("Driver Controller", send);
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

