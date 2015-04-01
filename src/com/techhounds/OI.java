package com.techhounds;

import java.util.Date;

import com.techhounds.commands.Debug;
import com.techhounds.commands.GoFishing;
import com.techhounds.commands.RefreshAutoChooser;
import com.techhounds.commands.SetOpFeedMode;
import com.techhounds.commands.UpdateDriverCont;
import com.techhounds.commands.Wink;
import com.techhounds.commands.auton.AutonChooser;
import com.techhounds.commands.auton.AutonDrive;
import com.techhounds.commands.auton.DriveToClosestTote;
import com.techhounds.commands.driving.RotateToAngle;
import com.techhounds.commands.feeder.SetFeeder;
import com.techhounds.commands.lift.AddTote;
import com.techhounds.commands.lift.AddToteOneInch;
import com.techhounds.commands.lift.NextLevel;
import com.techhounds.commands.lift.PutRCPassive;
import com.techhounds.commands.lift.SetBrake;
import com.techhounds.commands.lift.SetLift;
import com.techhounds.commands.lift.SetLiftHeight;
import com.techhounds.commands.lift.SetPassiveStop;
import com.techhounds.subsystems.FeederSubsystem;
import com.techhounds.subsystems.FishingPoleSubsystem;
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
	public static OPBoardMap operatorBoard;
	
	public static boolean opFeedMode = true;
	
	public SendableChooser autonChoice;
	private static SendableChooser driverContChoice;
		
	//Driver buttons
	private static int liftUp;		static Button setLiftUp;
	private static int liftDown;	static Button setLiftDown;
	private static int liftIn;		static Button setLiftIn;
	private static int liftOut;		static Button setLiftOut;
	private static int passiveIn;		static Button passIn;
	private static int passiveOut;		static Button passOut;
	private static int passivePushStop;	static Button passStop;
	private static int feederClose;		static Button feedClose;
	private static int feederOpen;		static Button feedOpen;
	private static int feederIn;		static Button feedIn;
	private static int feederOut;		static Button feedOut;
	private static int allOpen;			static Button openAll;
	private static int wink;			static Button winky;
	
    //Tweaker buttons
//    private final int opLiftUp = 			ControllerMap.Y;		Button opSetLiftUp;
//    private final int opLiftDown = 			ControllerMap.A;		Button opSetLiftDown;
    private final int opLiftIn = 			ControllerMap.X;		Button opSetLiftIn;
    private final int opLiftOut = 			ControllerMap.B;		Button opSetLiftOut;
//	private final int opUpOneLevel = 		ControllerMap.BACK;		Button opUpLevel;
//	private final int opPassiveIn =			ControllerMap.RB;		Button opPassIn;
//	private final int opPassiveOut =		ControllerMap.RT;		Button opPassOut;
    private final int opPassiveToggle = 	ControllerMap.RB;		Button opPassTogg;
	private final int opPassiveStop = 		ControllerMap.LT;		Button opPassStop;
	private final int opFeederClose =		ControllerMap.LB;		Button opFeedClose;
//	private final int opFeederOpen =		ControllerMap.LB;		Button opFeedOpen;
	private final int opFeederIn =			ControllerMap.A;		Button opFeedIn;
	private final int opFeederOut =			ControllerMap.Y;		Button opFeedOut;
//	private final int opAllOpen = 			ControllerMap.START;	Button opOpenAll;
	private final int opFeederOff =			ControllerMap.DOWN;		Button opFeedOff;
//	private final int opGoFishingDown =		ControllerMap.DOWN;		Button opGoFishingD;
//	private final int opGoFishingUp = 		ControllerMap.UP;		Button opGoFishingU;
//	private final int opUpInch = 			ControllerMap.BACK;		Button opUpOneInch;
//	private final int opFeedSlightLeft =	ControllerMap.DIAG_UP_LEFT;	Button opFeedLittleLeft;
//	private final int opFeedSlightRight =	ControllerMap.DIAG_UP_RIGHT;Button opFeedLittleRight;
//	private final int opFeedHardLeft =		ControllerMap.LEFT;		Button opFeedLeft;
//	private final int opFeedHardRight =		ControllerMap.RIGHT;	Button opFeedRight;
	private final int opSetFeedMode = 		ControllerMap.START;	Button opSetFeed;
	private final int opSetDriveMode = 		ControllerMap.BACK;		Button opSetDrive;
	private final int opSetBrake = 			ControllerMap.RT;		Button opBrake;
	
	//OP Board buttons
	private final int opBoardFeederOpen = 			OPBoardMap.FEEDER_OPEN;		Button opBoardOpenFeeder;
	private final int opBoardFeederClose = 			OPBoardMap.FEEDER_CLOSE;	Button opBoardCloseFeeder;
	private final int opBoardFeederSpit =			OPBoardMap.FEEDER_SPIT; 	Button opBoardSpitFeeder;
	private final int opBoardFeederTake = 			OPBoardMap.FEEDER_TAKE; 	Button opBoardTakeFeeder;
	private final int opBoardOpenAll = 				OPBoardMap.OPEN_ALL;		Button opBoardAllOpen;
	private final int opBoardRCPassive = 			OPBoardMap.RC_TO_PASSIVE;	Button opBoardRcToPassive;
	private final int opBoardAddTote =				OPBoardMap.ADD_TOTE; 		Button opBoardToteAdd;
	private final int opBoardCloseTake = 			OPBoardMap.CLOSE_TAKE;	 	Button opBoardTakeClose;
	private final int opBoardCloseSpit = 			OPBoardMap.CLOSE_SPIT;		Button opBoardSpitClose;
	private final int opBoardWink = 				OPBoardMap.WINK;			Button opBoardWinky;
	private final int opBoardLetsFish =				OPBoardMap.FISHING; 		Button opBoardFishing;
	private final int opBoardLiftOpen = 			OPBoardMap.LIFT_OPEN; 		Button opBoardOpenLift;
	private final int opBoardLiftClose = 			OPBoardMap.LIFT_CLOSE;		Button opBoardCloseLift;
	private final int opBoardPassOpen = 			OPBoardMap.PASSIVE_OPEN;	Button opBoardPasOpen;
	private final int opBoardPassClose =			OPBoardMap.PASSIVE_CLOSE; 	Button opBoardPasClose;
	private final int opBoardPassBrek = 			OPBoardMap.PASSIVE_BREAK; 	Button opBoardPasBreak;
	private final int opBoardLevelDo = 				OPBoardMap.DOWN_TOTE_LEVEL;	Button opBoardDownLevel;
	private final int opBoardLevelUp =				OPBoardMap.UP_TOTE_LEVEL; 	Button opBoardUpLevel;
	private final int opBoardSlide =				OPBoardMap.SLIDER; 			Button opBoardSlider;

	
	public static void updateDriverCont(){
		
		int choice = (int) driverContChoice.getSelected();
		
		if (choice == 1)
			driver = new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), PlaystationMap.PS4_DS4);
		else if (choice == 0)
			driver = new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), PlaystationMap.PS4_WIN);
		else if (choice == 2)
			driver = new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), ControllerMap.LOGITECH, true);
		
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

		autonChoice = createAutonChoices();
		driverContChoice = createDriverChoices();
		
		updateDriverCont();
		operator = new ControllerMap(new Joystick(RobotMap.OPERATOR_PORT), ControllerMap.LOGITECH, true);
		operatorBoard = new OPBoardMap(new Joystick(RobotMap.OPERATOR_BOARD_PORT));
		
    	initOperator();
    	initOPBoard();
    	initSD();
	}
	
	public static OI getInstance() {
		if(instance == null)
			instance = new OI();
		return instance;
	}

    public static void initDriver() {

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
    }
    
    public void initOperator() {

//        opSetLiftUp = operator.createButton(opLiftUp);
//        opSetLiftUp.whenPressed(new SetLift(LiftSubsystem.UP));
//        opSetLiftUp.whenReleased(new SetLift(LiftSubsystem.STOPPED));
//        
//        opSetLiftDown = operator.createButton(opLiftDown);
//        opSetLiftDown.whenPressed(new SetLift(LiftSubsystem.DOWN));
//        opSetLiftDown.whenReleased(new SetLift(LiftSubsystem.STOPPED));

        opSetLiftIn = operator.createButton(opLiftIn);
        opSetLiftIn.whenPressed(new SetLift(LiftSubsystem.CLOSED));
        
        opSetLiftOut = operator.createButton(opLiftOut);
        opSetLiftOut.whenPressed(new SetLift(LiftSubsystem.OPEN));
        
//	    opUpLevel = operator.createButton(opUpOneLevel);
//	    opUpLevel.whenPressed(new NextLevel(LiftSubsystem.UP));

//        opPassIn = operator.createButton(opPassiveIn);
//        opPassIn.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));
//
//        opPassOut = operator.createButton(opPassiveOut);
//        opPassOut.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));

        opPassTogg = operator.createButton(opPassiveToggle);
        opPassTogg.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, true, 0));
        
        opPassStop = operator.createButton(opPassiveStop);
        opPassStop.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
        opPassStop.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
        
        opFeedClose = operator.createButton(opFeederClose);
        opFeedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
        opFeedClose.whenReleased(new SetFeeder(FeederSubsystem.OPEN));
        
//        opFeedOpen = operator.createButton(opFeederOpen);
//        opFeedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
//        opFeedOpen.whenReleased(new SetFeeder(FeederSubsystem.CLOSED));
        
        opFeedIn = operator.createButton(opFeederIn);
        opFeedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        opFeedIn.whenReleased(new SetFeeder(0));
        
        opFeedOut = operator.createButton(opFeederOut);
        opFeedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        opFeedOut.whenReleased(new SetFeeder(0));
        
//        opOpenAll = operator.createButton(opAllOpen);
//        opOpenAll.whenPressed(new SetLift(LiftSubsystem.OPEN));
//        opOpenAll.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
//        opOpenAll.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
//        opOpenAll.whenPressed(new Wink());
        
        opFeedOff = operator.createButton(opFeederOff);
        opFeedOff.whenPressed(new SetFeeder(FeederSubsystem.STOPPED));
    
//        opGoFishingD = operator.createButton(opGoFishingDown);
//        opGoFishingD.whenPressed(new GoFishing(FishingPoleSubsystem.OUT));
//        
//        opGoFishingU = operator.createButton(opGoFishingUp);
//        opGoFishingU.whenPressed(new GoFishing(FishingPoleSubsystem.IN));
        
//        opUpOneInch = operator.createButton(opUpInch);
//        opUpOneInch.whenPressed(new AddToteOneInch());
        
//        opFeedLittleLeft = operator.createButton(opFeedSlightLeft);
//        opFeedLittleLeft.whenPressed(new SetFeederMult(FeederSubsystem.STOPPED, FeederSubsystem.FEED_IN));
//        opFeedLittleLeft.whenReleased(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN));
//
//        opFeedLittleRight = operator.createButton(opFeedSlightRight);
//        opFeedLittleRight.whenPressed(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.STOPPED));
//        opFeedLittleRight.whenReleased(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN));
//
//        opFeedLeft = operator.createButton(opFeedHardLeft);
//        opFeedLeft.whenPressed(new SetFeederMult(FeederSubsystem.SLOW_FEED_OUT, FeederSubsystem.FEED_IN));
//        opFeedLeft.whenReleased(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN));
//
//        opFeedRight = operator.createButton(opFeedHardRight);
//        opFeedRight.whenPressed(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.SLOW_FEED_OUT));
//        opFeedRight.whenReleased(new SetFeederMult(FeederSubsystem.FEED_IN, FeederSubsystem.FEED_IN));
        
        opSetFeed = operator.createButton(opSetFeedMode);
        opSetFeed.whenPressed(new SetOpFeedMode(true));
        
        opSetDrive = operator.createButton(opSetDriveMode);
        opSetDrive.whenPressed(new SetOpFeedMode(false));
        
        opBrake = operator.createButton(opSetBrake);
        opBrake.whenPressed(new SetBrake(true));
        opBrake.whenReleased(new SetBrake(false));
    }
    
    public void initOPBoard() {
//    	if (true) {
//    		return;
//    	}
    	opBoardOpenFeeder = operatorBoard.createButton(opBoardFeederOpen);
    	opBoardOpenFeeder.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
    	opBoardOpenFeeder.whenPressed(new Debug("open feed"));
    	
    	opBoardCloseFeeder = operatorBoard.createButton(opBoardFeederClose);
    	opBoardCloseFeeder.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
    	opBoardCloseFeeder.whenPressed(new Debug("close feed"));

    	opBoardSpitFeeder = operatorBoard.createButton(opBoardFeederSpit);
    	opBoardSpitFeeder.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
    	opBoardSpitFeeder.whenReleased(new SetFeeder(FeederSubsystem.STOPPED));
    	opBoardSpitFeeder.whenPressed(new Debug("spit feed"));

    	opBoardTakeFeeder = operatorBoard.createButton(opBoardFeederTake);
    	opBoardTakeFeeder.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
    	opBoardTakeFeeder.whenReleased(new SetFeeder(FeederSubsystem.STOPPED));
    	opBoardTakeFeeder.whenPressed(new Debug("take feed"));

    	opBoardAllOpen = operatorBoard.createButton(opBoardOpenAll);
    	opBoardAllOpen.whenPressed(new SetLift(LiftSubsystem.OPEN));
    	opBoardAllOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
    	opBoardAllOpen.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
    	opBoardAllOpen.whenPressed(new Wink());
    	opBoardAllOpen.whenPressed(new Debug("open all"));

    	opBoardRcToPassive = operatorBoard.createButton(opBoardRCPassive);
    	opBoardRcToPassive.whenPressed(new PutRCPassive());
    	opBoardRcToPassive.whenPressed(new Debug("rc to pass"));

    	opBoardToteAdd = operatorBoard.createButton(opBoardAddTote);
    	opBoardToteAdd.whenPressed(new AddTote());
    	opBoardToteAdd.whenPressed(new Debug("add tote"));

    	opBoardTakeClose = operatorBoard.createButton(opBoardCloseTake);
    	opBoardTakeClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
    	opBoardTakeClose.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
    	opBoardTakeClose.whenReleased(new SetFeeder(FeederSubsystem.STOPPED));
    	opBoardTakeClose.whenPressed(new Debug("take close"));

    	opBoardSpitClose = operatorBoard.createButton(opBoardCloseSpit);
    	opBoardSpitClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
    	opBoardSpitClose.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
    	opBoardSpitClose.whenReleased(new SetFeeder(FeederSubsystem.STOPPED));
    	opBoardSpitClose.whenPressed(new Debug("spit close"));

    	opBoardWinky = operatorBoard.createButton(opBoardWink);
    	opBoardWinky.whenPressed(new Wink());
    	opBoardWinky.whenPressed(new Debug("wink"));

    	opBoardFishing = operatorBoard.createButton(opBoardLetsFish);
    	opBoardFishing.whenPressed(new GoFishing(FishingPoleSubsystem.OUT));
    	opBoardFishing.whenReleased(new GoFishing(FishingPoleSubsystem.IN));
    	opBoardFishing.whenPressed(new Debug("fish"));

    	opBoardOpenLift = operatorBoard.createButton(opBoardLiftOpen);
    	opBoardOpenLift.whenPressed(new SetLift(LiftSubsystem.OPEN));
    	opBoardOpenLift.whenPressed(new Debug("open lift"));

    	opBoardCloseLift = operatorBoard.createButton(opBoardLiftClose);
    	opBoardCloseLift.whenPressed(new SetLift(LiftSubsystem.CLOSED));
    	opBoardCloseLift.whenPressed(new Debug("close lift"));

    	opBoardPasOpen = operatorBoard.createButton(opBoardPassOpen);
    	opBoardPasOpen.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
    	opBoardPasOpen.whenPressed(new Debug("pass open"));
    	
    	opBoardPasClose = operatorBoard.createButton(opBoardPassClose);
    	opBoardPasClose.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));
    	opBoardPasClose.whenPressed(new Debug("pass close"));

    	opBoardPasBreak = operatorBoard.createButton(opBoardPassBrek);
    	opBoardPasBreak.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
    	opBoardPasBreak.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
    	opBoardPasBreak.whenPressed(new Debug("pass brake"));

    	opBoardDownLevel = operatorBoard.createButton(opBoardLevelDo);
    	opBoardDownLevel.whenPressed(new NextLevel(LiftSubsystem.DOWN));
    	opBoardDownLevel.whenPressed(new Debug("down level"));

    	opBoardUpLevel = operatorBoard.createButton(opBoardLevelUp);
    	opBoardUpLevel.whenPressed(new NextLevel(LiftSubsystem.UP));
    	opBoardUpLevel.whenPressed(new Debug("up level"));
    	
    	opBoardSlider = operatorBoard.createButton(opBoardSlide);
    	opBoardSlider.whileHeld(new SetLiftHeight(SetLiftHeight.USE_SLIDE));
    	opBoardSlider.whenReleased(new SetLiftHeight(SetLiftHeight.USE_SLIDE));
    	opBoardSlider.whenPressed(new Debug("slide"));
    }
    
    public void initSD() {
    	
    	SmartDashboard.putData("Update Controller", new UpdateDriverCont());
    	SmartDashboard.putData("Add Tote & 1 inch", new AddToteOneInch());
//    	SmartDashboard.putData("Move To Zone", new MoveToAutoZone(1.5));
    	SmartDashboard.putData("Goto Closest Tote", new DriveToClosestTote());    	
//    	SmartDashboard.putNumber("Controller Power", getDriverLeftYAxis());
//    	SmartDashboard.putNumber("Controller Steer", getDriverRightXAxis());
    	SmartDashboard.putData("Toggle Fishing", new GoFishing());
    	
    	SmartDashboard.putData("Refresh Auto Chooser", new RefreshAutoChooser());

    	SmartDashboard.putData("Gyro Rotate 90", new RotateToAngle(90, 1));
    	SmartDashboard.putData("Drive PID", new AutonDrive(4, 3));
    	
    	SmartDashboard.putBoolean("Slide Pressed", opBoardSlider.get());
   
    }
    
    public int getAutonChoice() {
        return ((Integer) autonChoice.getSelected()).intValue();
    }
   
    public SendableChooser createAutonChoices() {
        SendableChooser send = new SendableChooser();
        String[] choices = AutonChooser.AUTON_CHOICES;
	       
	       if(choices.length > 0) {
	           send.addDefault(choices[0], new Integer(0));
	           
	           for(int i = 1; i < choices.length; i++) {
	               send.addObject(choices[i], new Integer(i));
	           }
	           
	           SmartDashboard.putData("Auton Choices", send);
	           System.out.println("Put out chooser at " + new Date());
	           SmartDashboard.putString("AutonInit", "Put out chooser at " + new Date());
	       }
	       
	       return send;
	}
    
	private SendableChooser createDriverChoices(){
		SendableChooser send = new SendableChooser();
	    send.addDefault("Aryaman's Driver Station", new Integer(0));
	    send.addObject("Aryaman's SWARTZ", new Integer(1));
	    send.addObject("Logitech", new Integer(2));
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
    
    public static double getOPBoardSlider() {
//    	System.out.println("OP Height: " + operatorBoard.getSliderAxis());
    	return (operatorBoard.getSliderAxis() + 1) * 2;
    }
}