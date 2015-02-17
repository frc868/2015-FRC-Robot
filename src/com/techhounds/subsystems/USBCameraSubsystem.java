package com.techhounds.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;

public class USBCameraSubsystem extends BasicSubsystem {

	private static USBCameraSubsystem instance;
	private CameraServer server;
	
	private int session;
	private Image frame;
	
	private USBCameraSubsystem() {
//		server = CameraServer.getInstance();
//		setQuality(50);
//		startCapture();
//		
//		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//
//        session = NIVision.IMAQdxOpenCamera("cam0",
//                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//        NIVision.IMAQdxConfigureGrab(session);
//        
//		NIVision.IMAQdxStartAcquisition(session);
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
	
	public void updateCamera() {
		
		NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);

	}

	@Override
	public void updateSmartDashboard() {
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
