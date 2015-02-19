package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.CameraServer;

public class USBCameraSubsystem extends BasicSubsystem {

	private static USBCameraSubsystem instance;
	private CameraServer server;
	
	private USBCameraSubsystem() {
		server = CameraServer.getInstance();
        startCapture();
	}
	
	public static USBCameraSubsystem getInstance() {
		if(instance == null)
			instance = new USBCameraSubsystem();
		return instance;
	}
	
	public void setQuality(int quality) {
		server.setQuality(quality);
	}
	
	public void startCapture() {
		if(!server.isAutoCaptureStarted())
			server.startAutomaticCapture("cam0");
	}
	
	@Override
	public void updateSmartDashboard() {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
