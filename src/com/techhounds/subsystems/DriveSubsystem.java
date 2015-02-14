package com.techhounds.subsystems;

import com.techhounds.MultiCANTalon;
import com.techhounds.MultiMotor;
import com.techhounds.OI;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.driving.DriveWithGamepad;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Alex Fleig, Matt Simons, Ayon Mitra, Clayton Detke
 */
public class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;
	
	private static final double Kp = 0, Ki = 0, Kd = 0;
	
	private boolean overrideOperatorButton, twoPersonDrive = true, isForward, isHalfSpeed;
	private boolean leftEncEnabled, rightEncEnabled;
	
	private PIDController drivePID;
	
	private MultiCANTalon leftMotors, rightMotors;
	private MultiMotor leftMotorsPract, rightMotorsPract;
	private Counter leftEnc, rightEnc;
	
	private final double COUNTS_TO_FEET = 1;
	private final double COUNTS_TO_FEET_PRACT = 1;
	
	private double driveTolerance;

	private DriveSubsystem() {
		super("DriveSubsystem");
		
		if (Robot.isFinal()){
			leftMotors = new MultiCANTalon(
					new CANTalon[]{
							new CANTalon(RobotMap.Drive.RIGHT_MOTOR_1),
							new CANTalon(RobotMap.Drive.RIGHT_MOTOR_2)},
					new boolean[]{false, false},
					FeedbackDevice.QuadEncoder,
					false, false, false, false, false);
			
			rightMotors = new MultiCANTalon(
					new CANTalon[]{
							new CANTalon(RobotMap.Drive.LEFT_MOTOR_1),
							new CANTalon(RobotMap.Drive.LEFT_MOTOR_2)},
					new boolean[]{true, true},
					FeedbackDevice.QuadEncoder,
					false, false, false, false, false);
			leftMotors.setCountsPerFeet(1 / COUNTS_TO_FEET);
			rightMotors.setCountsPerFeet(1 / COUNTS_TO_FEET);
			leftMotors.resetEnc();
			rightMotors.resetEnc();
			
			leftEncEnabled = true;
			rightEncEnabled = true;
		}else{
			leftMotorsPract = new MultiMotor(
					new SpeedController[]{
							new Victor(RobotMap.Drive.LEFT_MOTOR_1_PRACT),
							new Victor(RobotMap.Drive.LEFT_MOTOR_2_PRACT),
							new Victor(RobotMap.Drive.LEFT_MOTOR_3_PRACT),
							},
					new boolean[]{false, false, false});
			
			rightMotorsPract = new MultiMotor(
					new SpeedController[]{
							new Victor(RobotMap.Drive.RIGHT_MOTOR_1_PRACT),
							new Victor(RobotMap.Drive.RIGHT_MOTOR_2_PRACT),
							new Victor(RobotMap.Drive.RIGHT_MOTOR_3_PRACT),
							},
					new boolean[]{true, true, true});
			
			if(leftEncEnabled = RobotMap.Drive.LEFT_ENC_PRACT != RobotMap.DOES_NOT_EXIST){
				leftEnc = new Counter(RobotMap.Drive.LEFT_ENC_PRACT);
				leftEnc.setDistancePerPulse(COUNTS_TO_FEET_PRACT);
			}
			
			if(rightEncEnabled = RobotMap.Drive.RIGHT_ENC_PRACT != RobotMap.DOES_NOT_EXIST){
				rightEnc = new Counter(RobotMap.Drive.RIGHT_ENC_PRACT);
				rightEnc.setDistancePerPulse(COUNTS_TO_FEET_PRACT);
			}
		}
		
		drivePID = new PIDController(
				Kp, Ki, Kd, 
				new PIDSource() {
					public double pidGet() {
						return getAvgDistance();
					}
				}, 
				new PIDOutput() {
					public void pidWrite(double output) {
						double drift = 0.05 * (getLeftDistance() - getRightDistance());
						setPower(output - drift, output + drift);
					}
				});
		drivePID.setOutputRange(-1, 1);
		drivePID.setAbsoluteTolerance(1);
	}
	
	public static DriveSubsystem getInstance() {
		if(instance == null)
			instance = new DriveSubsystem();
		return instance;
	}
	
	//If you have questions about this, ask Evan <3 <3 <3 XOXO
	public void driveWithGamepad() {
		double powerMag, steerMag;
    	boolean posPower, posSteer;
    	
    	double onePower = OI.getDriverLeftYAxis(), oneSteer = OI.getDriverRightXAxis(), 
    			twoPower = OI.getOperatorLeftYAxis(), twoSteer = OI.getOperatorRightXAxis();
    	
    	if (!getTwoPersonDrive()){
	        powerMag = onePower;
	        steerMag = oneSteer;
	        posPower = powerMag >= 0;
	        posSteer = steerMag >= 0;
    	}else{
    		powerMag = Math.max(Math.abs(onePower), Math.abs(twoPower));
    		steerMag = Math.max(Math.abs(oneSteer), Math.abs(twoSteer));
    		if ((onePower > 0 && twoPower < 0) || (onePower < 0 && twoPower > 0))
    			powerMag = 0;
    		if ((oneSteer > 0 && twoSteer < 0) || (oneSteer < 0 && twoSteer > 0))
    			steerMag= 0;
    		posPower = onePower >= 0 && twoPower >= 0;
    		posSteer = oneSteer >= 0 && twoSteer >= 0;
    	}
        
        powerMag = Math.abs(powerMag);
        steerMag = Math.abs(steerMag);
        
        powerMag *= powerMag * powerMag;
        steerMag *= steerMag * steerMag;
        
        if (!isForward)
        	powerMag *= -1;
        
        if (isHalfSpeed){
        	powerMag *= .5;
        	steerMag *= .6;
        }
        
        if (!posPower)
        	powerMag *= -1;
        if (!posSteer)
        	steerMag *= -1;
        
        double left = powerMag + steerMag;
        double right = powerMag - steerMag;
        
        setPower(left, right);
	}
	
	public double getLeftDistance(){
		return leftEncEnabled ? (Robot.isFinal() ? leftMotors.getDistance() : leftEnc.getDistance()) : 0;
    }
    
    public double getRightDistance(){
    	return rightEncEnabled ? (Robot.isFinal() ? rightMotors.getDistance() : rightEnc.getDistance()) : 0;
    }

	public double getAvgDistance(){
		return (getLeftDistance() + getRightDistance()) / 2;
	}
    
	public double getLeftCount(){
		return leftEncEnabled ? (Robot.isFinal() ? leftMotors.getCount() : leftEnc.get()) : 0;
	}
	
	public double getRightCount(){
		return rightEncEnabled ? (Robot.isFinal() ? rightMotors.getCount() : rightEnc.get()) : 0;
	}
	
	public double getAvgCount(){
		return (getLeftCount() + getRightCount()) / 2;
	}
	
	//feet per second
	public double getLeftSpeed(){
		return leftEncEnabled ? (Robot.isFinal() ? leftMotors.getSpeed() : leftEnc.getRate()) : 0;
	}
	
	public double getRightSpeed(){
		return rightEncEnabled ? (Robot.isFinal() ? rightMotors.getSpeed() : rightEnc.getRate()) : 0;
	}

	public double getAvgSpeed(){
		return (getLeftSpeed() + getRightCount()) / 2;
	}
	
    public void setDriveMode(boolean forward){
        isForward = forward;
        updateLEDCommand();
    }
    
    public void setHalfSpeed(boolean isHalfSpeed){
    	this.isHalfSpeed = isHalfSpeed;
    	updateLEDCommand();
    }
    
    public void toggleDriveMode(){
        setDriveMode(!isForward);
    }
    
    public void setTwoPersonDrive(boolean isTwoPeople){
    	twoPersonDrive = isTwoPeople;
    }
    
    public void setOverrideOperator(boolean override){
    	overrideOperatorButton = override;
    }
    
    public boolean getOverrideOperator(){
    	return overrideOperatorButton;
    }
    
    public void updateLEDCommand(){
    	if (isForward){
	        if (isHalfSpeed){
	        	LEDSubsystem.getInstance().forwardHalf();
	        }else{
	        	LEDSubsystem.getInstance().forwardFull();
	        }
        }else{
        	if (isHalfSpeed){
	        	LEDSubsystem.getInstance().backwardHalf();
	        }else{
	        	LEDSubsystem.getInstance().backwardFull();
	        }
        }
    }
    
    public boolean getTwoPersonDrive(){
    	return twoPersonDrive;
    }
    
    public boolean getDriveForward(){
    	return isForward;
    }
    
    public boolean getHalfSpeed(){
    	return isHalfSpeed;
    }
    
    public void stopMotors(){
        setPower(0, 0);
    }
    
    public void setPower(double power){
        setPower(power, power);
    }
    
    public void setPower(double leftPower, double rightPower){
    	
    	leftPower = Math.max(Math.min(leftPower, 1), -1);
    	rightPower = Math.max(Math.min(rightPower, 1), -1);
    	
    	if(Robot.isFinal()){
    		leftMotors.set(leftPower);
    		rightMotors.set(rightPower);
    	}else{
	        leftMotorsPract.set(leftPower);
	        rightMotorsPract.set(rightPower);
    	}
    }
    
    public double getRightPower(){
        return Robot.isFinal() ? rightMotors.get() : rightMotorsPract.get();
    }
    
    public double getLeftPower(){
        return Robot.isFinal() ? leftMotors.get() : leftMotorsPract.get();
    }
    
    public double getAvgPower(){
        return (getLeftPower() + getRightPower()) / 2;
    }
    
    public void setDrivePID(double dist) {
    	if (Robot.isFinal()){
    		leftMotors.resetEnc();
    		rightMotors.resetEnc();
    	}else{
	    	leftEnc.reset();
	    	rightEnc.reset();
    	}
    	drivePID.setSetpoint(dist);
    	drivePID.enable();
    }
    
    public void stopDrivePID() {
    	drivePID.disable();
    }
    
    public double getDriveSetPoint() {
    	return drivePID.getSetpoint();
    }
    
    public void setDriveTolerance(double feet) {
    	drivePID.setAbsoluteTolerance(feet);
    	driveTolerance = feet;
    }
    
    public double getDriveTolerance() {
    	return driveTolerance;
    }
    
    public boolean drivePIDOnTarget() {
    	return drivePID.onTarget();
    }
    
    public void updateSmartDashboard(){
//        SmartDashboard.putData("drivePID", drivePID);
    	SmartDashboard.putNumber("Left Drive Power", getLeftPower());
    	SmartDashboard.putNumber("Right Drive Power", getRightPower());
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
}