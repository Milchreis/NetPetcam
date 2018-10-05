package de.milchreisjunkie.netpetcam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import de.milchreisjunkie.netpetcam.interfaces.ICaptureDevice;

public class ConfigManager {

	private Properties properties;
	private ICaptureDevice device;

	public ConfigManager() throws FileNotFoundException, IOException {
	}
	
	public void reloadProperties() throws FileNotFoundException, IOException {
		Properties systemProps = System.getProperties();
		String home = systemProps.getProperty("user.home");
		File configFile = new File(home, "npetcam.properties");
		if(configFile.exists())
			loadProperties(configFile);
	}

	public void loadProperties(File file) throws IOException {
		try(InputStream inputstream = new FileInputStream(file)) {
			properties = new Properties(); 
			properties.load(inputstream);
		}
	}
	
	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
	public void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

	public ICaptureDevice getDevice() {
		return device;
	}

	public void setDevice(ICaptureDevice device) {
		this.device = device;
	}

}
