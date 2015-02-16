package com.techhounds.subsystems;

import edu.wpi.first.wpilibj.CameraServer;

public class USBCameraSubsystem extends BasicSubsystem {

	private static USBCameraSubsystem instance;
	private CameraServer server;
	
	private USBCameraSubsystem() {
		server = CameraServer.getInstance();
		setQuality(50);
		startCapture();
	}
	
	public static USBCameraSubsystem getInstance() {
		if(instance == null)
			instance = new USBCameraSubsystem();
		return instance;
	}
	
	public void setQuality(int quality) {
		server.setQuality(50);
	}
	
	private void startCapture() {
		server.startAutomaticCapture();
	}
	
	public void startCapture(String port) {
		server.startAutomaticCapture(port);
	}

	@Override
	public void updateSmartDashboard() {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
