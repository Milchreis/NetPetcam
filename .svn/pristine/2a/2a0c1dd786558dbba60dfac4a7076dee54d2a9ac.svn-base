package de.milchreisjunkie.netpetcam.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.log4j.Logger;

public class WLANCheck {
	private static final Logger LOG = Logger.getLogger(WLANCheck.class.getName());

	public static void setupWlan(String ssid, String pass) throws IOException {
		
		if(ssid == null || ssid.isEmpty() || pass == null || pass.isEmpty()) {
			LOG.info("No wifi changes, because no pass or ssid found");
			return;
		}
		
		File networkfile = new File("/etc/wpa_supplicant/wpa_supplicant.conf");
		String content = new String(Files.readAllBytes(Paths.get(networkfile.toURI())));
		LOG.info("wifi settings loaded");
		
		if(!content.contains(ssid)) {
			content = 
					"country=GB\n"+
					"ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev\n"+
					"update_config=1\n"+
					"\n" +
					"network={ \n" +
						"\tssid=\""+ssid+"\"\n" +
						"\tpsk=\"" +pass+"\"\n" +
					"}";

			Files.write(networkfile.toPath(), content.getBytes(), StandardOpenOption.WRITE);
			LOG.info("wifi settings changed");
		}
	}
}
