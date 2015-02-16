package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.CameraServer;

public class USBCameraSubsystem extends BasicSubsystem {

	private static USBCameraSubsystem instance;
	private CameraServer server;
	
	private USBCameraSubsystem() {
		server = CameraServer.getInstance();
	}
	
	public static USBCameraSubsystem getInstance() {
		if(instance == null)
			instance = new USBCameraSubsystem();
		return instance;
	}
	
	public void setCameraQuality(int quality) {
		server.setQuality(quality);
	}
	
	public void startUSBCapture(String port) {
		server.startAutomaticCapture(port);
	}

	@Override
	public void updateSmartDashboard() {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
