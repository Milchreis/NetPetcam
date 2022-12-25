package de.milchreisjunkie.netpetcam;

import java.awt.Dimension;
import java.util.concurrent.Executors;

import com.github.sarxos.webcam.WebcamStreamer;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;
import org.apache.log4j.Logger;

import com.github.sarxos.webcam.Webcam;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import de.milchreisjunkie.netpetcam.dropbox.DropboxManager;
import de.milchreisjunkie.netpetcam.dropbox.DropboxRemoteConfig;
import de.milchreisjunkie.netpetcam.dropbox.DropboxUpload;

public class NetPetcam {
	private static final Logger log = Logger.getLogger(NetPetcam.class);

	private Config config;
	private DropboxManager dbxMan;
	
	public NetPetcam(Config config) {
		this.config = config;
	}

	public void start() {
		if (config.isStream()) {
			Executors.newFixedThreadPool(1).execute(() -> startStream());
		} else {
			Executors.newFixedThreadPool(1).execute(() -> startDropboxSchedule());
		}
	}

	private void startDropboxSchedule() {
		
		this.dbxMan = new DropboxManager(config.getValue("dbxtoken"));
		try {
			new CaptureManager(config,
					System.getProperty("user.home"), 
					new DropboxUpload(dbxMan, config), 
					new DropboxRemoteConfig(dbxMan, config), 
					config.getDevice());
			
		} catch (FailedToRunRaspistillException e) {
			log.error("Error while creation capture manager", e);
		}
	}

	private void startStream() {
		Webcam.setDriver(V4l4jDriver.class);
		Webcam cam = Webcam.getDefault();
		cam.setViewSize(new Dimension(640, 480));

		new WebcamStreamer(8080, Webcam.getDefault(), 20, true);
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				log.warn("Error while sleep: " + e.getMessage());
			}
		} while (true);
	}


}
