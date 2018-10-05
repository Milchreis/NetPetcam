package de.milchreisjunkie.netpetcam;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import de.milchreisjunkie.netpetcam.cams.RpiCam;
import de.milchreisjunkie.netpetcam.cams.WebcamDevice;

public class CLIManager {
	private static final Logger log = Logger.getLogger(CLIManager.class.getName());
	
	public static Config parse(String[] args) throws ParseException, IOException {

		Config configuration = new Config();
		
		Options options = new Options();
		options.addOption("c", "configfile", true, "Sets the path to a configfile");
		options.addOption("i", "interval", true, "Sets the capture interval in seconds");
		options.addOption("m", "max-images", true, "Sets the maximum of saved images in the dropbox");
		options.addOption("t", "token", true, "Sets the dropbox access token");
		options.addOption("r", "rpi-mode", false, "Sets the raspberry pi mode for capture with rpicam");
		options.addOption("s", "stream", false, "Instead of Dropbox image, stream to port 8080");
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
			configuration.loadProperties(new File(line.getOptionValue("configfile")));
			log.info("Started with configfile");
		} else {
			configuration.reloadProperties();
		}
		
		if(line.hasOption("interval")) {
			configuration.setValue("interval", line.getOptionValue("interval"));
			log.info("Starts with interval = " + configuration.getValue("interval"));
		}
		
		if(line.hasOption("max-images")) {
			configuration.setValue("maximages", line.getOptionValue("max-images"));
			log.info("Starts with max images = " + configuration.getValue("maximages"));
		}
		
		if(line.hasOption("token")) {
			configuration.setValue("dbxtoken", line.getOptionValue("token"));
			log.info("Starts with dropbox access token");
		}
		
		if(line.hasOption("stream")) {
			configuration.setStream(line.hasOption("stream"));
			log.info("Starts with dropbox access token");
		}
		
		if(line.hasOption("rpi-mode")) {
			configuration.setDevice(new RpiCam());
			log.info("Run with raspberry Pi camera");
		} else {
			configuration.setDevice(new WebcamDevice());
			log.info("Run with webcam");
		}
		
		return configuration;
	}
}
