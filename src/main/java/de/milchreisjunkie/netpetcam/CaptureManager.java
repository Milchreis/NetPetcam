package de.milchreisjunkie.netpetcam;

import java.io.File;

import org.apache.log4j.Logger;

import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import de.milchreisjunkie.netpetcam.interfaces.ICaptureDevice;
import de.milchreisjunkie.netpetcam.interfaces.ICaptureHook;
import de.milchreisjunkie.netpetcam.interfaces.IRefreshHook;

public class CaptureManager implements Runnable {
	private static final Logger log = Logger.getLogger(CaptureManager.class.getName());
	
	private long nextrun;
	private long nextconfigrun;
	private int configRefreshInterval = 30000;
	private int interval;
	private Thread thread;
	private ICaptureHook hook;
	private ICaptureDevice device;
	private IRefreshHook refreshhook;
	private String imagepath;
	private Config configmanager;
	
	public CaptureManager(Config configmanager, String imagePath, ICaptureHook hook, IRefreshHook refreshhook, ICaptureDevice device) 
	throws FailedToRunRaspistillException {
		
		this.configmanager = configmanager;
		this.hook = hook;
		this.device = device;
		this.refreshhook = refreshhook;
		this.imagepath = imagePath;
		
		try {
			if (device != null) {
				device.setup(imagePath);
			}
		} catch (Exception e) {
			log.warn("Setup failed: " + e.getCause());
		}

		interval = Integer.parseInt(configmanager.getValue("interval")) * 1000;
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {

			if(nextrun <= System.currentTimeMillis()) {
			
				log.info("loop starts");
				
				if (device != null) {
					try {
						device.capture();
					} catch (Exception e1) {
						log.error("Could not take a photo");
					}
				}

				if (hook != null) {
					try {
						hook.perform(new File(imagepath, "out.jpg"));
					} catch (Exception e1) {
						log.error("Could not perform post process with the captured image");
					}
				}
				
				nextrun = System.currentTimeMillis() + interval;
			}

			// Refresh the configuration by file from dropbox
			if(nextconfigrun <= System.currentTimeMillis()) {
				
				if (refreshhook != null) {
					try {
						refreshhook.refresh();
						interval = Integer.parseInt(configmanager.getValue("interval")) * 1000;
					} catch (Exception e1) {
						log.error("Could not refresh");
					}
				}

				nextconfigrun = System.currentTimeMillis() + configRefreshInterval;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}
