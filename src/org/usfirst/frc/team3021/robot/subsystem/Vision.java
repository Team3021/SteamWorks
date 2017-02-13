package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.SubSystem;
import org.usfirst.frc.team3021.robot.vision.VisionProcessor;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision extends SubSystem {
	
	private static final int USB_CAMERA_UNKOWN = -1;
	private static final int USB_CAMERA_ZERO = 0;
	private static final int USB_CAMERA_ONE = 1;

	// Member Attributes
	private UsbCamera cam0;
	private UsbCamera cam1;
	
	private int curCamNum;
	
	private CameraServer server;
	
	private MjpegServer sink;
	
	private boolean btn_down;
	
	private VisionProcessor visionProcessor;
	
	public Vision() {
		server = CameraServer.getInstance();
		
		// setup the jpeg server to communicate with the smart dashboard
		sink = new MjpegServer("Server 1", 1180);
		server.addServer(sink);
		
		// set up a usb camera on port 0
		cam0 = new UsbCamera("Active USB Camera", 0);
		
		if (cam0.isConnected()) {
			cam0.setFPS(20);
			server.addCamera(cam0);
		}

		// set up a usb camera on port 0
		cam1 = new UsbCamera("Active USB Camera", 1);
		
		if (cam1.isConnected()) {
			cam1.setFPS(20);
			server.addCamera(cam1);
		}
		
		VideoSource currentCam = null;
		curCamNum = USB_CAMERA_UNKOWN;

		if (curCamNum == USB_CAMERA_UNKOWN && cam0.isConnected()) {
			curCamNum = USB_CAMERA_ZERO;
			currentCam = cam0;
		} else if (curCamNum == USB_CAMERA_UNKOWN && cam1.isConnected()) {
			curCamNum = USB_CAMERA_ONE;
			currentCam = cam1;
		}
		
		if (curCamNum != USB_CAMERA_UNKOWN) {
			System.out.println("Camera starting.");
			sink.setSource(currentCam);
			
			visionProcessor = new VisionProcessor(currentCam);
			visionProcessor.setDaemon(true);
			visionProcessor.start();
		}
	}

	public void teleopPeriodic() {		
		if (controller.isSwitchingCamera() && !btn_down) {
			btn_down = true;
			
			VideoSource currentCam = null;
			
			if (curCamNum == 1 && cam0.isConnected()) {
				System.out.println("Switch cam");
				curCamNum = 0;
				currentCam = cam0;
			} 
			else if (curCamNum == 0 && cam1.isConnected()) {
				System.out.println("Switch cam");
				curCamNum = 1;
				currentCam = cam1;
			}
			else if (curCamNum == 0 && cam0.isConnected()) {
				curCamNum = 0;
				currentCam = cam0;
			} 
			else if (curCamNum == 1 && cam1.isConnected()) {
				curCamNum = 1;
				currentCam = cam1;
			}
			
			if (currentCam != null) {
				sink.setSource(currentCam);
				visionProcessor.setVideoSource(currentCam);
			}
		} 
		else if (!controller.isSwitchingCamera()) {
			btn_down = false;
		}
	}
}
