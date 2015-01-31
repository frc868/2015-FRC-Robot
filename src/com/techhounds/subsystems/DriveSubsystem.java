package com.techhounds.subsystems;

import com.techhounds.MultiMotor;
import com.techhounds.OI;
import com.techhounds.Robot;
import com.techhounds.RobotMap;
import com.techhounds.commands.driving.DriveWithGamepad;

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
	
	private boolean overrideOperatorButton = false;
	private boolean twoPersonDrive = true;
	private boolean isForward = true;
	private boolean isHalfSpeed = false;
	
	private static final double Kp = 0;
	private static final double Ki = 0;
	private static final double Kd = 0;
	private double driveTolerance;
	
	private PIDController drivePID;
	
	private MultiMotor leftMotors;
	private MultiMotor rightMotors;
	
	private Counter leftEnc;
	private Counter rightEnc;
	
	private boolean leftEncEnabled = false;
	private boolean rightEncEnabled = false;
	
	private final double COUNTS_TO_FEET = 0;

	private DriveSubsystem() {
		super("DriveSubsystem");
		
		leftMotors = new MultiMotor(
				new SpeedController[]{
						new Victor(RobotMap.Drive.LEFT_DRIVE_MOTOR_1),
						new Victor(RobotMap.Drive.LEFT_DRIVE_MOTOR_2),
						new Victor(RobotMap.Drive.LEFT_DRIVE_MOTOR_3),},
				new boolean[]{false, false, false});
		
		rightMotors = new MultiMotor(
				new SpeedController[]{
						new Victor(RobotMap.Drive.RIGHT_DRIVE_MOTOR_1),
						new Victor(RobotMap.Drive.RIGHT_DRIVE_MOTOR_2),
						new Victor(RobotMap.Drive.RIGHT_DRIVE_MOTOR_3),},
				new boolean[]{false, false, false});
		
		if(RobotMap.Drive.LEFT_ENC != RobotMap.DOES_NOT_EXIST){
			leftEnc = new Counter(RobotMap.Drive.LEFT_ENC);
			leftEnc.setDistancePerPulse(COUNTS_TO_FEET);
			leftEncEnabled = true;
		}
		
		if(RobotMap.Drive.RIGHT_ENC != RobotMap.DOES_NOT_EXIST){
			rightEnc = new Counter(RobotMap.Drive.RIGHT_ENC);
			rightEnc.setDistancePerPulse(COUNTS_TO_FEET);
			rightEncEnabled = true;
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
	
	public void driveWithGamepad() {
		double powerMag;
    	double steerMag;
    	boolean posPower;
    	boolean posSteer;
    	
    	double onePower = OI.getDriverLeftYAxis();
    	double oneSteer = OI.getDriverRightXAxis();
    	double twoPower = OI.getOperatorLeftYAxis();
    	double twoSteer = OI.getOperatorRightXAxis();
    	
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
        	steerMag *= .3;
        }
        
        if (!posPower)
        	powerMag *= -1;
        if (!posSteer)
        	steerMag *= -1;
        
        double left = powerMag + steerMag;
        double right = powerMag - steerMag;
        
        setPower(left, -right);
	}
	
	public double getLeftDistance(){
    	return leftEncEnabled ? leftEnc.getDistance() : 0;
    }
    
    public double getRightDistance(){
    	return rightEncEnabled ? rightEnc.getDistance() : 0;
    }

	public double getAvgDistance(){
		return (getLeftDistance() + getRightDistance()) / 2;
	}
    
	public double getLeftCount(){
		return leftEncEnabled ? leftEnc.get() : 0;
	}
	
	public double getRightCount(){
		return rightEncEnabled ? rightEnc.get() : 0;
	}
	
	public double getAvgCount(){
		return (getLeftCount() + getRightCount()) / 2;
	}
	
	//feet per second
	public double getLeftSpeed(){
		return leftEncEnabled ? leftEnc.getRate() : 0;
	}
	
	public double getRightSpeed(){
		return rightEncEnabled ? rightEnc.getRate() : 0;
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
    	
        leftMotors.set(leftPower);
        rightMotors.set(rightPower);
    }
    
    public double getRightPower(){
        return -rightMotors.get();
    }
    
    public double getLeftPower(){
        return leftMotors.get();
    }
    
    public double getAvgPower(){
        return (getLeftPower() + getRightPower()) / 2;
    }
    
    public void setDrivePID(double dist) {
    	leftEnc.reset();
    	rightEnc.reset();
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
        SmartDashboard.putData("drivePID", drivePID);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
}