package org.usfirst.frc.team3021.robot.subsystem;

import org.usfirst.frc.team3021.robot.Subsystem;
import org.usfirst.frc.team3021.robot.commands.VisionCommand;
import org.usfirst.frc.team3021.robot.vision.VisionProcessor;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;

public class Vision extends Subsystem {
	
	private final String PREF_VISION_CAMERA_0_ENABLED = "vision.camera.0.enabled";
	private final String PREF_VISION_CAMERA_1_ENABLED = "vision.camera.1.enabled";
	
	private static final int USB_CAMERA_UNKNOWN = -1;
	private static final int USB_CAMERA_ZERO = 0;
	private static final int USB_CAMERA_ONE = 1;

	// Member Attributes
	private int curCamNum;
	
	private boolean btn_down;

	// main camera server to share registered video inputs and outputs with the dashboard
	private CameraServer server;
	
	// usb cameras are video sources
	private UsbCamera cam0;  
	private UsbCamera cam1;
	
	// mjpeg server is a video sink that takes images and sends them to the dashboard
	private MjpegServer dashboardSink; 
	
	// our robot vision processing
	private VisionProcessor visionProcessor;
	
	private boolean isCamera0Enabled() {
		return Preferences.getInstance().getBoolean(PREF_VISION_CAMERA_0_ENABLED, false);
	}
	
	private boolean isCamera1Enabled() {
		return Preferences.getInstance().getBoolean(PREF_VISION_CAMERA_1_ENABLED, false);
	}
	
	private boolean isVisionEnabled() {
		return isCamera0Enabled() || isCamera1Enabled();
	}
	
	public Vision() {
		if (!isVisionEnabled()) {
			// Don't initialize the camera objects and return right away
			System.out.println("WARNING !!! NO CAMERAS ENABLED");
			return;  
		}
		else {
			System.out.println("One or more cameras are enabled");
		}
		
		server = CameraServer.getInstance();
		
		// setup the mjpeg server to communicate with the smart dashboard
		dashboardSink = new MjpegServer("Server 1", 1180);
		server.addServer(dashboardSink);
		
		// set up a usb camera on port 0
		cam0 = new UsbCamera("Active USB Camera", 0);
		
		if (cam0.isConnected()) {
			cam0.setFPS(20);
		}

		// set up a usb camera on port 1
		cam1 = new UsbCamera("Active USB Camera", 1);
		
		if (cam1.isConnected()) {
			cam1.setFPS(20);
		}
		
		VideoSource currentCam = null;
		curCamNum = USB_CAMERA_UNKNOWN;

		if (curCamNum == USB_CAMERA_UNKNOWN && isCamera0Enabled()) {
			curCamNum = USB_CAMERA_ZERO;
			currentCam = cam0;
		} else if (curCamNum == USB_CAMERA_UNKNOWN && isCamera1Enabled()) {
			curCamNum = USB_CAMERA_ONE;
			currentCam = cam1;
		}
		
		if (curCamNum != USB_CAMERA_UNKNOWN) {
			System.out.println("Camera starting.");
			
			// setup the vision processor device and connect a camera as the input source
			visionProcessor = new VisionProcessor(currentCam);
			
			// get the output from the vision processor device
			VideoSource visionProcessorOutput = visionProcessor.getOutput();
			
			// wire the output of the vision processor device to the dashboard
			dashboardSink.setSource(visionProcessorOutput);

			visionProcessor.play();
		} else {
			System.out.println("WARNING !!! NO CAMERAS FOUND");
		}
	}

	public void teleopPeriodic() {		
		if (isVisionEnabled()) {
			toggleCamera();
		}
	}

	private void toggleCamera() {
		if (mainController.isSwitchingCamera() && !btn_down) {
			btn_down = true;
			
			VideoSource currentCam = null;
			
			if (curCamNum == 1 && isCamera0Enabled()) {
				System.out.println("Switch cam");
				curCamNum = 0;
				currentCam = cam0;
			} 
			else if (curCamNum == 0 && isCamera1Enabled()) {
				System.out.println("Switch cam");
				curCamNum = 1;
				currentCam = cam1;
			}
			else if (curCamNum == 0 && isCamera0Enabled()) {
				curCamNum = 0;
				currentCam = cam0;
			} 
			else if (curCamNum == 1 && isCamera1Enabled()) {
				curCamNum = 1;
				currentCam = cam1;
			}
			
			if (currentCam != null) {
				visionProcessor.setInput(currentCam);
			}
		} 
		else if (!mainController.isSwitchingCamera()) {
			btn_down = false;
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new VisionCommand());
	}
}
