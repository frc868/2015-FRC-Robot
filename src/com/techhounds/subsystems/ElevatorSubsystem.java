
package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
	
	private static ElevatorSubsystem instance;
	private double power;
	public static final boolean ON = true;
	public static final boolean OFF = false;
    
	public static ElevatorSubsystem getInstance() {
		if (instance == null) 
			instance = new ElevatorSubsystem();
		
		return instance;
	}

	private Relay motor;
	private DigitalInput topSwitch, bottomSwitch;
	private AnalogInput potential;
	private DigitalOutput led;
	private Servo pwm;
	
	private ElevatorSubsystem(){
		
		motor = new Relay(0);
		motor.setDirection(Relay.Direction.kBoth);
		topSwitch = new DigitalInput(6);
		bottomSwitch = new DigitalInput(4);
		potential = new AnalogInput(0);
		led = new DigitalOutput(8);
		pwm = new Servo(9);
	}
	
	public boolean getTopSwitch(){
		return topSwitch.get();
	}
	
	public boolean getBottomSwitch(){
		return bottomSwitch.get();
	}
	
	public int getRotation(){
		return potential.getValue();
	}
	
	public void startRaisingElevator(){
		motor.set(Relay.Value.kForward); 
	}
	
	public void startLoweringElevator(){
		motor.set(Relay.Value.kReverse); 
	}
	
	public void stopElevator(){
		motor.set(Relay.Value.kOff); 
	}
	
	public void setLed(boolean value) {
		led.set(value);
	}
	
	public void setPWM(double power){
		pwm.set(power);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

