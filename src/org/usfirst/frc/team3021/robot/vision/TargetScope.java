package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class TargetScope {
	
	public static void draw(Mat mat) {
		Imgproc.rectangle(mat, new Point(25, 25), new Point(50, 100),
				new Scalar(255, 255, 255), 2);
		Imgproc.rectangle(mat, new Point(75, 25), new Point(100, 100),
				new Scalar(255, 255, 255), 2);
	}
}
