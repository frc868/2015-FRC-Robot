package com.techhounds.subsystems;

/**
 *
 */
public class GrabberSubsystem extends BasicSubsystem {
    
	private static GrabberSubsystem instance;
	
	public static GrabberSubsystem getInstance(){
		if (instance == null)
			instance = new GrabberSubsystem();
		return instance;
	}

	private GrabberSubsystem(){
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
		
	}
}

