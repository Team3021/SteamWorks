package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class TargetScope {
	
	private static final Scalar color = new Scalar(255, 255, 255);

	public Point getCenterPoint() {
		return TargetUtil.TARGET_CENTER;
	}
	
	public void draw(Mat mat) {
		
		Rect leftRect = TargetUtil.getLeftTargetStripe();
		
		Imgproc.rectangle(mat, leftRect.tl(), leftRect.br(), color, TargetUtil.LINE_THICKNESS);
		
		Rect rightRect = TargetUtil.getRightTargetStripe();
		
		Imgproc.rectangle(mat, rightRect.tl(), rightRect.br(), color, TargetUtil.LINE_THICKNESS);
		
		Imgproc.circle(mat, TargetUtil.TARGET_CENTER, TargetUtil.TARGET_RADIUS, color, TargetUtil.LINE_THICKNESS);
	}
}
