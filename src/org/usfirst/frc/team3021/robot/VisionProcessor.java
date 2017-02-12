package org.usfirst.frc.team3021.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionProcessor extends Thread {
	
	private CvSink cvSink;
	private Mat mat;

	public VisionProcessor(UsbCamera initialCam) {
		cvSink = new CvSink("Processor Sink");
		cvSink.setSource(initialCam);
		// Mats are very memory expensive. Let's reuse this Mat.
		mat = new Mat();
	}
	
	@Override
	public void run(){
		// Get a CvSink. This will capture Mats from the camera
		
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = CameraServer.getInstance().putVideo("Targeting (WIP)", 640, 480);

		

		/* This cannot be 'true'. The program will never exit if it is. This
		 * lets the robot stop this thread when restarting robot code or
		 * deploying.
		 */
		while (!Thread.interrupted()) {
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat.  If there is an error notify the output.
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				System.out.println("Unable to grab frame.");
				continue;
			}
			// Put a rectangle on the image
			Imgproc.rectangle(mat, new Point(25, 25), new Point(50, 100),
					new Scalar(255, 255, 255), 2);
			Imgproc.rectangle(mat, new Point(75, 25), new Point(100, 100),
					new Scalar(255, 255, 255), 2);
			// Give the output stream a new image to display
			outputStream.putFrame(mat);
		}
	
	}

	public void setCamera(UsbCamera cam) {
		cvSink.setSource(cam);	
	}
}
