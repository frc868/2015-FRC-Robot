package org.usfirst.frc.team868.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team868.robot.commands.RecordGyro;
import org.usfirst.frc.team868.robot.commands.ResetGyro;
import org.usfirst.frc.team868.robot.commands.ShowGyro;
import org.usfirst.frc.team868.robot.commands.ShowRotationTracker;
import org.usfirst.frc.team868.robot.commands.StartGyro;
import org.usfirst.frc.team868.robot.commands.StopGyro;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private static OI c_instance;
	//private Joystick m_driver;

	private OI() {
		//m_driver = new Joystick(0);
	}

	/**
	 * 
	 */
	public static OI getInstance() {
		if (c_instance == null) {
			c_instance = new OI();
			c_instance.setupDashboard();
		}
		return c_instance;
	}

	/**
	 * 
	 */
	private void setupDashboard() {
		SmartDashboard.putData("Reset Gyro", new ResetGyro());
		SmartDashboard.putData("Show Gyro Angles", new ShowGyro());
		SmartDashboard.putData("Start Gyro", new StartGyro());
		SmartDashboard.putData("Stop Gyro", new StopGyro());
		SmartDashboard.putData("Capture Raw Gyro Data", new RecordGyro(10000));
		
		SmartDashboard.putData("RotationTracker1", new ShowRotationTracker("RotationTracker1"));
		SmartDashboard.putData("RotationTracker2", new ShowRotationTracker("RotationTracker2"));

		SmartDashboard.putData("DirectionTracker1", new ShowRotationTracker("DirectionTracker1"));
		SmartDashboard.putData("DirectionTracker2", new ShowRotationTracker("DirectionTracker2"));
	}
}

