package com.techhounds.subsystems;

public class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;

	private DriveSubsystem() {
	
	}
	
	public static DriveSubsystem getInstance() {
		if(instance == null)
			instance = new DriveSubsystem();
		return instance;
	}
	
	@Override
	public void updateSmartDashboard() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
