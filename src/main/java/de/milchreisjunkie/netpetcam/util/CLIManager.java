package de.milchreisjunkie.netpetcam.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import de.milchreisjunkie.netpetcam.RpiCam;
import de.milchreisjunkie.netpetcam.WebcamDevice;

public class CLIManager {
	private static final Logger log = Logger.getLogger(CLIManager.class.getName());
	
	public static ConfigManager parse(String[] args) throws ParseException, IOException {

		ConfigManager configmanager = new ConfigManager();
		
		Options options = new Options();
		options.addOption("c", "configfile", true, "Sets the path to a configfile");
		options.addOption("i", "interval", true, "Sets the capture interval in seconds");
		options.addOption("m", "max-images", true, "Sets the maximum of saved images in the dropbox");
		options.addOption("t", "token", true, "Sets the dropbox access token");
		options.addOption("s", "ssid", true, "Sets the ssid for the WIFI access point");
		options.addOption("p", "wifipass", true, "Sets the WPA2 key for the WIFI access point");
		options.addOption("r", "rpi-mode", false, "Sets the raspberry pi mode for capture with rpicam");
		options.addOption("h", "help", false, "Prints this help");
		
		// parse the command line arguments
		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(options, args);
		HelpFormatter formatter = new HelpFormatter();
		
		if(line.hasOption("help")) {
			formatter.printHelp("NPetcam.jar", options);
			System.exit(0);
		}
		
		if(line.hasOption("configfile")) {
			configmanager.loadProperties(new File(line.getOptionValue("configfile")));
			log.info("Started with configfile");
		} else {
			configmanager.reloadProperties();
		}
		
		if(line.hasOption("interval")) {
			configmanager.setValue("interval", line.getOptionValue("interval"));
			log.info("Starts with interval = " + configmanager.getValue("interval"));
		}
		
		if(line.hasOption("max-images")) {
			configmanager.setValue("maximages", line.getOptionValue("max-images"));
			log.info("Starts with max images = " + configmanager.getValue("maximages"));
		}
		
		if(line.hasOption("token")) {
			configmanager.setValue("dbxtoken", line.getOptionValue("token"));
			log.info("Starts with dropbox access token");
		}
		
		if(line.hasOption("ssid")) {
			configmanager.setValue("ssid", line.getOptionValue("ssid"));
			log.info("Starts with SSID = " + configmanager.getValue("ssid"));
		}
		
		if(line.hasOption("wifipass")) {
			configmanager.setValue("wlanpw", line.getOptionValue("wifipass"));
			log.info("Starts with SSID = " + configmanager.getValue("wlanpw"));
		}
		
		if(line.hasOption("rpi-mode")) {
			configmanager.setDevice(new RpiCam());
			log.info("Run with raspberry Pi camera");
		} else {
			configmanager.setDevice(new WebcamDevice());
			log.info("Run with webcam");
		}
		
		return configmanager;
	}
}
