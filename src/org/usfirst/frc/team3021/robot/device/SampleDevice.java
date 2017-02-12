package org.usfirst.frc.team3021.robot.device;

public class SampleDevice extends RunnableDevice {
	
	public SampleDevice() {
		super();
	}

	@Override
	protected void runPeriodic() {
		System.out.println("I'm doing stuff");
		
		delay(50);
	}
	
	// This is a test to demonstrate how to use the threaded device
	public static void main(String[] args) {
		
		// create the device
		System.out.println("Creating the device");
		SampleDevice device = new SampleDevice();
		
		RunnableDevice.delay(2000);
		
		System.out.println("Starting the device");
		device.play();
		
		RunnableDevice.delay(2000);

		// you can pause the device loop
		System.out.println("Pausing the device");
		device.pause();

		RunnableDevice.delay(2000);

		// you can resume the device loop
		System.out.println("Resuming the device");
		device.play();

		RunnableDevice.delay(1000);

		System.out.println("Stopping the device");
		device.stop();
	}
}
