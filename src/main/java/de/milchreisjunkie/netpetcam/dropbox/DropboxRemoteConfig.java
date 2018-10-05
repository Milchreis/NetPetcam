package de.milchreisjunkie.netpetcam.dropbox;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.milchreisjunkie.netpetcam.Config;
import de.milchreisjunkie.netpetcam.interfaces.IRefreshHook;

public class DropboxRemoteConfig implements IRefreshHook {
	private static final Logger log = Logger.getLogger(DropboxRemoteConfig.class);

	private DropboxManager dbxMan;
	private Config config;
	
	public DropboxRemoteConfig(DropboxManager dbxMan, Config config) {
		this.dbxMan = dbxMan;
		this.config = config;
	}
	
	@Override
	public void refresh() throws Exception {
		File remoteconfig = new File("./remote.properties");
		dbxMan.downloadFile("/admin/config.properties", remoteconfig);

		if (remoteconfig.exists()) {

			Properties props = new Properties();
			props.load(new FileInputStream(remoteconfig));

			String interval = props.getProperty("interval");
			if (interval != null) {
				config.setValue("interval", interval);
				log.debug("interval changed to " + interval);
			}

			String maximages = props.getProperty("maximages");
			if (maximages != null) {
				config.setValue("maximages", maximages);
				log.debug("maximages changed to " + maximages);
			}

			remoteconfig.delete();
		}
	}

}
