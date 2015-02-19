package com.techhounds;

import com.techhounds.commands.WaitForToteLift;
import com.techhounds.commands.Wink;
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
	
	private static ControllerMap driver;
	private static ControllerMap operator;
	
	private SendableChooser autonChoice;
		
	//Driver buttons
//	private final int toggleForward = 	PS4Map.OPTIONS; // i like it
	private final int toggleHalf = 		PS4Map.R2;
	private final int liftUp = 			PS4Map.TRIANGLE;
	private final int liftDown = 		PS4Map.CROSS;
	private final int liftIn = 			PS4Map.SQUARE;
	private final int liftOut = 		PS4Map.CIRCLE;
//	private final int oneToteHeight = 	PS4Map.R1;
//	private final int toteOnGround = 	PS4Map.R2;
//	private final int upOneLevel = 		PS4Map.UP;
//	private final int downOneLevel = 	PS4Map.DOWN;
	private final int passiveIn =		PS4Map.L1;
	private final int passiveOut =		PS4Map.R1;
	private final int passivePushStop = PS4Map.L2;
//	private final int feederClose =		PS4Map.LEFT;
//	private final int feederOpen =		PS4Map.RIGHT;
	private final int feederIn =		PS4Map.DOWN;
	private final int feederOut =		PS4Map.UP;
	private final int feederPosToggle =	PS4Map.RIGHT;
//	private final int feederOutToggle = PS4Map.UP;
//	private final int feederInToggle = 	PS4Map.DOWN;

//	private final int toggleForward = 	ControllerMap.START;
//    private final int toggleHalf = 		ControllerMap.RT;
//    private final int liftUp = 			ControllerMap.Y;
//    private final int liftDown = 		ControllerMap.A;
//    private final int liftIn = 			ControllerMap.X;
//    private final int liftOut = 		ControllerMap.B;
////    private final int togglePassive = 	ControllerMap.RB;
    
	
    //Tweaker buttons
//  	private final int opToggleForward = 	ControllerMap.START;
    private final int opPushHalf = 			ControllerMap.RT;
    private final int opLiftUp = 			ControllerMap.Y;
    private final int opLiftDown = 			ControllerMap.A;
    private final int opLiftIn = 			ControllerMap.X;
    private final int opLiftOut = 			ControllerMap.B;
//	private final int opOneToteHeight = 	ControllerMap.RB;
//	private final int opToteOnGround =	 	ControllerMap.RT;
//	private final int opUpOneLevel = 		ControllerMap.UP;
//	private final int opDownOneLevel = 		ControllerMap.DOWN;
	private final int opPassiveIn =			ControllerMap.RB;
	private final int opPassiveOut =		ControllerMap.RT;
	private final int opPassiveStop = 		ControllerMap.LT;
	private final int opPassiveFree = 		ControllerMap.LB;
	private final int opFeederClose =		ControllerMap.RIGHT;
	private final int opFeederOpen =		ControllerMap.LEFT;
	private final int opFeederIn =			ControllerMap.DOWN;
	private final int opFeederOut =			ControllerMap.UP;
    
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

//        Button toggleDriveForward = driver.createButton(toggleForward);
//        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false, false));
        
//        Button toggleHalfSpeed = driver.createButton(toggleHalf);
//        toggleHalfSpeed.whenPressed(new ToggleDriveMode(false, true, false));
        Button toggleHalfSpeed = driver.createButton(toggleHalf);
        toggleHalfSpeed.whenPressed(new OperatorFullDrive(true));
        toggleHalfSpeed.whenReleased(new OperatorFullDrive(false));

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
        
//        Button feedClose = driver.createButton(feederClose);
//        feedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
//        
//        Button feedOpen = driver.createButton(feederOpen);
//        feedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
//        
        Button feedIn = driver.createButton(feederIn);
        feedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        feedIn.whenReleased(new SetFeeder(0));
        
        Button feedOut = driver.createButton(feederOut);
        feedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        feedOut.whenReleased(new SetFeeder(0));
        
        Button feederPositionToggle = driver.createButton(feederPosToggle);
        feederPositionToggle.whenPressed(new SetFeeder(true, true));
        
//        Button feederPushOutToggle = driver.createButton(feederOutToggle);
//        feederPushOutToggle.whenPressed(new SetFeeder(true, FeederSubsystem.FEED_OUT));
//        
//        Button feederPullInToggle = driver.createButton(feederInToggle);
//        feederPullInToggle.whenPressed(new SetFeeder(true, FeederSubsystem.FEED_IN));
    }
    
    public void initOperator() {
    	
//    	Button toggleDriveForward = operator.createButton(opToggleForward);
//        toggleDriveForward.whenPressed(new ToggleDriveMode(true, false, false));
        
        Button pushHalfSpeed = operator.createButton(opPushHalf);
        pushHalfSpeed.whenPressed(new OperatorFullDrive(true));
        pushHalfSpeed.whenReleased(new OperatorFullDrive(false));

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

        Button passIn = operator.createButton(opPassiveIn);
        passIn.whenPressed(new SetPassiveStop(PassiveSubsystem.CLOSED, 0));

        Button passOut = operator.createButton(opPassiveOut);
        passOut.whenPressed(new SetPassiveStop(PassiveSubsystem.OPEN, 0));
        
        Button passStop = operator.createButton(opPassiveStop);
        passStop.whenPressed(new SetPassiveStop(PassiveSubsystem.STOPPED));
        passStop.whenReleased(new SetPassiveStop(PassiveSubsystem.FREE));
        
        Button passFree = operator.createButton(opPassiveFree);
        passFree.whenPressed(new SetPassiveStop(PassiveSubsystem.FREE));
        
        Button feedClose = operator.createButton(opFeederClose);
        feedClose.whenPressed(new SetFeeder(FeederSubsystem.CLOSED));
        
        Button feedOpen = operator.createButton(opFeederOpen);
        feedOpen.whenPressed(new SetFeeder(FeederSubsystem.OPEN));
        
        Button feedIn = operator.createButton(opFeederIn);
        feedIn.whenPressed(new SetFeeder(FeederSubsystem.FEED_IN));
        feedIn.whenReleased(new SetFeeder(0));
        
        Button feedOut = operator.createButton(opFeederOut);
        feedOut.whenPressed(new SetFeeder(FeederSubsystem.FEED_OUT));
        feedOut.whenReleased(new SetFeeder(0));
        
    }
    
    public void initSD() {
    	SmartDashboard.putData("Move To Zone", new MoveToAutoZone(1.5));
    	SmartDashboard.putData("Goto Closest Tote", new DriveToClosestTote());
    	SmartDashboard.putData("One Crate", new FirstTote());
    	SmartDashboard.putData("Next Crate", new NextTote(false));
    	SmartDashboard.putData("Final Crate", new NextTote(true));
    	SmartDashboard.putData("Two Tote", new TwoTote(true, 2));
    	SmartDashboard.putData("Three Tote", new ThreeTote(true, 2));
    	SmartDashboard.putData("Reverse Three Tote, Start L", new ReverseThreeTote(true, true, true));
    	SmartDashboard.putData("Reverse Three Tote, Start R", new ReverseThreeTote(true, false, true));
    	
    	SmartDashboard.putData("Move Forward", new DriveTime(.75, .4, false));

    	SmartDashboard.putData("Gyro Rotate 90", new RotateToAngle(90, 1));
    	SmartDashboard.putData("Gyro Rotate 270", new RotateToAngle(270, 3));
    	SmartDashboard.putData("Gyro Rotate 180", new RotateToAngle(180, 2));
    	SmartDashboard.putData("Drive PID", new AutonDrive(6.75, 3));
    	
    	SmartDashboard.putData("Wait Tote Lift", new WaitForToteLift());
    	SmartDashboard.putData("Wink", new Wink());
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

