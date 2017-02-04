package org.usfirst.frc.team3021.robot;


import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision {
	//Member Attributes
	Controller controller;
	UsbCamera cam0;
	UsbCamera cam1;
	int curCamNum;
	CameraServer server;
	MjpegServer sink;
	private boolean btn_down;
	VisionProcessor visionProcessor;
	
	public Vision(){
		server = CameraServer.getInstance();
		
		cam0 = new UsbCamera("Active USB Camera", 0);
		cam1 = new UsbCamera("Active USB Camera", 1);
		
		curCamNum = 0;
		
		sink = new MjpegServer("Server 1", 1180);
		
		server.addServer(sink);
		server.addCamera(cam0);
		server.addCamera(cam1);
		
		sink.setSource(cam0);
		cam0.setFPS(20);
		cam1.setFPS(20);
		
		System.out.println("Camera starting.");
		
		visionProcessor = new VisionProcessor(cam0);
		visionProcessor.setDaemon(true);
		visionProcessor.start();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	void teleopPeriodic(){
		if(controller.isSwitchingCamera() && !btn_down){
			btn_down = true;
			System.out.println("Switch cam\n");
			if (curCamNum == 1) {
				sink.setSource(cam0);
				curCamNum = 0;
				visionProcessor.setCamera(cam0);
			} 
			else if (curCamNum == 0) {
				sink.setSource(cam1);
				curCamNum = 1;
				visionProcessor.setCamera(cam1);
			}
		} 
		else if (!controller.isSwitchingCamera()) {
			btn_down = false;
		}
	}
	
	

}
