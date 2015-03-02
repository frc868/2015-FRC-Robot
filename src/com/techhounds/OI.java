package com.techhounds;

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
		
	//Driver buttons
//	private final int toggleForward = 	PS4Map.OPTIONS; // i like it
//	private final int toggleFull = 		PlaystationMap.R2;
	private final int liftUp = 			PlaystationMap.TRIANGLE;
	private final int liftDown = 		PlaystationMap.CROSS;
	private final int liftIn = 			PlaystationMap.SQUARE;
	private final int liftOut = 		PlaystationMap.CIRCLE;
//	private final int oneToteHeight = 	PS4Map.R1;
//	private final int toteOnGround = 	PS4Map.R2;
//	private final int upOneLevel = 		PS4Map.UP;
//	private final int downOneLevel = 	PS4Map.DOWN;
	private final int passiveIn =		PlaystationMap.LEFT;
	private final int passiveOut =		PlaystationMap.RIGHT;
	private final int passivePushStop = PlaystationMap.UP;
	private final int feederClose =		PlaystationMap.L1;
	private final int feederOpen =		PlaystationMap.L2;
	private final int feederIn =		PlaystationMap.R1;//TEMP
	private final int feederOut =		PlaystationMap.R2;//TEMP
//	private final int feederPosToggle =	PlaystationMap.RIGHT;
//	private final int feederOutToggle = PS4Map.UP;
//	private final int feederInToggle = 	PS4Map.DOWN;
	private final int allOpen = 		PlaystationMap.OPTIONS;
	private final int wink = 			PlaystationMap.SHARE;
	
//	private final int toggleForward = 	ControllerMap.START;
//    private final int toggleHalf = 		ControllerMap.RT;
//    private final int liftUp = 			ControllerMap.Y;
//    private final int liftDown = 		ControllerMap.A;
//    private final int liftIn = 			ControllerMap.X;
//    private final int liftOut = 		ControllerMap.B;
////    private final int togglePassive = 	ControllerMap.RB;
    
	
    //Tweaker buttons
//  	private final int opToggleForward = 	ControllerMap.START;
//    private final int opPushHalf = 			ControllerMap.RT;
    private final int opLiftUp = 			ControllerMap.Y;
    private final int opLiftDown = 			ControllerMap.A;
    private final int opLiftIn = 			ControllerMap.X;
    private final int opLiftOut = 			ControllerMap.B;
//	private final int opOneToteHeight = 	ControllerMap.RB;
//	private final int opToteOnGround =	 	ControllerMap.RT;
	private final int opUpOneLevel = 		ControllerMap.BACK;
//	private final int opDownOneLevel = 		ControllerMap.DOWN;
	private final int opPassiveIn =			ControllerMap.RB;
	private final int opPassiveOut =		ControllerMap.RT;
	private final int opPassiveStop = 		ControllerMap.LT;
//	private final int opFeederClose =		ControllerMap.LB;
	private final int opFeederOpen =		ControllerMap.LB;
	private final int opFeederIn =			ControllerMap.DOWN;
	private final int opFeederOut =			ControllerMap.UP;
	private final int opOpenAll = 			ControllerMap.START;
	private final int opFeederOffA =		ControllerMap.LEFT;
	private final int opFeederOffB = 		ControllerMap.RIGHT;
    
	public OI() {
		
		driver = new PlaystationMap(new Joystick(RobotMap.DRIVER_PORT), PlaystationMap.PS4_WIN);
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

//        Button toggleDriveForward = driver.createButton(toggleForward);
//        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false, false));
        
//        Button toggleHalfSpeed = driver.createButton(toggleHalf);
//        toggleHalfSpeed.whenPressed(new ToggleDriveMode(false, true, false));
//        Button toggleFullSpeed = driver.createButton(toggleFull);
//        toggleFullSpeed.whenPressed(new OperatorFullDrive(true));
//        toggleFullSpeed.whenReleased(new OperatorFullDrive(false));

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
        
        Button passIn = driver.createButton(passiveIn);
        passIn.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));

        Button passOut = driver.createButton(passiveOut);
        passOut.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        
        Button passStop = driver.createButton(passivePushStop);
        passStop.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
        passStop.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
        
        Button feedClose = driver.createButton(feederClose);
        feedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
        
        Button feedOpen = driver.createButton(feederOpen);
        feedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
//        
        Button feedIn = driver.createButton(feederIn);
        feedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        feedIn.whenReleased(new SetFeeder(0));
        
        Button feedOut = driver.createButton(feederOut);
        feedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        feedOut.whenReleased(new SetFeeder(0));
        
//        Button feederPositionToggle = driver.createButton(feederPosToggle);
//        feederPositionToggle.whenPressed(new SetFeeder(true, true));
        
//        Button feederPushOutToggle = driver.createButton(feederOutToggle);
//        feederPushOutToggle.whenPressed(new SetFeeder(true, FeederSubsystem.FEED_OUT));
//        
//        Button feederPullInToggle = driver.createButton(feederInToggle);
//        feederPullInToggle.whenPressed(new SetFeeder(true, FeederSubsystem.FEED_IN));

        Button openAll = driver.createButton(allOpen);
        openAll.whenPressed(new SetLift(LiftSubsystem.OPEN));
        openAll.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        openAll.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        openAll.whenPressed(new Wink());
        
        Button winky = driver.createButton(wink);
        winky.whenPressed(new Wink());
        
        // Rumble Test
//        Button rumblePS4 = driver.createButton(PlaystationMap.OPTIONS);
//        rumblePS4.whenPressed(new Rumble(driver, 2.0));
    }
    
    public void initOperator() {
    	
//    	Button toggleDriveForward = operator.createButton(opToggleForward);
//        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false, false));
        
//        Button pushHalfSpeed = operator.createButton(opPushHalf);
//        pushHalfSpeed.whenPressed(new OperatorFullDrive(true));
//        pushHalfSpeed.whenReleased(new OperatorFullDrive(false));

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

//        Button oneHeight = operator.createButton(opOneToteHeight);
//        oneHeight.whenPressed(new SetLiftHeight(LiftSubsystem.ONE_TOTE_HEIGHT));
//        
//        Button offGround = operator.createButton(opToteOnGround);
//        offGround.whenPressed(new SetLiftHeight(0));
        
	    Button upLevel = operator.createButton(opUpOneLevel);
	    upLevel.whenPressed(new NextLevel(LiftSubsystem.UP));

        Button passIn = operator.createButton(opPassiveIn);
        passIn.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));

        Button passOut = operator.createButton(opPassiveOut);
        passOut.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        
        Button passStop = operator.createButton(opPassiveStop);
        passStop.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
        passStop.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
        
//        Button feedClose = operator.createButton(opFeederClose);
//        feedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
//        feedClose.whenReleased(new SetFeeder(FeederSubsystem.OPEN));
        
        Button feedOpen = operator.createButton(opFeederOpen);
        feedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        feedOpen.whenReleased(new SetFeeder(FeederSubsystem.CLOSED));
        
        Button feedIn = operator.createButton(opFeederIn);
        feedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        feedIn.whenReleased(new SetFeeder(0));
        
        Button feedOut = operator.createButton(opFeederOut);
        feedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        feedOut.whenReleased(new SetFeeder(0));
        
        Button openAll = operator.createButton(opOpenAll);
        openAll.whenPressed(new SetLift(LiftSubsystem.OPEN));
        openAll.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        openAll.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        openAll.whenPressed(new Wink());
        
        Button feederStopA = operator.createButton(opFeederOffA);
        feederStopA.whenPressed(new SetFeeder(FeederSubsystem.STOPPED));
        
        Button feederStopB = operator.createButton(opFeederOffB);
        feederStopB.whenPressed(new SetFeeder(FeederSubsystem.STOPPED));
        
    }
    
    public void initSD() {
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

