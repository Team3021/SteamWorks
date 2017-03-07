package org.usfirst.frc.team3021.robot.vision;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class TargetElement {
	
	protected final int LINE_THICKNESS = 2;
	
	protected final int TARGET_RADIUS = 7;

	protected final double STRIPE_TOP = 50;
	
	protected final double STRIPE_WIDTH_MIN = 15;
	protected final double STRIPE_HEIGHT_MIN = 50;
	
	protected final double STRIPE_WIDTH = 17;
	protected final double STRIPE_HEIGHT = 150;
	
	protected final double STRIPE_WIDTH_MAX = 23;
	protected final double STRIPE_HEIGHT_MAX = 150;

	
	protected final double STRIPE_OFFSET = 50;
	
	protected final Size STRIPE_SIZE = new Size(STRIPE_WIDTH, STRIPE_HEIGHT);
	
	protected final double FRAME_WIDTH = 320;
	protected final double FRAME_HEIGHT = 240;
	
	protected final Size FRAME_SIZE = new Size(FRAME_WIDTH, FRAME_HEIGHT);
	
	protected double TARGET_CENTER_X = FRAME_WIDTH / 2;

	protected Point TARGET_CENTER = new Point(TARGET_CENTER_X, STRIPE_TOP + (STRIPE_HEIGHT / 2));
}
