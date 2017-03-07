package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class TargetUtil {
	
	public static final int LINE_THICKNESS = 2;
	
	public static final int TARGET_RADIUS = 7;

	public static final double STRIPE_TOP = 50;
	
	public static final double STRIPE_WIDTH_MIN = 15;
	public static final double STRIPE_HEIGHT_MIN = 50;
	
	public static final double STRIPE_WIDTH = 17;
	public static final double STRIPE_HEIGHT = 150;
	
	public static final double STRIPE_WIDTH_MAX = 23;
	public static final double STRIPE_HEIGHT_MAX = 150;

	
	public static final double STRIPE_OFFSET = 50;
	
	public static final Size STRIPE_SIZE = new Size(STRIPE_WIDTH, STRIPE_HEIGHT);
	
	public static final double FRAME_WIDTH = 320;
	public static final double FRAME_HEIGHT = 240;
	
	public static final Size FRAME_SIZE = new Size(FRAME_WIDTH, FRAME_HEIGHT);
	
	public static double TARGET_CENTER_X = FRAME_WIDTH / 2;

	public static Point TARGET_CENTER = new Point(TARGET_CENTER_X, STRIPE_TOP + (STRIPE_HEIGHT / 2));

	public static Rect getLeftTargetStripe() {
		Point leftTop = new Point(TARGET_CENTER_X - STRIPE_OFFSET - STRIPE_WIDTH, STRIPE_TOP);
		Point bottomRight = new Point(TARGET_CENTER_X - STRIPE_OFFSET, STRIPE_TOP + STRIPE_HEIGHT);
		
		Rect rect = new Rect(leftTop, bottomRight);
		
		return rect;
	}

	public static Rect getRightTargetStripe() {
		Point leftTop = new Point(TARGET_CENTER_X + STRIPE_OFFSET, STRIPE_TOP);
		Point bottomRight = new Point(TARGET_CENTER_X + STRIPE_OFFSET + STRIPE_WIDTH, STRIPE_TOP + STRIPE_HEIGHT);
		
		Rect rect = new Rect(leftTop, bottomRight);
		
		return rect;
	}
	
	public static Point getCenterPoint(Rect leftRect, Rect rightRect) {

		Point leftBoxCenterPoint = new Point(leftRect.x + (leftRect.width / 2), leftRect.y + (leftRect.height / 2));

		Point rightBoxCenterPoint = new Point(rightRect.x + (rightRect.width / 2), rightRect.y + (rightRect.height / 2));

		double centerX = leftBoxCenterPoint.x + ((rightBoxCenterPoint.x - leftBoxCenterPoint.x) / 2);
		double centerY = (leftBoxCenterPoint.y + rightBoxCenterPoint.y) / 2;

		return new Point(centerX, centerY);
	}
}
