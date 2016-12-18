package de.milchreisjunkie.netpetcam;

import java.io.File;

import org.apache.log4j.Logger;

import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import de.milchreisjunkie.netpetcam.interfaces.ICaptureDevice;
import de.milchreisjunkie.netpetcam.interfaces.ICaptureHook;

public class CaptureManager implements Runnable {
	private static final Logger log = Logger.getLogger(CaptureManager.class.getName());
	
	private int interval;
	private Thread thread;
	private ICaptureHook hook;
	private ICaptureDevice device;
	private String imagepath;
	
	public CaptureManager(String intervalInSecs, String imagePath, ICaptureHook hook, ICaptureDevice device) 
	throws FailedToRunRaspistillException {
		interval = Integer.parseInt(intervalInSecs) * 1000;
		this.hook = hook;
		this.device = device;
		this.imagepath = imagePath;
		
		try {
			if (device != null) {
				device.setup(imagePath);
			}
		} catch (Exception e) {
			log.warn("Setup failed: " + e.getCause());
		}
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			log.info("loop starts");
			
			try {
				if(device != null) {
					device.capture();
				}
			} catch (Exception e1) {
				log.error("Could not take a photo");
			}

			if(hook != null) {
				hook.perform(new File(imagepath, "out.jpg"));
			}
			
			try {
				Thread.sleep(interval);

			} catch (InterruptedException e) {
				log.warn("Could not sleep thread");
			}
			
		}
	}
}
