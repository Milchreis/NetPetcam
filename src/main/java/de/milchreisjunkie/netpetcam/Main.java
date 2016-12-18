package de.milchreisjunkie.netpetcam;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import de.milchreisjunkie.netpetcam.util.CLIManager;
import de.milchreisjunkie.netpetcam.util.ConfigManager;
import de.milchreisjunkie.netpetcam.util.DropboxManager;
import de.milchreisjunkie.netpetcam.util.WLANCheck;

public class Main {
	private static final Logger LOG = Logger.getLogger("Petcam");
	private static final SimpleDateFormat SDF = new SimpleDateFormat("YYYY-MM-DD_HH-mm-ss");

	public static void main(String args[]) throws Exception {
		
		// Create ConfigManager by commandline-interface
		ConfigManager configManager = CLIManager.parse(args);
	
		// Update wifi settings, if values found
		WLANCheck.setupWlan(
				configManager.getValue("ssid"), 
				configManager.getValue("wlanpw"));

		// Connect to dropbox
		DropboxManager dbxMan = new DropboxManager(configManager.getValue("dbxtoken"));
		int maxImages = Integer.parseInt(configManager.getValue("maximages"));
		
		new CaptureManager(
			configManager.getValue("interval"), 
			System.getProperty("user.home"),
			f -> {
				try {
					// Upload files
					dbxMan.uploadFile(f, generateName());
									
					// Get files and sort list
					List<String> files = dbxMan.getFileList();
					Collections.sort(files);		
				
					// Delete oldest file, if maximum is reached
					if(files.size() > maxImages) {
						dbxMan.deleteFile(files.get(0));
					}					
				} catch (Exception e) {
					LOG.error("Error while processing: " + e.getMessage());
				}
			},
			configManager.getDevice()
		);
	}

	private static String generateName() {
		return SDF.format(new Date())+".jpg";
	}
}
