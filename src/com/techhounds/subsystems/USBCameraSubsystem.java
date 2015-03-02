package com.techhounds.subsystems;

import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.CameraServer;

public class USBCameraSubsystem extends BasicSubsystem {

	private static USBCameraSubsystem instance;
	private CameraServer server;
	
	private boolean enabled;
	
	private USBCameraSubsystem() {
		if (enabled = RobotMap.USB_CAMERA_ENABLED != RobotMap.DOES_NOT_EXIST){
			server = CameraServer.getInstance();
	        startCapture();
		}
	}
	
	public static USBCameraSubsystem getInstance() {
		if(instance == null)
			instance = new USBCameraSubsystem();
		return instance;
	}
	
	public void setQuality(int quality) {
		if (enabled)
			server.setQuality(quality);
	}
	
	public void startCapture() {
		if(!server.isAutoCaptureStarted() && enabled)
			server.startAutomaticCapture("cam0");
	}
	
	@Override
	public void updateSmartDashboard() {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
