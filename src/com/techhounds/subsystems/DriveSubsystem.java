package com.techhounds.subsystems;

public abstract class DriveSubsystem extends BasicSubsystem {
	
	private static DriveSubsystem instance;
	private static boolean tank = true;
	
	public static DriveSubsystem getInstance() {
		if(instance == null) {
			if(tank){
				instance = new TankDrive();
			}else{
				instance = new SwerveDrive();
			}
		}
		return null;
	}
	
	public abstract void updateSmartDashboard();
}
