package org.usfirst.frc.team3021.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision {
	//Member Attributes
	Controller controller;
	UsbCamera cam0;
	UsbCamera cam1;
	int curCam;
	CameraServer server;
	MjpegServer sink;
	private boolean btn_down;
	
	public Vision(){
		server = CameraServer.getInstance();
		
		cam0 = new UsbCamera("Active USB Camera", 0);
		cam1 = new UsbCamera("Active USB Camera", 1);
		
		curCam = 0;
		
		sink = new MjpegServer("Server 1", 1180);
		
		server.addServer(sink);
		server.addCamera(cam0);
		server.addCamera(cam1);
		
		sink.setSource(cam0);
		
		System.out.println("Camera starting.");
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	void teleopPeriodic(){
		if(controller.isSwitchingCamera() && !btn_down){
			btn_down = true;
			System.out.println("Switch cam\n");
			if (curCam == 1) {
				sink.setSource(cam0);
				curCam = 0;
			} 
			else if (curCam == 0) {
				sink.setSource(cam1);
				curCam = 1;
			}
		} 
		else if (!controller.isSwitchingCamera()) {
			btn_down = false;
		}
	}

}
