package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class TargetScope {
	
	private static final Scalar color = new Scalar(255, 255, 255);
	
	private static final int lineThickness = 2;
	
	private static final double width = 30;
	private static final double height = 80;
	
	private static final int radius = 10;
			
	private static final Size rectSize = new Size(width, height);

	private Point leftBoxTLPoint = new Point(25, 25);
	private Point rightBoxTLPoint = new Point(75, 25);
	
	private Rect leftRect;
	private Rect rightRect;
	
	private Point targetCenter;

	public TargetScope() {
		// Create left rectangle on screen
		leftRect = new Rect(leftBoxTLPoint, rectSize);
		
		Point leftBoxCenterPoint = new Point(leftBoxTLPoint.x + (width / 2), leftBoxTLPoint.y + (height / 2));
		
		// Create right rectangle on screen
		rightRect = new Rect(rightBoxTLPoint, rectSize);
		
		Point rightBoxCenterPoint = new Point(rightBoxTLPoint.x + (width / 2), rightBoxTLPoint.y + (height / 2));
		
		double targetCenterX = leftBoxCenterPoint.x + ((rightBoxCenterPoint.x - leftBoxCenterPoint.x) / 2);
		double targetCenterY = (leftBoxCenterPoint.y + rightBoxCenterPoint.y) / 2;
		
		targetCenter = new Point(targetCenterX, targetCenterY);
	}
	
	public Point getTargetCenter() {
		return targetCenter;
	}
	
	public void draw(Mat mat) {
		
		Imgproc.rectangle(mat, leftRect.tl(), leftRect.br(), color, lineThickness);
		
		Imgproc.rectangle(mat, rightRect.tl(), rightRect.br(), color, lineThickness);
		
		Imgproc.circle(mat, targetCenter, radius, color, lineThickness);
	}
}
