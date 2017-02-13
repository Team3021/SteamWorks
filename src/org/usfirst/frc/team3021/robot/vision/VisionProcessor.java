package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Mat;
import org.usfirst.frc.team3021.robot.device.RunnableDevice;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionProcessor extends RunnableDevice {
	
	private boolean drawSiteScope = false;

	private CvSink input; // This is the the sink that will receive images from the camera source

	private CvSource output;
	
	private Mat mat;

	public VisionProcessor(VideoSource initialCam) {
		// Setup a CvSink. This will capture Mats from an external source
		input = new CvSink("Camera Sink");
		input.setSource(initialCam);
		
		// Setup a CvSource. This will send images back to an external sink
		output = CameraServer.getInstance().putVideo("Stanely : Vision", 640, 480);

		// Mats are very memory expensive.
		mat = new Mat();
	}

	public void setInput(VideoSource source) {
		input.setSource(source);	
	}
	
	public VideoSource getOutput() {
		return output;
	}
	
	public void setDrawSiteScope(boolean drawSiteScope) {
		this.drawSiteScope = drawSiteScope;
	}

	@Override
	protected void runPeriodic() {

		// Grab a frame from the source camera
		// If there is an error notify the output
		if (input.grabFrame(mat) == 0) {
			// Send the output the error.
			output.notifyError(input.getError());

			// skip the rest of the current iteration
			System.out.println("Unable to grab frame.");
			delay(50);
			return;
		}

		// Draw a target scope on the image
		if (drawSiteScope) {
			TargetScope.draw(mat);
		}

		// Give the frame to the output
		output.putFrame(mat);

		delay(50);
	}
}
