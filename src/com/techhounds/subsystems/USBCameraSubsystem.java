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
		
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        startSession();
	}
	
	public static USBCameraSubsystem getInstance() {
		if(instance == null)
			instance = new USBCameraSubsystem();
		return instance;
	}
	
	private void startSession() {
		NIVision.IMAQdxStartAcquisition(session);
	}
	
	public void updateCamera() {
		
		NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);
	}
	
	private void stopSession() {
		NIVision.IMAQdxStopAcquisition(session);
	}

	@Override
	public void updateSmartDashboard() {
		updateCamera();
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
