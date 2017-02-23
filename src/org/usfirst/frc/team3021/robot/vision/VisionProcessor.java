package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Mat;
import org.usfirst.frc.team3021.robot.device.RunnableDevice;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;

public class VisionProcessor extends RunnableDevice {
	
	private final String PREF_TARGET_SCOPE_ENABLED = "VisionProcessor.target.scope.enabled";
	private final String PREF_TARGET_LOCATOR_ENABLED = "VisionProcessor.target.locator.enabled";
	
	private TargetScope targetScope;
	private TargetLocation targetLocation;

	private CvSink input; // Video sink that will receive images from the camera source

	private CvSource output;
	
	private Mat mat;

	public VisionProcessor(VideoSource initialCam) {
		// Setup a CvSink. This will capture Mats from an external source
		input = new CvSink("Camera Sink");
		input.setSource(initialCam);
		
		// Setup a CvSource. This will send images back to an external sink
		output = CameraServer.getInstance().putVideo("Stanely : Vision", 320, 240);

		// Mats are very memory expensive.
		mat = new Mat();
	}

	public void setInput(VideoSource source) {
		input.setSource(source);	
	}
	
	public VideoSource getOutput() {
		return output;
	}
	
	private boolean isTargetScopeEnabled() {
		return Preferences.getInstance().getBoolean(PREF_TARGET_SCOPE_ENABLED, false);
	}
	
	private boolean isTargetLocatorEnabled() {
		return Preferences.getInstance().getBoolean(PREF_TARGET_LOCATOR_ENABLED, false);
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

		// Draw a target location on the image
		if (isTargetLocatorEnabled()) {
			targetLocation.draw(mat);
		}

		// Draw a target scope on the image
		if (isTargetScopeEnabled()) {
			targetScope.draw(mat);
		}

		// Give the frame to the output
		output.putFrame(mat);

		delay(50);
	}
}
